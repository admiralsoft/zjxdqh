package com.zjxdqh.evcs.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjxdqh.evcs.supervise.vo.TokenResultVO;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @author Yorking
 * @date 2019/05/08
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

    }

    public static <T> T parse(String  value, Class<T> clazz) {
        try {
            return objectMapper.readValue(value.getBytes(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJsonString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        TokenResultVO rvo = new TokenResultVO();
        rvo.setAccessToken("1111");
        String s = toJsonString(rvo);
        System.out.println(s);
        System.out.println(parse(s, TokenResultVO.class));
    }



}
