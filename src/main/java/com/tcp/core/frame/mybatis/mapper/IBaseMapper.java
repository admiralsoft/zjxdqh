package com.tcp.core.frame.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 * @author ChenYaokun
 * @param <T>
 */
public interface IBaseMapper<T> extends Mapper<T>, MySqlMapper<T>, MyMapper<T>  {
	//TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
	
	/**
	 * 通过map作为参数查询出list集合 满足一定条件可分页;
	 * 分页条件:
	 * 		params的map中含有分页插件指定的分页参数即可分页
	 * 分页示例:
	 * 		如果使用Page对象中的params,则此params会封装此分页参数,即可完成分页功能
	 * @param params
	 * @return
	 */
	public List<T> getList(Map<String, Object> params);
	
	
}
