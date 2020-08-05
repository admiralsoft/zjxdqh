package com.tcp.biz.pile;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.JsonObject;
import com.tcp.bean.TGun;
import com.tcp.bean.TPile;
import com.tcp.bean.TPileStatusInfo;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.retry.RetryRecord;
import com.tcp.core.task.SignStatusTask;
import com.tcp.mapper.TGunMapper;
import com.tcp.mapper.TPileMapper;
import com.tcp.mapper.TPileStationMapper;
import com.tcp.mapper.TPileStatusInfoMapper;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.GunStateVo;
import com.tcp.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PileService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TPileMapper pileMapper;

    @Autowired
    private TGunMapper gunMapper;

    @Autowired
    protected TPileStatusInfoMapper tPileStatusInfoMapper;

    @Autowired
    private TPileStationMapper pileStationMapper;

    @Autowired
    protected RabbitMqSender sender;

    public static final org.slf4j.Logger logremindLong=org.slf4j.LoggerFactory.getLogger("com.tcp.off.line.remind.long");



    /**
     * 获取桩信息
     * @param pileNum
     * @return
     */
    public TPile getPileById(String pileNum) {
        TPile pile = (TPile) redisUtil.get(RedisCode.PILE_INFO, pileNum);
        if (pile == null) {
            pile = pileMapper.getPileByNum(pileNum);
            if (pile != null) {
                redisUtil.set(RedisCode.PILE_INFO, pileNum, pile, (long) (5*60));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("获取桩体信息[{}]，DB：{}", pileNum,JSONObject.toJSONString(pile));
            }
        }
        return pile;
    }

    /**
     * 更新桩信息
     * @param pile
     * @return
     */
    public int updatePile(TPile pile) {
        if (pile != null && !StringUtil.isEmpty(pile.getPileNum())) {
            redisUtil.remove(RedisCode.PILE_INFO, pile.getPileNum());
            if (logger.isDebugEnabled()) {
                logger.debug("更新桩【{}】信息及清理缓存", pile.getPileNum());
            }
            return pileMapper.updPileByNum(pile);
        }
        return 0;
    }


    /**
     * 修改枪状态
     * @param gun
     * @return
     */
    public int updateGunStat(TGun gun) {
        if (gun == null || StringUtil.isEmpty(gun.getGunNum()) || StringUtil.isEmpty(gun.getPileNum())) {
            return 0;
        }
        return gunMapper.updateGunStat(gun);
    }

    /**
     * 根据桩号,枪号,更改枪状态
     */
    public void updateGunAdminState(TGun gun){
        gunMapper.updateGunAdminState(gun);
    }

    /**
     * 更新桩及离线流水信息
     * @param pile
     * @return
     */
    @Async
    public int updatePile4OnOff(TPile pile) {
        updateLeaveTime(pile);

        if (pile.getPileSigninState() != null) {
            List<TGun> tGuns = gunMapper.selectByPileNum(pile.getPileNum());
            if (pile.getPileSigninState() == TPile.SIGN_OFFLINE) {
                // 离线
                sendGunStat(tGuns, pile.getPileNum(), -2);
                pile.setLastOfflineTime(new Date());
            } else if (pile.getPileSigninState() == TPile.SIGN_FAULT) {
                // 故障
                sendGunStat(tGuns, pile.getPileNum(), -1);
            } else if (pile.getPileSigninState() == TPile.SIGN_ONLINE) {
                pile.setLastOnlineTime(new Date());
                sendGunStat(tGuns, pile.getPileNum(), 1);
            }
        }
        return updatePile(pile);
    }

    private void sendGunStat(List<TGun> guns, String pileNum, int gunSate) {

        for (TGun gun : guns) {
            GunStateVo stateVo = new GunStateVo();
            stateVo.setGunNum(Integer.valueOf(gun.getGunNum()));
            stateVo.setGunAdminState(1);
            // 离线
            stateVo.setGunChargingState(gunSate);
            sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "", MQCode.GUN_STATES, true, stateVo));
        }
    }

    /**
     * 更新桩离线流水
     */
    public int updateLeaveTime(TPile pile) {

        Date now = new Date();
        String pileNum = pile.getPileNum();
        if (StringUtil.isEmpty(pileNum)) {
            return 0;
        }
        Integer sign = pile.getPileSigninState();
        if (TPile.SIGN_ONLINE == sign) {
            //上线 (30秒内重复注册 不予记录)
            boolean exists = redisUtil.exists(RedisCode.PILE_INFO, "sign:" + pileNum);
            if (!exists) {
                redisUtil.set(RedisCode.PILE_INFO, "sign:" + pileNum, 1, 30L);
                //根据创建时间获取最新的一条
                TPileStatusInfo info = tPileStatusInfoMapper.selectLastOnlineTime(pileNum);
                if (info != null && info.getLeaveTime() == null ) {
                    //设置离线时间
                    info.setLeaveTime(now);
                    //状态修改为在线状态
                    info.setState((byte)0);
                    info.setUpdateTime(now);
                    tPileStatusInfoMapper.updateByPrimaryKeySelective(info);
                }
                TPileStatusInfo onlineInfo =  new TPileStatusInfo();
                //设置桩号
                onlineInfo.setPileNum(pileNum);
                onlineInfo.setLastLeaveTime(info != null ? info.getLeaveTime() : null);
                //设置上线时间
                onlineInfo.setOnlineTime(now);
                onlineInfo.setCreateTime(now);
                //设置桩的状态为在线
                onlineInfo.setState((byte)1);
                tPileStatusInfoMapper.insertSelective(onlineInfo);
                logger.debug("添加一条上线信息：{}", JsonUtils.toJson(onlineInfo));
                logger.debug("桩已上线, 桩号[{}]，时间：[{}]", pileNum, DateUtil.getNow());


            }
        } else if (TPile.SIGN_OFFLINE == sign) {
            //下线
            //根据创建时间获取最新的一条
            TPileStatusInfo info = tPileStatusInfoMapper.selectLastOnlineTime(pileNum);
            logger.debug("更新最近下线信息：{}", JsonUtils.toJson(info));
            if (info != null && info.getLeaveTime() == null) {
                String stationName = getStationByPileId(info.getPileNum());
                SignStatusTask.logremind.debug("场站[{}]，桩号[{}]离线，时间[{}]", stationName, pileNum, DateUtil.getNow());
                info.setState((byte) 0);
                info.setLeaveTime(now);
                info.setUpdateTime(now);
                tPileStatusInfoMapper.updateByPrimaryKeySelective(info);

                //20190312 添加桩长时间离线延迟队列
                String retryKey=stationName+"|"+pileNum;
                sender.offLinesendDelay(new RetryRecord(5,retryKey));
            }
        }
        return 1;
    }

    public String getStationByPileId(String pileNum) {
        if (StringUtils.isEmpty(pileNum)) {
            return null;
        }
        return pileStationMapper.getStationByPileId(pileNum);
    }

    /**
     * 获取桩的对应分时电价
     * @param pileNum
     * @return
     */
    public List<PrescribedRateInfo> getPriceRates(String pileNum) {
        if (StringUtil.isEmpty(pileNum)) {
            return null;
        }
        return pileMapper.getPriceRates(pileNum);
    }

    /**
     *
     *  桩长时间离线业务处理
     * @param mgs
     * @param current 当前重试次数
     */
    public boolean offLineService(String mgs,int current)
    {

        String str[]=mgs.split("\\|");
        String stationName=str[0];
        String pileNum=str[1];

        logger.info("stationName:"+stationName);
        logger.info("pileNum:"+pileNum);

        Integer num=pileMapper.getOffLineMinByNum(pileNum);
        if(num==null)
        {
            return false;
        }
        logger.info("桩号:"+pileNum+",距当前时间离线分钟数"+num);

        boolean flag=false;
        //3,6,12,24,48小时提醒
        if(current==1 && num>=180)
        {
            flag=true;
        }else if(current==2 && num>=360)
        {
            flag=true;
        }else if(current==3 && num>=720)
        {
            flag=true;
        }else if(current==4 && num>=1440)
        {
            flag=true;
        }else if(current==5 && num>=2880)
        {
            flag=true;
        }

        if(flag)
        {
            logremindLong.debug("场站[{}]，桩号[{}]离线，离线时长[{}]，请运维同事重点关注！", stationName, pileNum, getoffLineTimeZh(num));
            return true;
        }

        return false;
    }

    public String getoffLineTimeZh(Integer dt)
    {
        if (dt < 60) {
            return dt + "分钟";
        }else
        {
            int hour = Math.round(dt / 60);

            int minute = Math.round(dt - (hour * 60));

            return hour + "小时" + (minute == 0 ? "" : minute + "分钟");
        }

    }

    protected JsonObject getResultObj(String pileNum, String msg, Integer code, Boolean isSuccess) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.setPileNum(pileNum);
        jsonObject.setCode(code);
        jsonObject.setIp(CoreUtil.getHost());
        jsonObject.setMqId(StringUtil.get32UUID());
        jsonObject.setTimestemp(System.currentTimeMillis());
        jsonObject.setSuccess(isSuccess);
        jsonObject.setMsg(msg);
        return jsonObject;
    }

    protected JsonObject getResultObj(String pileNum, String msg,Integer code,Boolean isSuccess,Object obj) {
        JsonObject jsonObject = getResultObj(pileNum, msg, code, isSuccess);
        jsonObject.setObj(obj);
        return jsonObject;
    }





}
