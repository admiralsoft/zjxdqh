package com.tcp.core.frame.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

/**
 * 自定义的mapper实现
 * @author ChenYaokun
 *
 * @param <T>
 */
public interface MyMapper<T> {

	/**
	 * 通过map作为参数查询一条记录
	 * @param params
	 * @return
	 */
	@SelectProvider(type = MyProvider.class, method = "dynamicSQL")
	public T getOne(Map<String, Object> params);
	
	/**
	 * 通过主键更新对象,主键参数不可以不存在和为空,只更新不为null的数据
	 * 返回更新到的条数
	 * @param params
	 */
	@UpdateProvider(type = MyProvider.class, method = "dynamicSQL")
	public int updtByPk(Map<String, Object> params);
	
	/**
	 * 通过主键更新对象,主键参数不可以不存在和为空,为null的也会更新
	 * 返回更新到的条数
	 * @param params
	 */
	@UpdateProvider(type = MyProvider.class, method = "dynamicSQL")
	public int updtAllByPk(Map<String, Object> params);
	
	
	/**
	 * 批量插入对象，主键参数必须存在且不为空，包含主键一起插入，为null的也会插入
	 * 返回插入的条数
	 * @param params
	 */
	@InsertProvider(type = MyProvider.class, method = "dynamicSQL")
	public int insertListIncludeId(List<T> list);
	
}
