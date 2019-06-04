package com.ozone.mfls.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface UserAuthenticate {
    /**
     * @Author Ozone
     * @Description 是否需要校验访问权限 默认不校验
     * @Date 2019/5/31 15:21
     * @Param []
     * @return boolean
     **/
    boolean permission() default false;
}
