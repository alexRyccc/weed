package com.weed.loginfo.annotion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: com.weed.loginfo.exception
 * @date 2020/12/9 16:20
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    /**
     * 列索引
     *
     * @return
     */
    int columnIndex() default 0;

    /**
     * 列名
     *
     * @return
     */
    String columnName() default "";
}
