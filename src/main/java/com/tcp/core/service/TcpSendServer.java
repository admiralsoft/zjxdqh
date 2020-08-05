package com.tcp.core.service;

import com.tcp.bean.TPile;
import com.tcp.bean.TTCPMessage;
import com.tcp.biz.pile.PileService;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.TTCPMessageMapper;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.fo.TCPConnectionFo;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.util.CoreUtil;
import com.tcp.util.DataUtil;
import com.tcp.util.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Yorking
 * @date 2019/02/21
 */
@Service
public class TcpSendServer {

    protected Logger logger = LoggerFactory.getLogger(TcpSendServer.class);


    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private PileService pileService;


    @Autowired
    protected TTCPMessageMapper messageMapper;

    /**
     * 主动下发
     *
     * @param data
     * @param pileNum
     * @param cmd
     * @return 0桩不在线，2-该桩不属于本服务器操作，1允许下发
     */
    public DictCodeEnum.SendMessageResult sendMessage(byte[] data, String pileNum, int cmd) {

        TCPConnectionFo pile = (TCPConnectionFo) redisUtil.get(RedisCode.REDIS_TCP, pileNum);
        if (pile == null) {
            System.out.println("设备号：" + pileNum + ",缓存连接信息获取失败 ");
            return DictCodeEnum.SendMessageResult.NO_CHANNEL;
        }
        String localHost = CoreUtil.getHost();
        System.out.println("localHost: " + localHost);
        if (!localHost.equals(pile.getServiceIp())) {
            System.out.println("设备号：" + pileNum + ", "+localHost+"缓存通道IP地址错误 ");
            logger.debug("桩号[{}], 通信渠道IP[{}], 当前服务IP[{}]", pileNum, pile.getServiceIp(), localHost);
            return DictCodeEnum.SendMessageResult.NO_HOST;
        }
        ChannelHandlerContext ctx = TCPMap.connMap.get(pileNum);
        if (ctx == null) {
            System.out.println("设备号：" + pileNum + ",缓存通道获取失败 ");
            return DictCodeEnum.SendMessageResult.NO_CHANNEL;
        }
        try {
            sendMessage(ctx, data, pileNum, cmd);
            return DictCodeEnum.SendMessageResult.SEND_OK;
        } catch (Exception e) {
            logger.error("指令下达异常: 桩号[" + pileNum + "], 指令["+DataUtil.cmdTohexString(cmd)+"], 异常信息:", e);
            return DictCodeEnum.SendMessageResult.SEND_FAIL;
        }
    }


    /*-----------------------------------下面是发送TCP---------------------------------------*/
    /**
     * 基本用于回复这个就不存在成功失败
     *
     * @param ctx
     *            通道
     * @param data
     *            byte数据
     * @param pileNum
     *            桩号
     */
    public void sendMessage(ChannelHandlerContext ctx, byte[] data, String pileNum, int cmd) {
        logger.info("通道："+ctx);
        TPile pile = pileService.getPileById(pileNum);
        if (null == pile) {
            return;
        }
        if (pile.getPileSigninState() != 1) {
            return;
        }
        if (pile.getIsMessage() == 2 || pile.getIsMessage() == 3) {
            TTCPMessage mess = new TTCPMessage();
            mess.setMessageCmd(DataUtil.cmdTohexString(cmd));
            mess.setMessageInfo(DataUtil.printHexByte(data));
            mess.setMessageType(1);
            mess.setPileNum(pileNum);
            mess.setCreateTime(new Date());
            // todo:事务处理?
            messageMapper.insert(mess);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("下达指令[{}], 下达桩[{}], 消息内容: {}", DataUtil.numToHex16(ByteToMessageConvert.unWrapConvertCmd(data)), pileNum, DataUtil.printHexByte(data));
        }
        ByteBuf replyMsg = Unpooled.buffer(data.length);
        replyMsg.writeBytes(data);
        ctx.writeAndFlush(replyMsg);
    }

}
