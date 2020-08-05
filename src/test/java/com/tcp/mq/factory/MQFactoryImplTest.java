package com.tcp.mq.factory;


import com.tcp.bean.JsonObject;
import com.tcp.bean.MRemoteChargingConfig;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * @author TcT
 * Date: 2018/8/21.
 * Time: 下午3:15.
 * @Title:
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MQFactoryImplTest {

    @Resource
    private MQFactoryImpl mqFactory;

    @Test
    public void test(){
        //远程配置系统测试
        JsonObject jsonObject = new JsonObject();
        jsonObject.setMqId("102");
        jsonObject.setPileNum("6668880000000213");
        MRemoteChargingConfig config = new MRemoteChargingConfig();
        config.setPileType(1);
        config.setPileLongitude(new BigDecimal("0"));
        config.setPileLatitude(new BigDecimal("0"));
        config.setCarportNum(0);
        config.setParkingNum(0);
        config.setPowerStationNupowerStationNum(0);
        config.setAreaNum(0);
        config.setOperationType(0);
        jsonObject.setObj(config);
        mqFactory.mqMessage(jsonObject);
    }


    @Test
    public void test1(){
        //充电设备费率及时间段
        JsonObject jsonObject = new JsonObject();
        jsonObject.setMqId("110");
        jsonObject.setPileNum("6668880000000213");
        PrescribedRateVo vo = new PrescribedRateVo();
        LinkedList<PrescribedRateInfo> infos = new LinkedList<>();
        vo.setGunNum(1);
        vo.setTimeNum(1);
        PrescribedRateInfo info = new PrescribedRateInfo();
        info.setStarTime("12:34:30");
        info.setEndTime("14:30:20");
        info.setPowerRate(new BigDecimal(1.5));
        info.setServiceRate(new BigDecimal(0.5));
        PrescribedRateInfo info2 = new PrescribedRateInfo();
        info2.setStarTime("13:34:30");
        info2.setEndTime("15:30:20");
        info2.setPowerRate(new BigDecimal(1.6));
        info2.setServiceRate(new BigDecimal(0.6));
        infos.add(info);
        infos.add(info2);
        vo.setPrescribedRateInfos(infos);
        jsonObject.setObj(vo);
        mqFactory.mqMessage(jsonObject);
    }


    @Test
    public void test2() {
        //充电设备费率及时间段
        JsonObject jsonObject = new JsonObject();
        jsonObject.setMqId("124");
        jsonObject.setPileNum("6668880000000213");
        mqFactory.mqMessage(jsonObject);
    }



}