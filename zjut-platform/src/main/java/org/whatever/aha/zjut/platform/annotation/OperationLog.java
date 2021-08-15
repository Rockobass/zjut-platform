package org.whatever.aha.zjut.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解记录日志
 *
 * @author cygao
 * @date 2021/5/26
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 方法所属模块名称
     */
    String module() default "未指定模块";

    /**
     * 调用方法名
     */
    String method() default "未指定方法名";

    /**
     * 隐藏参数(不打印参数,为了保护隐私或节省空间)
     */
    boolean ignoreParameters() default false;
}
