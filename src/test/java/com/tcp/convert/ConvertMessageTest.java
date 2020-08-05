package com.tcp.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.LogVinCharge;
import com.tcp.tcp.base.message.BaseMessage;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import com.tcp.tcp.convert.util.ConvertUtil;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.LogMsDataVo;
import com.tcp.tcp.vo.receive.vo.LogVinChargeVo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;
import com.tcp.tcp.vo.send.vo.SendPileLocalConfigVo;
import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import com.tcp.util.DataUtil;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;

import java.math.BigDecimal;
import java.util.*;

public class ConvertMessageTest {

    public static void main(String[] args) {
//        BaseMessage<TestMessage> mess = new BaseMessage<>();
//
//        mess.setDeviceNo("werw111eikmnujhbgtfqwes");
//        mess.setDataLength(189);
//        mess.setKeyUptTime((int) (System.currentTimeMillis()/1000));
//        mess.setValidCode(12);
//
//        TestMessage t = new TestMessage();
//        t.setPileNum(1121);
//        mess.setData(t);
//
//        t.setChilds(new ArrayList<>());
//        ChildMessage cmess = new ChildMessage();
//        cmess.setGunNum(123);
//        t.getChilds().add(cmess);
//        ChildMessage cmess1 = new ChildMessage();
//        cmess1.setGunNum(456);
//        t.getChilds().add(cmess1);
//        t.setChildLen(t.getChilds().size());
//
//        t.setStrDesc("adbccdfsdsfa");
//        t.setStrLen(t.getStrDesc().length());


//        byte[] bytes = MessageToByteConvert.convertWrapReport(t, "121231231", 0x0201);
//        System.out.println(Arrays.asList(ConvertUtil.toObjects(bytes)));
//
//
//        TestMessage testMessage = ByteToMessageConvert.unWrapConvert(bytes, TestMessage.class);
//        System.out.println(JsonUtils.toJson(testMessage));
//        System.out.println(ByteToMessageConvert.unWrapConvertCmd(bytes));
//        System.out.println(ByteToMessageConvert.unWrapConvertDeviceNo(bytes));


        try {

            SendPileLocalConfigVo configVo = new SendPileLocalConfigVo();
            configVo.setPhaseSystem((byte) 1);
            configVo.setMaxOutput(221);
            configVo.setPileWorkPattern((byte) 7);
            configVo.setOffNetCharge((byte) 3);
            configVo.setOffNetChargeNum(5);
            configVo.setQrCodeLength(4);
            configVo.setQrCode("xwesssaerew");
            configVo.setCommand(0x0101);
            configVo.setGunId(12);
            configVo.setGunUseNum(6);
            configVo.setHeartBeatIntervalTime(2);
            configVo.setPileViewLoadingAccount("account");
            configVo.setPileViewLoadingPassword("aaaaapwd");
            configVo.setQrcodePileNum(1);
            configVo.setServerIp("127.0.0.1");
            configVo.setUsableGunNum(11);
            configVo.setUsablePrePile((byte) 8);
            configVo.setServerPort(8080);
            configVo.setVersionInformation("aaaaaaaa");

//            long l = System.currentTimeMillis();
//            byte[] bts = MessageToByteConvert.convertWrapReport(configVo, "1112222222222", 0x0201);
//            System.out.println(Arrays.asList(ConvertUtil.toObjects(bts)));
//            System.out.println("耗时:" + (System.currentTimeMillis() -l));
//            SendPileLocalConfigVo configVo1 = ByteToMessageConvert.unWrapConvert(bts, SendPileLocalConfigVo.class);
//            System.out.println(ByteToMessageConvert.unWrapConvertCmd(bts));
//            System.out.println(ByteToMessageConvert.unWrapConvertDeviceNo(bts));
//            System.out.println("耗时:" + (System.currentTimeMillis() -l));
//            System.out.println(JsonUtils.toJson(configVo1));


            SimpleByteMessageResult simpleByteMessageResult = new SimpleByteMessageResult();
            simpleByteMessageResult.setResult((byte) 2);
            byte[] bts1 = MessageToByteConvert.convertWrapReport(simpleByteMessageResult, "1112222222222", 0x0201);
            System.out.println(Arrays.asList(ConvertUtil.toObjects(bts1)));
            SimpleByteMessageResult simple = ByteToMessageConvert.unWrapConvert(bts1, SimpleByteMessageResult.class);
            System.out.println(simple.getResult());

            LogMsDataVo dataVo = new LogMsDataVo();
            dataVo.setId(StringUtil.get32UUID());
            dataVo.setCurrentOutMax("222");
            dataVo.setCurrentOutMin("111");
            dataVo.setDcDealer("12");
            dataVo.setDcRatedCapacity("asd");
            dataVo.setDcRatedVoltage("a23");
            dataVo.setDcTcpType("12");
            dataVo.setDcType((byte) 2);
            dataVo.setDcVoltageNow("1111");
            dataVo.setDczChargeNumber(23);
            dataVo.setDczCreateTime(new Date());
            dataVo.setRechargeableCurrentMax("333");
            dataVo.setDczSerial("321");
            dataVo.setDlDemand("4333");
            dataVo.setDzDemand("2222");
            dataVo.setRechargeableStatus("5");
            dataVo.setRechargeableTemperatureMax(30);
            dataVo.setRechargeableVoltageMax("555");
            dataVo.setVoltageOutMax("666");
            dataVo.setVoltageOutMin("222");
            dataVo.setBmsVersion("12");

            byte[] bts2 = MessageToByteConvert.convertWrapReport(dataVo, "1112222222222", 0x0201);
            System.out.println(Arrays.asList(ConvertUtil.toObjects(bts2)));
            LogMsDataVo msDataVo = ByteToMessageConvert.unWrapConvert(bts2, LogMsDataVo.class);
            System.out.println(JsonUtils.toJson(msDataVo));


            PrescribedRateVo rateVo = new PrescribedRateVo();
            rateVo.setGunNum(1);
            rateVo.setPrescribedRateInfos(new LinkedList<>());

            PrescribedRateInfo rateInfo = new PrescribedRateInfo();
            rateInfo.setStarTime("00:00:00");
            rateInfo.setEndTime("08:15:00");
            rateInfo.setPowerRate(new BigDecimal("0.80"));
            rateInfo.setServiceRate(new BigDecimal("1.5"));
            rateVo.getPrescribedRateInfos().add(rateInfo);
            rateInfo = new PrescribedRateInfo();
            rateInfo.setStarTime("08:15:00");
            rateInfo.setEndTime("24:00:00");
            rateInfo.setPowerRate(new BigDecimal("2"));
            rateInfo.setServiceRate(new BigDecimal("2.5"));
            rateVo.getPrescribedRateInfos().add(rateInfo);
            rateVo.setTimeNum(rateVo.getPrescribedRateInfos().size());

            byte[] convert = MessageToByteConvert.convertWrapReport(rateVo, "1110000000000111", 0x1001);

            System.out.println("========================================");
            System.out.println(DataUtil.printHexByte(convert));


            PrescribedRateVo prescribedRateVo = ByteToMessageConvert.unWrapConvert(convert, PrescribedRateVo.class);
            System.out.println("========================================");
            System.out.println(JSONObject.toJSONString(prescribedRateVo));

            System.out.println("*****************************************");

            SignCardResultVo cardResultVo = new SignCardResultVo();
            cardResultVo.setGunNum(1);
            cardResultVo.setCardNum("100012311111112");
//            cardResultVo.setOutCardNum("22222222222222222");
            cardResultVo.setResultCode(3);
//            cardResultVo.setMoney(2.33d);
            cardResultVo.setSn("C2018090818122411100000000001111");
//            byte[] bytes = MessageToByteConvert.convertWrapReport(cardResultVo, "111000000000111", 0x0009);
//            System.out.println("========================================");
//            System.out.println(DataUtil.printHexByte(bytes));
////            cardResultVo = ByteToMessageConvert.unWrapConvert(bytes, SignCardResultVo.class);
//            System.out.println("========================================");
//            System.out.println(JSONObject.toJSONString(cardResultVo));

            LogVinChargeVo logVinCharge = new LogVinChargeVo();
            logVinCharge.setGunNum((byte) 1);
            logVinCharge.setVinNum("53348909999999999");
            logVinCharge.setCharging_type(0);
            logVinCharge.setCharging_data(BigDecimal.valueOf(0.00d));

            byte[] bytes = MessageToByteConvert.convertWrapReport(logVinCharge, "2010000000000111", 0x1014);
            System.out.println(DataUtil.printHexByte(bytes));

            LogVinChargeVo vinChargeVo = ByteToMessageConvert.unWrapConvert(bytes, LogVinChargeVo.class);
            System.out.println(JSON.toJSONString(vinChargeVo));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
