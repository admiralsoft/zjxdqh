package com.tcp.tcp.active.impl;

import com.tcp.bean.MRemoteChargingConfig;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.RemoteChargingConfigVo;
import com.tcp.util.JsonUtils;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 *         Date: 2018/7/24.
 *         Time: 下午10:07.
 * @Title:
 * @Description: 远程配置系统
 */
@Service("activeRemoteChargingConServiceImpl")
public class ActiveRemoteChargingConServiceImpl extends AbStracActiveService<String> {

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String data, String pileNum) {

        MRemoteChargingConfig config = JsonUtils.toObject(data, MRemoteChargingConfig.class);
        RemoteChargingConfigVo configVo = new RemoteChargingConfigVo();
        //设备类型(1 直流快，2 直流慢，3 交流快， 4 交流慢，5 交直流混合 0不配置)
        configVo.setPileType(config.getPileType());
        //经度
        configVo.setPileLongitude(String.valueOf(config.getPileLongitude()));
        //纬度
        configVo.setPileLatitude(String.valueOf(config.getPileLatitude()));
        //车位号
        configVo.setCarportNum(config.getCarportNum());
        //停车位号
        configVo.setParkingNum(config.getParkingNum());
        //所属电站编号
        configVo.setPowerStationNum(String.valueOf(config.getPowerStationNupowerStationNum()));
        //所诉地区编号
        configVo.setAreaNum(String.valueOf(config.getAreaNum()));
        //运营类型(1 私有不开放，2 私有开发，3 共有免费 4，共有收费 0 不配置)
        configVo.setOperationType(config.getOperationType());
        return sendMessage(remoteChargingConfigCommandImpl.getByteInfo(configVo, pileNum, getCmd()), pileNum, getCmd());
    }

    @Override
    public short getCmd() {
        return TCPCode.PILE_REMOTE_CHARGING_CONFIG;
    }
}
