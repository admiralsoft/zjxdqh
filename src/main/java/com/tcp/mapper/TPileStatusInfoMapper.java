package com.tcp.mapper;


import com.tcp.core.frame.mybatis.annotation.MapperPrimary;
import com.tcp.core.frame.mybatis.mapper.IBaseMapper;
import com.tcp.bean.TPileStatusInfo;

/**
 * mapper接口,自定义方法写入此接口,并在xml中实现
 * @author code_generator
 */
@MapperPrimary
public interface TPileStatusInfoMapper extends IBaseMapper<TPileStatusInfo>{


    TPileStatusInfo selectLastOnlineTime(String pileNum);
}
