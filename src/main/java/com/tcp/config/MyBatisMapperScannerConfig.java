
package com.tcp.config;

import com.tcp.core.frame.mybatis.mapper.IBaseMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，如果你不使用通用Mapper，可以改为org.xxx...
 *
 * @author yaweiXu
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)// 注意，由于MapperScannerConfigurer执行的比较早，所以必须指定在mybatis 配置后面
public class MyBatisMapperScannerConfig {

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.tcp.dao,com.tcp.mapper"); //多个包用英文逗号分隔
        Properties properties = new Properties();
        properties.setProperty("mappers", IBaseMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "select replace(uuid(),'-','') from dual");
        properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
