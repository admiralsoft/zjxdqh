package com.tcp.tcp.active.impl;

import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.mq.base.RabbitMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 运营平台发送充电桩软件升级命令（FTP升级）
 * @Author Xu
 */
@Slf4j
@Service("updateFTPServiceImpl")
public class UpdateFTPServiceImpl extends AbStracActiveService<String> {
    @Resource
    RabbitMqSender rabbitMqSender;
    @Override
    public DictCodeEnum.SendMessageResult activeSend(String str, String pileNum) {
        //mq来的消息先入库
        //流程走完之后修改状态为执行成功
        //给平台返回信息
        //判断版本号
        //发送指令
        //先发送0107
        //回复0207需要判断版本号
        //发送0108
        //回复0208成功之后
        //下发0109升级指令
        //回复0209指令
        //重新上线会主动发送0207
        //0207数据入库

        DictCodeEnum.SendMessageResult i1 = sendMessage(updateFTPCommandImpl.getByteInfo(str,pileNum,getCmd()), pileNum, getCmd());
        if(i1 == DictCodeEnum.SendMessageResult.NO_CHANNEL){
            log.info("运营平台发送充电桩软件升级命令（FTP升级）:"+"下发失败");
            //在这一步并不是桩回复失败了而是下发指令失败了
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"下发失败",MQCode.NET_UPDATE,false));
        }
        return i1;
    }

    @Override
    public short getCmd() {
        return TCPCode.FTP_UPDATE;
    }
}
