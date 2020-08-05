package com.tcp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yaweiXu
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    private static  final Logger logger = LoggerFactory.getLogger(TransactionConfig.class);

    // 其中 dataSource 框架会自动为我们注入
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        logger.info("TransactionManager 初始化");
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager transactionManager) {
        logger.info("TransactionInterceptor 初始化");
        TransactionInterceptor ti = new TransactionInterceptor();
        ti.setTransactionManager(transactionManager);
        Properties properties = new Properties();
        properties.setProperty("*", "PROPAGATION_REQUIRED");

        properties.setProperty("save*", "PROPAGATION_REQUIRED");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
        properties.setProperty("add*", "PROPAGATION_REQUIRED");
        properties.setProperty("create*", "PROPAGATION_REQUIRED");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
        properties.setProperty("remove*", "PROPAGATION_REQUIRED");
        properties.setProperty("update*", "PROPAGATION_REQUIRED");
        properties.setProperty("edit*", "PROPAGATION_REQUIRED");
        properties.setProperty("modify*", "PROPAGATION_REQUIRED");
        properties.setProperty("load*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("is*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("select*", "PROPAGATION_REQUIRED,readOnly");

        ti.setTransactionAttributes(properties);
        return ti;
    }

//    @Bean
//    public BeanNameAutoProxyCreator transactionAutoProxy() {
//        logger.info("TransactionAutoProxy 初始化");
//        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
//        // 使用jdk接口代理，不使用cglib代理
//        transactionAutoProxy.setProxyTargetClass(false);
//        transactionAutoProxy.setBeanNames(new String[] { "*ServiceImpl" });
//        transactionAutoProxy.setInterceptorNames(new String[] { "transactionInterceptor" });
//        return transactionAutoProxy;
//    }
}