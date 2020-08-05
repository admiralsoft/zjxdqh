package com.tcp.util;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TcT
 * Date: 2018/8/15.
 * Time: 上午11:48.
 * @Title:
 * @Description:
 */
public final class DownPackDataUtill {

   // public final static Map<String, LinkedList<Map<String,byte[]>>> downData = new ConcurrentHashMap<>();
    public final static Map<String, HashMap<String, byte[]>> downData = new ConcurrentHashMap<>(0);

    public final static Map<String,Map<String, Integer>> faltMark = new ConcurrentHashMap<>(0);
}
