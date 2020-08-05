package com.tcp.tcp.active.impl;

import com.tcp.bean.JsonObject;
import com.tcp.bean.MUpgradeCommand;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.MUpgradeCommandMapper;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TcT
 * Date: 2018/8/6.
 * Time: 下午4:26.
 * @Title:
 * @Description: 升级请求
 */
@Service("activeUpgradeRequestImpl")
public class ActiveUpgradeRequestImpl extends AbStracActiveService<String> {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Resource
    MUpgradeCommandMapper mUpgradeCommandMapper;

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String json, String pileNum) {
        //ftp升级请求过来先请求桩是否可以升级
        //桩返回信息的时候下发升级指令
        //请求的时候把请求数据缓存在redis中
        //单桩升级请求正常只有一次
        //请求下发之后清楚redis中的记录
        //ftp对象
        //升级前有个操作需要核对版本信息,暂时流程中没有做这块
        //因为桩升级之后会主动上传0207,里面会携带桩的版本信息,在0207中数据会入库
        //在之后逻辑中可以在数据库直接时间排序查出来桩当前的版本信息,如果需要做比对要考虑桩的版本信息的定义
        //0001----0002---这种对应数据
        //升级数据入库操作方便追溯升级记录
        logger.debug("升级JSON： " +json);
        MUpgradeCommand mUpgradeCommand = JsonUtils.toObject(json, MUpgradeCommand.class);
        mUpgradeCommand.setId(StringUtil.get32UUID());
        mUpgradeCommand.setCreateTime(new Date());
        mUpgradeCommand.setPileNum(pileNum);
        mUpgradeCommandMapper.insert(mUpgradeCommand);
        redisUtil.set(RedisCode.FTP, pileNum, json);
        //下发
        //现在先写成软件升级
        //后期有改动的时候改成入参多加一个参数
        //1. 软件升级（通讯板软件）
        //2固件升级（控制板软件）
        DictCodeEnum.SendMessageResult i = sendMessage(upgradeRequestCommandImpl.getByteInfo(1, pileNum, getCmd()), pileNum, getCmd());
        //1. 可以升级
        //2禁⽌升级
        if (DictCodeEnum.SendMessageResult.SEND_OK == i) {
            //不需要做任何处理
        } else {
            //回复消费端失败
            JsonObject jsonObject = getResultObj(pileNum, "禁止升级", MQCode.NET_UPDATE, false);
            rabbitMqSender.sendRabbitmqCollectDirect(jsonObject);
        }
        return i;
    }

    @Override
    public short getCmd() {
        return TCPCode.UPGRADE_REQUEST;
    }
}
