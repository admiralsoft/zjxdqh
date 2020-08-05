
package com.tcp.mq.factory;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.JsonObject;
import com.tcp.tcp.vo.send.vo.SendBlacklistVo;
import com.tcp.tcp.vo.send.vo.SendPileLocalConfigVo;
import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import com.tcp.util.JsonUtils;
import com.tcp.util.OutUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.core.factory.BaseFactory;
import com.tcp.mq.base.MQFactory;


/**
 * MQ工厂逻辑
 */
@Service("mqFactoryImpl")
@Slf4j
public class MQFactoryImpl extends BaseFactory implements MQFactory<JsonObject> {

    @Override
    public void mqMessage(JsonObject jsonObject ) {
        int code = jsonObject.getCode();
        String pileNum = jsonObject.getPileNum();
        OutUtil.println("收到的MQ："+JSONObject.toJSONString(jsonObject));
        if (code > 0) {
            // TODO 这里的MQ格式需要自己定义 jsonObject.getObj()转换json或者转换对应的mxxxx对象处理
            switch (code) {
                case MQCode.CHARGING:// 充电操作
                    activeStartChargingServiceImp.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.ORDER_CHARGE:// 预约操作
                    activeReservationServiceImpl.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.SEARCH_O:// 查询预约
                    activeQueryReservationServicelmpl.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.RECORD_SEARCH:// 查询记录

                    break;
                case MQCode.DOWNLOAD_SEARCH:// 下载搜索

                    break;
                case MQCode.CONFIG:// 配置电桩
                    SendPileLocalConfigVo configVo = JsonUtils.toObject(JsonUtils.toJson(jsonObject.getObj()), SendPileLocalConfigVo.class);
                    activePileLocalConfigServiceImpl.send(configVo, jsonObject.getPileNum());
                    break;
                case MQCode.PILE_HEART:// 设置电桩心跳
                    setUpHeartbeatServiceImpl.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.RESTART_PILE:// 重启电桩
                    restartServiceImpl.send(JsonUtils.toJson(jsonObject.getObj()), pileNum);
                    break;
                case MQCode.CONFIG_SYS:// 109 0x0103 远程配置桩体数据
                    activeRemoteChargingConServiceImpl.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.CONFIG_TIME_PRICE:// // 设置时间段以及费率
                    activePrescribedRateServiceImpl.send(JSONObject.toJSONString(jsonObject.getObj()), jsonObject.getPileNum());
                    break;
                case MQCode.IS_UPDATE:// 是否更新

                    break;
                case MQCode.UPDATE_VERSION:// 版本检测更新

                    break;
                case MQCode.NET_UPDATE:// ftp更新
                    //逻辑写在0108
                    activeUpgradeRequestImpl.send(JsonUtils.toJson(jsonObject.getObj()), pileNum);
                    //updateFTPServiceImpl.send(JsonUtils.toJson(jsonObject.getObj()), pileNum);
                    break;
                case MQCode.PACKAGE_UPDATE:// 下载数据包更新

                    break;
                case MQCode.UPDATE_TIME://校时命令
                    setUpdateTimeServiceImpl.send(null, pileNum);
                    break;
                case MQCode.UPGRADE_REQUEST://升级请求
                    activeUpgradeRequestImpl.send(JsonUtils.toJson(jsonObject.getObj()), pileNum);
                break;
                case MQCode.BLACKLIST_SEND://下传黑名单
                    SendBlacklistVo blacklistVo = JsonUtils.toObject(JsonUtils.toJson(jsonObject.getObj()), SendBlacklistVo.class);
                    activeBlacklistServiceImpl.send(blacklistVo, pileNum);
                    break;
                case MQCode.QUERY_CONFIG://0x0107运营平台发送查询充电桩配置信息命令
                    activeQueryConfigServiceImpl.send(null, pileNum);
                    break;
                case MQCode.KEY_UPDATE://0x0110 运营平台发送密钥更新命令
                    keyUpdateServiceImpl.send(JsonUtils.toJson(jsonObject.getObj()),pileNum);
                    break;
                case MQCode.QUERY_DOWN_RESULT://0x0116 运营平台查询软件/固件下载是否成功命令
                    activeDownResultServiceImpl.send(jsonObject,jsonObject.getPileNum());
                    break;
                case MQCode.QUERY_UPDATE_RESULT://0x0117 运营平台查询软件/固件更新是否成功命令
                    activeUpdateResultServiceImpl.send(jsonObject,jsonObject.getPileNum());
                    break;
//                case MQCode.SIGN_CARD://0x0009 运营平台返回刷卡验证信息
//                    activeSignCardResponseServiceImp.send(JsonUtils.toJson(jsonObject.getObj()),jsonObject.getPileNum());
//                    break;
                case MQCode.QUERY_LOG:// 0x0114 运营平台查询记录命令
                    activeQueryLogServiceImpl.send(jsonObject.getObj(),pileNum);
                    break;
                case MQCode.SIGN_CARD_RESULT://0x0009刷卡验证结果
                    activeSignCardResponseServiceImp.send(jsonObject,pileNum);
                    break;
                case MQCode.VIN_SIGN_RESULT://0x1014VIN码验证结果
                    activeSignVinResponseServiceImp.send(jsonObject, pileNum);
                    break;
                case MQCode.PACKDATA_DOWN://0x0118 运营平台发送充电桩软件升级命令(数据包形式升级)
                    activePackDataDownServiceImpl.send(JsonUtils.toJson(jsonObject.getObj()),pileNum);
                    break;
                default:
                    break;
            }
        }
    }


    private String getObjJson(JsonObject jsonObject) {
        return JsonUtils.toJson(jsonObject.getObj());
    }

    @Override
    public void mqMessage(Object code, Object pileNum, JsonObject s) {

    }

    private boolean isNumber(String input) {

        String regex = "^\\d+$";
        return (input.replace(",", "")).matches(regex);

    }

}
