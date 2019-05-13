package com.zjxdqh.tools.annon;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 设置JSON 转换时， 小数位数
 *
 * @author Yorking
 * @date 2019/05/13
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberScale {

    /**
     * 精确小数位数（默认：2位小数）
     * @return
     */
    int value() default 2;
}
