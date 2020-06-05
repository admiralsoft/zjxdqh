package com.zjxdqh.evcs.supervise;

import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.supervise.vo.RequestResult;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.vo.SupersiveInfo;
import com.zjxdqh.tools.AESUtils;
import com.zjxdqh.tools.DateUtils;
import com.zjxdqh.tools.HMacMD5Utils;
import com.zjxdqh.tools.MathUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author Yorking
 * @date 2019/05/07
 */
public class SuperviseUtils {

    public static ThreadLocal<OperatorInfo> OperatorInfo = new ThreadLocal<>();


    public static ThreadLocal<List<OperatorInfo>> OperatorInfoList = new ThreadLocal<>();

    /**
     * 请求参数添加签名
     *
     * @param requestResult
     * @param sigSecret
     * @return
     */
    public static RequestResult sign(RequestResult requestResult, String sigSecret) {
        StringBuffer sb = new StringBuffer(requestResult.getOperatorID());
        sb.append(requestResult.getData());
        sb.append(requestResult.getTimeStamp());
        sb.append(requestResult.getSeq());
        try {
            String sign = HMacMD5Utils.encryptHMAC2String(sb.toString(), sigSecret);
            requestResult.setSig(sign.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestResult;
    }

    /**
     * 返回结果 添加签名
     *
     * @param responseResult
     * @param sigSecret
     * @return
     */
    public static ResponseResult sign(ResponseResult responseResult, String sigSecret) {
        StringBuffer sb = new StringBuffer();
        sb.append(responseResult.getRet());
        sb.append(responseResult.getMsg());
        sb.append(responseResult.getData());
        try {
            String sign = HMacMD5Utils.encryptHMAC2String(sb.toString(), sigSecret);
            responseResult.setSig(sign.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    /**
     * 校验返回结果的签名
     *
     * @param responseResult
     * @param sigSecret
     * @return
     */
    public static boolean checkSign(ResponseResult responseResult, String sigSecret) {
        StringBuffer sb = new StringBuffer();
        sb.append(responseResult.getRet());
        sb.append(responseResult.getMsg());
        sb.append(responseResult.getData());
        try {
            if (StringUtils.hasLength(responseResult.getSig())
                    && responseResult.getSig().equalsIgnoreCase(HMacMD5Utils.encryptHMAC2String(sb.toString(), sigSecret))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 校验请求内容的签名
     *
     * @param requestResult
     * @param sigSecret
     * @return
     */
    public static boolean checkSign(RequestResult requestResult, String sigSecret) {
        try {
            if (StringUtils.hasLength(requestResult.getSig())) {
                StringBuffer sb = new StringBuffer(requestResult.getOperatorID());
                sb.append(requestResult.getData());
                sb.append(requestResult.getTimeStamp());
                sb.append(requestResult.getSeq());
                String sign = HMacMD5Utils.encryptHMAC2String(sb.toString(), sigSecret);
                if (requestResult.getSig().equalsIgnoreCase(sign)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取充电设备编号
     *
     * @param pileNum
     * @param gunNum
     * @return
     */
    public static String getConnectorID(String pileNum, String gunNum) {

        return pileNum + gunNum;
    }


    /**
     * 根据充电设备编号获取 桩号
     *
     * @param connectorID
     * @return
     */
    public static String getPileNumByDeviceNo(String connectorID) {

        return connectorID.substring(0, connectorID.length() - 1);
    }


    /**
     * 根据充电设备编号获取 枪号
     *
     * @param connectorID
     * @return
     */
    public static String getGunNumByDeviceNo(String connectorID) {

        return connectorID.substring(connectorID.length() - 1);
    }


    /**
     * 将偶数个Integer类型的参数进行置换 例如：(1,2,3,4) 最后返回 (2,1,4,3)
     * 其实就是将第1个数与第2个数换位置，将3个数与第4个数换位置....
     *
     * @param integers
     * @return
     */
    public static List<Integer> convertIntData(Integer... integers) {
        if (integers.length % 2 != 0) {
            System.err.println("SuperviseUtils:传入参数必须为偶数位");
            return null;
        }
        List<Integer> ints = new ArrayList<>();
        for (Integer s : integers) {
            ints.add(s);
        }

        // 替换数据
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < ints.size(); i++) {
            Integer integer1 = ints.get(i);
            Integer integer2 = ints.get(i + 1);

            integer1 = integer1 ^ integer2;
            integer2 = integer1 ^ integer2;
            integer1 = integer1 ^ integer2;
            re.add(integer1);
            re.add(integer2);
            i++;
        }
        return re;
    }


    /**
     * 快乐充订单号转换成 监管平台所需 订单号
     *
     * @param operatorID,orderNo
     * @return
     */
    public static String convert2StartChargeSeq(String operatorID, String orderNo) {
        if (StringUtils.hasLength(orderNo) && orderNo.length() > 30) {
//            String a = orderNo.substring(0, 1);
//            String b = orderNo.substring(3, 15);
//            String c = orderNo.substring(15);
//
//            String b1 = Long.toString(Long.parseLong(b), 32);
//
//            return "0" + a + b1 + c;

            String a = orderNo.substring(0, 1);
            String b = orderNo.substring(3);

            String c = MathUtils.numToRadix(b, 52);

            return operatorID + a + c;
        }
        return orderNo;
    }


    /**
     * 监管平台订单号 转换成 快乐充 订单号
     *
     * @param startChargeSeq
     * @return
     */
    public static String convert2OrderNo(String startChargeSeq) {
        if (StringUtils.hasLength(startChargeSeq) && startChargeSeq.length() <= 27) {
//            String a = startChargeSeq.substring(1, 2);
//            String b = startChargeSeq.substring(2, 10);
//            String c = startChargeSeq.substring(10);
//
//            String b1 = "20" + Long.valueOf(b, 32).toString();

            String a = startChargeSeq.substring(9, 10);
            String b = startChargeSeq.substring(10);
            String c = "20" + MathUtils.radixToNum(b, 52);

            return a + c;
        }

        return startChargeSeq;
    }


    public static String getStationId(String operatorId, Integer sid) {
        return sid + "";
    }

    public static Integer getSid(String operatorId, String stationId) {
        if (StringUtils.hasLength(stationId) && stationId.contains(operatorId)) {
            String sid = stationId.substring(operatorId.length() - 1);
            if (StringUtils.hasLength(sid)) {
                return Integer.valueOf(sid);
            }
        }
        return Integer.valueOf(stationId);
    }

    public static OperatorInfo convertOperatorInfo(SupersiveInfo supersiveInfo) {
        if (supersiveInfo == null) {
            return null;
        }
        OperatorInfo operatorInfo = new OperatorInfo();
        BeanUtils.copyProperties(supersiveInfo, operatorInfo);
        return operatorInfo;
    }


    /**
     * 根据桩号、枪号、类型生成订单号
     *
     * @param pileNum
     * @param gunNum
     * @param type    0，app启动；1，刷卡启动；2，后台启动订单
     * @return
     */
    public static String getOrderNo(String pileNum, String  gunNum, Integer type) {
        if (type == null || gunNum == null || pileNum == null) {
            return null;
        }
        String s = typ.get(type);
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return s + DateUtils.getNowString(DateUtils.YMDHMS) +
                pileNum +
                gunNum;
    }

    /**
     * 首字母
     */
    private static Map<Integer, String> typ = new HashMap<>();


    static {
        // app启动订单
        typ.put(0, "A");
        // 刷卡启动订单
        typ.put(1, "C");
        // 后台启动订单
        typ.put(2, "W");
        // 中电联协议订单
        typ.put(3, "T");
    }


    public static void main(String[] args) {

//        String startChargeSeq = convert2StartChargeSeq("MA6DFE4R6", "A2019051017161233300000000001231");
//        System.out.println("====================== " + startChargeSeq);
//        System.out.println(startChargeSeq);
//        String orderNo = convert2OrderNo(startChargeSeq);
//        System.out.println(orderNo);
//
//
//        RequestResult reqRes = new RequestResult();
//        reqRes.setData(null);
//        reqRes.setOperatorID("765367656");
//        checkSign(reqRes, "LMqnz4LZMxvofGkm");


        String reqBody = "{\"StartChargeSeq\":\"MA005DBW1\""+DateUtils.getNowString()+",\"ConnectorID\":\"33300000000001251\", \"QRCode\":\"4hrC6kkEOhJYQuNl\"}";
//        String reqBody = "{\"StartChargeSeq\":\"MA6DE1HK0T6zaC6LrbHx4HMibur\",\"ConnectorID\":\"33300000000001251\", \"QRCode\":\"4hrC6kkEOhJYQuNl\"}";
//        String reqBody = "{\"OperatorID\":\"MA005DBW1\",\"OperatorSecret\":\"4hrC6kkEOhJYQuNy\"}";
        RequestResult req = new RequestResult();
        req.setOperatorID("MA005DBW1");
        req.setTimeStamp(DateUtils.getNowString(DateUtils.YMDHMS));
        // 加密参数内容
        byte[] secretData = AESUtils.encrypt(reqBody.getBytes(),
                "AveZrSgwSN1eSlxE".getBytes(), "siaxPfKkgAocqQ10".getBytes());
        req.setData(Base64.getEncoder().encodeToString(secretData));
        req.setSeq("0001");

        req = SuperviseUtils.sign(req, "LMqnz4LZMxvofGkm");
        System.out.println(JsonUtils.toJsonString(req));
    }

}
