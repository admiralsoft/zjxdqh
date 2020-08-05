package com.tcp.config;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MyBatisConfig {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        try {
            sessionFactory.setDataSource(dataSource);
            //sessionFactory.setConfigLocation(new InputStreamResource(this.getClass().getResourceAsStream("/mybatis-config.xml")));
            //分页插件
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            properties.setProperty("params", "count=countSql");
            pageHelper.setProperties(properties);


            Interceptor[] interceptor = {pageHelper, offsetLimitInterceptor()};
            sessionFactory.setPlugins(interceptor);
            //
//            sqlSessionFactory = sessionFactory.getObject();

//            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//            configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
//            configuration.setDefaultExecutorType(ExecutorType.BATCH); //批量操作
//            configuration.setUseColumnLabel(true);//使用列别名替换列名 select user as User
//            configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段   userId    user_id
//            configuration.setLocalCacheScope(LocalCacheScope.SESSION);
//            configuration.setLazyLoadingEnabled(true);
//            configuration.setAggressiveLazyLoading(false);
//            Set<String> methodNames = new HashSet() {{
//                add("equals");
//                add("clone");
//                add("hashCode");
//                add("toString");
//            }};
//            configuration.setLazyLoadTriggerMethods(methodNames);
            //configuration.setLogImpl(Logger.class);
//            sessionFactory.setConfiguration(configuration);

            //添加XML目录
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        sessionFactory.setMapperLocations(resolver.getResources("classpath*:com/tcp/mapper/*.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory.getObject();
    }

    @Bean(destroyMethod="clearCache")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        logger.debug("---------> Set SqlSessionTemplate");
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public OffsetLimitInterceptor offsetLimitInterceptor() throws Exception {
        OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
        offsetLimitInterceptor.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
        return offsetLimitInterceptor;
    }


}
