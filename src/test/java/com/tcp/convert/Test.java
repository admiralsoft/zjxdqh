package com.tcp.convert;

import com.tcp.core.code.MQCode;
import com.tcp.core.enums.MQCodeEnum;
import com.tcp.tcp.convert.util.ConvertUtil;
import com.tcp.util.DataUtil;
import com.tcp.util.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {


    public static void main(String[] args) {
        Byte[] b = {20, 17, 1, 18};
        System.out.println(b[0]);
        System.out.println(b[1]);
        System.out.println(b[0]+""+b[1]);
        String url = "www.b/";
        System.out.println(Arrays.asList(ConvertUtil.toObjects(url.getBytes())));

        System.out.println(DateUtil.parse("2017-1-1", DateUtil.FORMAT_SHORT));


        MQCodeEnum codeEnum = MQCodeEnum.getCodeEnum(115);
        System.out.println(codeEnum.getToQuene().getToname());


        int i = 0x0b;
        System.out.println(i);

        BigDecimal b1 = new BigDecimal(0.383d).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal(0.382d).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal sum = b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(sum.doubleValue());

        String s = "01.203.13";
        System.out.println("--------------------------------");

        System.out.println(s);
        byte[] bytes = DataUtil.StringToAsciiByte(s, 4);

        System.out.println(DataUtil.byteAsciiToString(bytes));

        BigDecimal ele = new BigDecimal(0.5);
        BigDecimal ele_price = new BigDecimal(0.8);
        BigDecimal server_price = new BigDecimal(0.8);

        ele_price=ele_price.multiply(ele).setScale(4,BigDecimal.ROUND_HALF_UP);
//        server_price=server_price.multiply(ele.multiply(new BigDecimal(0.5))).setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal totalPrice = ele_price.add(server_price).setScale(2, BigDecimal.ROUND_UP);

        System.out.println("=========================");
        System.out.println(totalPrice);
        System.out.println(ele_price);
        System.out.println(totalPrice.subtract(ele_price).setScale(2,BigDecimal.ROUND_HALF_UP));

        System.out.println("==================================");

        String ip = "/100.117.1.0:10528".replaceFirst("/", "");
        System.out.println(ip);

        boolean matches = ip.matches("100\\.117\\.[0-9]{1,3}\\..*");
        System.out.println("match  " + matches);

        System.out.println("====================================");
        Long aLong = DataUtil.fourByteToLong(new byte[]{(byte) 0x5b, (byte) 0x9e, (byte) 0xc5, (byte) 0xad});
        System.out.println(aLong);

        System.out.println(DateFormatUtils.format(aLong*1000, "YYYY-MM-dd HH:mm:ss"));


        System.out.println("====================================");


        Double d1 = 50.0d;
        String  s2 =  String.valueOf(d1);
        System.out.println(s2);


    }

}
