package com.tcp.tcp.convert.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Data {

    /**
     * 字段所处位置
     * @return
     */
    int order() default 0;

    /**
     * 报文所需字节长度
     * @return
     */
    int byteLen() default 1;
    /**
     * 报文所需字节长度
     * @return
     */
    String  byteLenAttr() default "";

    /**
     * 字节转换类型
     * @return
     */
    DataByteTypEnum byteType();

}
