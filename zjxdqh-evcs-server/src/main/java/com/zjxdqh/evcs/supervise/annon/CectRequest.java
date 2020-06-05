package com.zjxdqh.evcs.supervise.annon;

import java.lang.annotation.*;

/**
 *
 * 中电联推送接口 注释
 *
 * 添加 此注释， 可根据 桩关联的 第三方 对接平台，同时推送信息
 * @author Yorking
 * @date 2019/08/15
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CectRequest {


    /**
     * 对接 监管平台
     * @return
     */
    boolean supervise() default true;

    /**
     * 包含 监管平台 ID
     * @return
     */
    String[] includeSupervise() default {};

    /**
     * 排除 监管平台 ID
     * @return
     */
    String[] excludeSupervise() default {};

    /**
     * 对接第三方平台
     * @return
     */
    boolean dock() default false;

}
