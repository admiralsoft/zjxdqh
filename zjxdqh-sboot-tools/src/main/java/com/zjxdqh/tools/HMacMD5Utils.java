package com.zjxdqh.tools;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC-MD5 参数签名
 *
 * @author Yorking
 * @date 2019/05/07
 */
public class HMacMD5Utils {

    public static final String KEY_MAC = "HmacMD5";

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    /**byte数组转换为HexString*/
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }
    public static String encryptHMAC2String(String  data, String key)throws Exception {
        byte[] b = encryptHMAC(data.getBytes("UTF-8"), key);
        return byteArrayToHexString(b);
    }

    public static void main(String[] args) throws Exception {
        String s = "Query token successjZMqBQHzsnuFNLsmEXxS6aR4fV9dEHqUyg2ToFnZm95+vKu18D4OSwsE4WJ6rcUue/x7yDg+qfMFwm7CCU7uO6hR23vufQFbKYzA3ymrbFIE47fIc6h+XEAvLkjamYD9x7Vq785PUAVGphiC1/U4t7qny8QWpunulRTzOqueN7tkQF4nmht9ZOHfOvfdJmNH";
        String s1 = HMacMD5Utils.encryptHMAC2String("0" + s, "LMqnz4LZMxvofGkm");
        System.out.println(s1.toUpperCase());
    }
}
