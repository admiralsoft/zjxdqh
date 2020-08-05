
package com.tcp.tcp.factory;

import com.tcp.util.DataUtil;
import com.tcp.util.OutUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.tcp.core.code.TCPCode;
import com.tcp.core.factory.BaseFactory;
import com.tcp.tcp.base.TCPFactory;

import io.netty.channel.ChannelHandlerContext;

/**
 * TCP工厂管理
 */
@Component
@Slf4j
public class TCPFactoryImpl extends BaseFactory implements TCPFactory {

    @Override
    public void produce(int cmd, String pileNum, byte[] req, ChannelHandlerContext ctx) {
        OutUtil.println("解析的:"+DataUtil.printHexByte(req));
        /* 解密 */
        /* byte[] bytes = DataUtil.decode(req, TCPCode.KEY); */
        byte[] bytes = req;

        if (log.isDebugEnabled())
            log.debug("上传指令[{}]，上传桩[{}]，消息内容：{}", DataUtil.numToHex16(cmd),pileNum, DataUtil.printHexByte(req));
        long ctime = System.currentTimeMillis();
        switch (cmd) {
            case TCPCode.PILE_SIGNIN:
                /* 注册逻辑 */
                pileSigninServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_HEARTBEAT:
                /* 心跳 */
                pileHeartbeatServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.HeartBeat_BACK:
                /* 回复心跳包设置 */
                setHeartbeatResultServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_UPD_STATE:
                /* 0x1007 状态改变 */
                gunStateServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_TIME_INFO:
                /* 0x1006 定时数据 */
                gunTimeInfoServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_CHARGING_INFO:
                /* 0x1005 发送充电数据,充电中数据 */
                chargingInfoServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.START_BACK:
                /* 启动充电数据 */
                startChargingServiceImpl.service(ctx, bytes, pileNum);
                break;

            case TCPCode.START_CHARGING_BACK:
                /* 桩回复启动信息 */
                startChargingFromPileServiceImp.service(ctx, bytes, pileNum);
                break;
            case TCPCode.END_CHARGING:
                /* 桩回复结束充电信息 */
                endChargingFromPileServiceImp.service(ctx, bytes, pileNum);
                break;
            case TCPCode.END_CHARGING_INFO:
                /* 0x1011 桩回复结束充电信息 */
                endDataFromPileServiceImp.service(ctx, bytes, pileNum);
                break;
            case TCPCode.prescribed_rate:
                /* 充电设备费率及时间段 */
                prescribedRateServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_OFFLINE_INFO:
                /*离线充电数据信息*/
                offlineInfoServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.remote_charging_config:
                /*发送远程配置充电系统*/
                remoteChargingConfigServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.SCHOOL_TIME_BACK:
                /*回复校时命令*/
                schoolTimeBackServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.CAR_VIN:
                /*车辆VIN验证充电*/
                carVINServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.CAR_VIN_BACK:
                /*车辆VIN验证回复信息*/
                carVINBackServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_ERROR_MESSAGE:
                /*0x1004桩体上报故障信息*/
                errorMessageServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.WARNING_GUARD:
                /*0x1012桩体上报预警码及保护码*/
                warningAndGuardServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.RESERVATION_BACK:
                /*充电桩回复预约指令功能码*/
                reservationServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.FTP_UPGRADE_RESULT:
                /*充电桩软件升级命令功能码*/
                fTPUpgradeServiceImpl.service(ctx, bytes, pileNum);
                break;

            case TCPCode.PILE_LOCAL_CONFIG_BACK:
                /*0x0204充电桩回复本地参数配置指令功能码*/
                fTPUpgradeServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.PILE_UPGRADE_RESULT:
                /*充电桩回复软件升级请求*/
                upgradeRequestServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.UPDATE_RESULT:
                /* 桩体上报软件更新结果 */
                updateResultServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.RESERVATION_RESULT:
                /*0x0212充电桩回复预约查询*/
                reservationResultServiceImpl.service(ctx, bytes, pileNum);
                break;
            case TCPCode.RESTART_CHARGE_BACK:
                /*0x0213充电桩回复重启命令*/
                restartChargeServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.QUERY_CONFIG_RESULT:
                /*0x0207充电桩回复配置信息查询功能码*/
                queryConfigServiceImpl.service(ctx,bytes,pileNum);
                break;

            case TCPCode.TIME_SYNCHRONOUS:
                /*0x1013 发送时间同步指令信息*/
                timeSynchronousServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.KEY_UODATE_RESULT:
                /* 充电桩回复密钥更新功能码*/
                keyUpdateResultServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.QUERY_LOG_RESULT:
                /*充电桩回复查询记录*/
                queryLogResultServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.CARD_START:
                /*0x1015 刷卡启动结果*/
                cardStartResultServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.PACK_DATA_DOWN:
                /*0x0218 充电桩回复软件升级命令(数据包形式升级)*/
                packDataDownServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.SIGN_CARD:
                 /*0x1009 刷卡充电请求*/
                signCardRequestServiceImpl.service(ctx,bytes,pileNum);
                break;
            case TCPCode.PILE_BMS_MESSAGE:
                /* 0x1016BMS返回数据*/
                pileBMSMessageServiceImpl.service(ctx,bytes,pileNum);
                break;
            default:
                break;
        }
        log.debug("桩[{}]指令[{}]回复处理耗时:{}", pileNum, DataUtil.numToHex16(cmd), System.currentTimeMillis() - ctime);
    }
}
