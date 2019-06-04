package com.ozone.mfls.configer;

import com.ozone.mfls.annotation.UserId;
import com.ozone.mfls.annotation.UserMobile;
import com.ozone.mfls.interceptor.UserIdMethodArgumentResolver;
import com.ozone.mfls.interceptor.UserMobileMethodArgumentResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;


/**
 * @ClassName FilterAutoConfiguration
 * @Author Ozone
 * @Description 拦截所有添加了我们自定义的注解的方法，并将userId和userMobile放入HttpServletRequest，
 *              之后通过对应的注解取值
 * @Date 2019/5/31 15:37
 * @Version 1.0
 **/
@Configuration
public class FilterAutoConfiguration {
    @Configuration
    @ConditionalOnWebApplication
    /** 多个用逗号隔开**/
    @ConditionalOnClass({UserId.class, UserMobile.class})
    protected static class ArgumentResolverAutoConfiguration implements WebMvcConfigurer  {
        protected ArgumentResolverAutoConfiguration() {
        }
        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            //可添加多个
            argumentResolvers.add(new UserIdMethodArgumentResolver());
            argumentResolvers.add(new UserMobileMethodArgumentResolver());
        }
    }
}
