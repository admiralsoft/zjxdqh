package com.zjxdqh.tools.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@Component("springContextHolder")
@Log4j2
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;

        log.info("SpringContextHolder.applicationContext  上下文 注入成功");
    }


    private static ApplicationContext getApplicationContext(){
        checkApplicationContext();
        return applicationContext;
    }

    private static void checkApplicationContext(){
        if (applicationContext == null) {
            throw new NullPointerException("applicationContext 尚未 注册， 还无法使用");
        }
    }

    /**
     * 外部调用这个getBean方法就可以手动获取到bean
     * 用bean组件的name来获取bean
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T>T getBean(String beanName){
        return (T) getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
