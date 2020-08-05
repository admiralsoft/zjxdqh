package com.tcp.tcp.active.impl;

import com.tcp.bean.MPrescribedRate;
import com.tcp.bean.MPrescribedRateInfo;
import com.tcp.core.code.MQCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.MPrescribedRateInfoMapper;
import com.tcp.mapper.MPrescribedRateMapper;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author TcT
 * Date: 2018/7/24.
 * Time: 下午5:07.
 * @Title:
 * @Description: 充电设备费率及时间段
 */
@Service("activePrescribedRateServiceImpl")
public class ActivePrescribedRateServiceImpl extends AbStracActiveService<String> {

    @Resource
    private MPrescribedRateMapper mPrescribedRateMapper;

    @Resource
    private MPrescribedRateInfoMapper mPrescribedRateInfoMapper;


    @Override
    public DictCodeEnum.SendMessageResult activeSend(String data, String pileNum) {

        PrescribedRateVo vo = JsonUtils.toObject(data, PrescribedRateVo.class);

        if (vo != null) {

            List<PrescribedRateInfo> rateInfos = vo.getPrescribedRateInfos();

            MPrescribedRate rate = new MPrescribedRate();

            //int timeNum = vo.getTimeNum();
            String uuid = StringUtil.get32UUID();
            rate.setId(uuid);
            //枪号
            rate.setGunNum(vo.getGunNum());
            //时间段个数
            rate.setTimeNum(rateInfos.size());
            rate.setCreateTime(new Date());
            rate.setPileNum(pileNum);
            //入库父表
            mPrescribedRateMapper.insert(rate);
            //入库子表
            MPrescribedRateInfo prescribedRateInfo;
            for (PrescribedRateInfo rateInfo : rateInfos) {
                prescribedRateInfo = new MPrescribedRateInfo();
                prescribedRateInfo.setId(StringUtil.get32UUID());
                //父键
                prescribedRateInfo.setParentId(uuid);
                //todo: 这里MQ字符串为12:23:34,(否则这边改)需要协商转换
                prescribedRateInfo.setStarTime(rateInfo.getStarTime());
                prescribedRateInfo.setEndTime(rateInfo.getEndTime());
                prescribedRateInfo.setPowerRate(rateInfo.getPowerRate());
                prescribedRateInfo.setServiceRate(rateInfo.getServiceRate());
                prescribedRateInfo.setCreateTime(new Date());
                mPrescribedRateInfoMapper.insert(prescribedRateInfo);
            }
            return sendMessage(prescribedRateResultCommandImpl.getByteInfo(vo, pileNum, getCmd()), pileNum, getCmd());
        }
        return DictCodeEnum.SendMessageResult.NO_CHANNEL;
    }

    @Override
    public short getCmd() {
        return TCPCode.PILE_PRESCRIBED_RATE;
    }

    @Override
    protected int getMqCode(){
        return MQCode.CONFIG_TIME_PRICE;
    }
}
