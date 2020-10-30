package com.weed.loginfo.annotion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Alexryc
 * @Date 2020/10/30/030 14:02
 * @Version 1.0
 * 类字段检查
 */
@Target(java.lang.annotation.ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckField {

    /**
     * 字段最小长度
     */
    int minlen() default 0;

    /**
     * 字段最大长度
     */
    int maxlen() default Short.MAX_VALUE;

    /**
     * 字段是否需要校验
     */
    Check check() default Check.unchecks;

    /**
     * 字段名（用于后期获取）
     */
    String name() default "";

    /**
     * 字段类型
     */
    Check type() default Check.basics;

}
