package com.zjxdqh.tools.lock;

import java.util.HashMap;
import java.util.Map;

public class ThreadVariable {
	private static final ThreadLocal<Map<String,Object>> container = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * 获取当前线程中变量中的键为key的值
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		Map<String, Object> map = container.get();
		if(map==null) return null;
		return container.get().get(key);
	}
	
	/**
	 * 向当前线程变量中设置一个值
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value){
		Map<String, Object> map = container.get();
		if(map==null){
			container.set(new HashMap<String,Object>(0));
		}
		container.get().put(key, value);
	}
	
	/**
	 * 移除当前线程变量中的某个key值对应的value
	 * @param key
	 * @return
	 */
	public static Object remove(String key){
		Map<String, Object> map = container.get();
		if(map==null) return null;
		return container.get().remove(key);
	}
	
	/**
	 * 移除当前线程的变量
	 */
	public static void removeAll(){
		Map<String, Object> map = container.get();
		if(map!=null) map.clear();
		container.remove();
	}

	
}
