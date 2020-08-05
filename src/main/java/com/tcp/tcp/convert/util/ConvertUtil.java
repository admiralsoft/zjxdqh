package com.tcp.tcp.convert.util;


import com.tcp.tcp.convert.anno.Data;

import java.lang.reflect.Field;
import java.util.*;

public class ConvertUtil {

    /**
     * 缓存类属性
     */
    private static Map<Class<?>, List<Field>> clzzFields = new WeakHashMap<>();

    /**
     * 获取注解属性字段列表
     * @param clazz
     * @return
     */
    public static List<Field> getAnnoFields(Class clazz) {
        List<Field> fields = clzzFields.get(clazz);
        if (fields != null && !fields.isEmpty()) {
            return fields;
        }
        fields = getFields(clazz);
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getDeclaredAnnotation(Data.class) == null) {
                fields.remove(i);
                --i;
            }
        }
        fields.sort((x, y)->{
            Data xAonn = x.getDeclaredAnnotation(Data.class);
            Data yAonn = y.getDeclaredAnnotation(Data.class);
            if (xAonn == null || yAonn == null) return 1;
            return xAonn.order() > yAonn.order()? 1:-1;
        });
        clzzFields.put(clazz, fields);
        return fields;
    }

    private static List<Field> getFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass() ) {
            Field[] fs = clazz.getDeclaredFields();
            if (fs.length > 0)
                fields.addAll(Arrays.asList(fs));
        }
        return fields;
    }


    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing
        return bytes;
    }

    public static byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];

        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }

    public static byte[] toPrimitives(List<Byte> oBytes) {
        byte[] bytes = new byte[oBytes.size()];

        for (int i = 0; i < oBytes.size(); i++) {
            bytes[i] = oBytes.get(i);
        }
        return bytes;
    }
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

}
