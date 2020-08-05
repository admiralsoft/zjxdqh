package com.tcp.util;

/**
 * @Author yaweiXu
 */
public class AddData {

    //b 开始位置
    //a ++次数
    public static byte[] appendNext(byte[] data, int a,int b) {
        b=b+32;
        byte[] bytes = new byte[a];
        for (int i = b; i < a; i++) {
            bytes[i]=data[i];
        }
        return bytes;
    }
}
