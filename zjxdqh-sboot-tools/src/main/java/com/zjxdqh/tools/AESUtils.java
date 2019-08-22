package com.zjxdqh.tools;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES 加解密
 * @author Yorking
 * @date 2019/05/07
 */
public class AESUtils {



    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    //加密方式
    public static String KEY_ALGORITHM = "AES";
    //数据填充方式
    //避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
    //只在第一次调用decrypt()方法时才new 对象

    public static boolean initialized = false;

    /**
     * AES加密
     * 填充模式AES/CBC/PKCS5Padding
     * @param originalContent   加密内容
     * @param encryptKey        密钥
     * @param ivByte            向量
     * @return
     */
    public static byte[] encrypt(byte[] originalContent, byte[] encryptKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            byte[] encrypted = cipher.doFinal(originalContent);
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     * @param content
     *            目标密文
     * @return
     * @throws Exception
     * @throws InvalidKeyException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] aesKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            Key sKeySpec = new SecretKeySpec(aesKey, "AES");
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错。**/
    public static void initialize() {
        if (initialized) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "{\"operationID\":\"765367611\",\"operatorSecret\":\"4hrC6kkEOhJYQuNl\"}";

        System.out.println("s:" + s);

        byte[] s1 = AESUtils.encrypt(s.getBytes(), "AveZrSgwSN1eSlxE".getBytes(), "siaxPfKkgAocqQ10".getBytes());
        String s3 = Base64.getEncoder().encodeToString(s1);
        System.out.println("s1:" + s3);

        s3 = "frSjl30cmgkYT8O88fXJCWUhSQA8nWychKr7Wv6pNCg9WsDB9HRHZ54ZYC/F3w2IlrCIMwBXgWlwel0kH5FfowYHti3k3esapK2K1jtPAffh78vG0XJV6V4e86Mh05X5D32Do/1z5HxuAkx9JeEB1UIgnSkOWp09Uonuiy6urx5q9HlZmmx8ys45M06JZhRR";
        byte[] s2 = AESUtils.decrypt(Base64.getDecoder().decode(s3), "AveZrSgwSN1eSlxE".getBytes(), "siaxPfKkgAocqQ10".getBytes());
        System.out.println("s2:"+new String(s2, "utf-8"));
    }

}
