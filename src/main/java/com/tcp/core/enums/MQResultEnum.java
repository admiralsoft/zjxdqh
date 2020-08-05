package com.tcp.core.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yaweiXu
 */
public enum MQResultEnum {

    MQ_RESULT_SUCCESS("执行成功"),
    MQ_RESULT_FAIL("执行失败");


    public String desc;

    private MQResultEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static MQResultEnum getEnum(String name) {
        MQResultEnum[] arry = MQResultEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equals(name)) {
                return arry[i];
            }
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List toList() {
        MQResultEnum[] ary = MQResultEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }
}
