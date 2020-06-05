package com.zjxdqh.evcs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import com.zjxdqh.evcs.enums.FailReasonEnum;
import com.zjxdqh.evcs.enums.StopChargeEnum;
import com.zjxdqh.evcs.enums.SuccStatEnum;
import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.vo.*;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.service.HappyService;
import com.zjxdqh.face.vo.ChargeDataTemp;
import com.zjxdqh.face.vo.FOrder;
import com.zjxdqh.face.vo.StopChargeResultVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.xml.transform.Source;

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static com.zjxdqh.evcs.supervise.SuperviseUtils.getConnectorID;

/**
 * @author Yorking
 * @date 2019/05/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EvcsServerApplication.class)
@ActiveProfiles("dev")
public class ApplicationTest {
    
    @Autowired
    private EvcsService evcsService;
    @Autowired
    private HappyService happyService;

    @Test
    public void testGetSupervise() {

        QueryStationsInfoParam param = new QueryStationsInfoParam();
//        param.setLastQueryTime(DateUtils.addWeeks(new Date(), -1));
        QueryStationsInfoResult sites = evcsService.getSuperviseSites(param);

        System.out.println(sites);
        Assert.notEmpty(sites.getStationInfos(), "sites.  empty..  ");

    }


    @Test
    public void test1() {
        BusinessPolicyParam businessPolicyParam = new BusinessPolicyParam();
        String connectorID = SuperviseUtils.getConnectorID("3330000000000123", "1");
        System.out.println(connectorID);
        businessPolicyParam.setConnectorID(connectorID);
        businessPolicyParam.setEquipBizSeq("11111111111");
        BusinessTacticsInfoVo businessTacticsInfoVo = evcsService.queryEquipBusinessPolicy(businessPolicyParam);
        System.out.println(businessTacticsInfoVo.toString());
    }

    /**
     * 6.2 此接口用户客户归属运营商请求充电基础设施的认证信息。
     */
    @Test
    public void test2() {
        RequestEquipmentParam requestEquipmentParam = new RequestEquipmentParam();
        String connectorID = SuperviseUtils.getConnectorID("3330000000000123", "1");
        System.out.println(connectorID);
        requestEquipmentParam.setConnectorID(connectorID);
        requestEquipmentParam.setEquipAuthSeq("hhhhhhhhhhhhhhhh");
        RequestEquipmentVo requestEquipmentVo = evcsService.queryEquipAuth(requestEquipmentParam);
        System.out.println(requestEquipmentVo.toString());
    }

    /**
     * 重庆补贴
     */
    @Test
    public void test3() {
//        evcsService.notificationRecordsAmountInfo();
        ChargeStateParam c = new ChargeStateParam();
        c.setStartChargeSeq("T2019082116304066688800000001231");
        ChargeStateVo chargeStateVo = evcsService.queryEquipChargeStatus(c);
        System.err.println(chargeStateVo);

    }

    /**
     * 订单状态
     */
    @Test
    public void test4() {

        ChargeStateParam chargeStateParam = new ChargeStateParam();
        chargeStateParam.setStartChargeSeq("T2019082116304066688800000001231");

        ChargeStateVo chargeStateVo = evcsService.queryEquipChargeStatus(chargeStateParam);
        System.err.println(JSON.toJSON(chargeStateVo));
    }

    /**
     * 测试订单推送
     */
    @Test
    public void test5(){
        //evcsService.pushOrderInfo("T2020032311564551012100100212011");
        //查询订单
        /*FOrder sn = happyService.findFOderBySn("T2020052413520751010500200610011");
        System.out.println(sn);*/
        //FOrder f = happyService.findFOderBysuperviseSn("MA005DBW1202005241352028588");
        //System.out.println(f);
        FileReader fr=null;
        try {

             fr = new FileReader("C:\\Users\\admin\\Desktop\\805.txt");
            BufferedReader bufferedreader = new BufferedReader(fr);
            String instring=null;
           //创建集合接收集合
            List<String>ms=new ArrayList<>();
            while ((instring = bufferedreader.readLine()) != null) {
                if (0 != instring.length()) {
                    ms.add(instring);
                }
            }


            System.out.println("一共"+ms.size()+"多少条:=========读取完成");
              Thread.sleep(5000);
            System.out.println("=================开始推送===================");
            Thread.sleep(3000);
            for (String m : ms) {
                //第三方订单查询出数据
                FOrder f1 = happyService.findFOderBysuperviseSn(m);
                //根据订单号开始推送
                System.out.println(f1);
                evcsService.pushOrderInfo(f1.getSn());


            }
        } catch (Exception e) {
            System.out.println("出现错误");
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 结算订单
     */
    @Test
    public void test6(){
        /*//计算充电时间
        if (baseOrder.getEndTime() == null || baseOrder.getStartTime() == null){
            orderInfoParam.setChargeLast(0);
        }else{
            Long time = baseOrder.getEndTime().getTime() - baseOrder.getStartTime().getTime();
            if(time < 0){
                orderInfoParam.setChargeLast(0);
            }else{
                orderInfoParam.setChargeLast((int)(time/1000));
            }
        }*/
        //最终返回数据对象
        ChargeStateVo evchargeStateVo = new ChargeStateVo();

        //1.创建订单号
        ChargeStateParam happychargeChargeStateParam=new ChargeStateParam();
        //封装订单号

        // 电费单价 = 电费/充电量 、  服务费单价 = 总服务费/充电量

        // 查询电流电压
        ChargeDataTemp chargeDataTemp = happyService.getVoltageCurrent(happychargeChargeStateParam.getStartChargeSeq());
        System.out.println(chargeDataTemp);
        if (!ObjectUtils.isEmpty(chargeDataTemp)) {
//                evchargeStateVo.setConnectorStatus(Integer.parseInt(PileGunStatusEnum.KV_CHARGING.getValue()));
            evchargeStateVo.setTotalPower(chargeDataTemp.getUseele() == null ? 0 : chargeDataTemp.getUseele().doubleValue());
            evchargeStateVo.setCurrentA(chargeDataTemp.getCurrent().doubleValue());
            evchargeStateVo.setVoltageA(chargeDataTemp.getVoltage().doubleValue());
            evchargeStateVo.setSoc(Double.parseDouble(chargeDataTemp.getSoc()));
        }
        System.out.println(evchargeStateVo);
        //生成订单号


    }
    @Test
    public void test8(){
        String pileNum="125";//桩号
        String  gunNum="2";//枪号
        Integer type=1;//0，app启动；1，刷卡启动；2，后台启动订单
        String orderNo = SuperviseUtils.getOrderNo(pileNum, gunNum, type);
        System.out.println(orderNo);
    }

    /**
     * StartChargeSeqStat=4,
     * ConnectorID=66688800000001231,
     * ConnectorStatus=1,
     * CurrentA=0.0,
     * CurrentB=0.0,
     * CurrentC=0.0,
     * VoltageA=0.0,
     * VoltageB=0.0,
     * VoltageC=0.0,
     * Soc=50.0,
     * StartTime=Thu Aug 22 05:30:41 CST 2019,
     * ChargeModel=3,
     * EndTime=Fri Sep 06 09:45:40 CST 2019,
     * TotalPower=0.0,
     * ElecMoney=100.0,
     * SeviceMoney=100.0,
     * TotalMoney=200.0,
     * SumPeriod=2,
     *
     * ChargeDetails=[ChargeOrderDetailVo(
     *      DetailStartTime=Fri Aug 02 02:38:55 CST 2019,
     *      DetailEndTime=Mon Sep 02 02:39:02 CST 2019,
     *      ElecPrice=1.0,
     *      SevicePrice=1.0,
     *      DetailPower=50.5,
     *      DetailElecMoney=50.0,
     *      DetailSeviceMoney=50.0),
     * ChargeOrderDetailVo(
     *      DetailStartTime=Fri Sep 06 02:37:19 CST 2019,
     *      DetailEndTime=Sat Sep 07 02:37:22 CST 2019,
     *      ElecPrice=0.8,
     *      SevicePrice=1.0,
     *      DetailPower=30.0,
     *      DetailElecMoney=20.0,
     *      DetailSeviceMoney=30.0)]
     */
}