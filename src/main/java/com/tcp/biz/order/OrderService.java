package com.tcp.biz.order;

import com.tcp.bean.BOrderChargingInfo;
import com.tcp.mapper.BOrderChargingInfoMapper;
import com.tcp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {


    @Autowired
    private BOrderChargingInfoMapper chargingInfoMapper;


    /**
     * 保存停止充电数据信息
     * @param chargingInfo
     * @return
     */
    public int saveStopChargingInfo(BOrderChargingInfo chargingInfo) {
        if (chargingInfo == null) return 0;
        chargingInfo.setOrderType(BOrderChargingInfo.STOP_CHARGE);
        chargingInfo.setId(StringUtil.get32UUID());
        chargingInfo.setCreateTime(new Date());
        return chargingInfoMapper.insert(chargingInfo);
    }

    /**
     * 保存开始充电数据信息
     * @param chargingInfo
     * @return
     */
    public int saveStartChargingInfo(BOrderChargingInfo chargingInfo) {
        if (chargingInfo == null) return 0;
        chargingInfo.setOrderType(BOrderChargingInfo.START_CHARGE);
        chargingInfo.setId(StringUtil.get32UUID());
        chargingInfo.setCreateTime(new Date());
        return chargingInfoMapper.insert(chargingInfo);
    }


}
