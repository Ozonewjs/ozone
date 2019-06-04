package com.ozone.mfls.configer;

import com.ozone.mfls.filter.TokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfig
 * @Author Ozone
 * @Description TODO
 * @Date 2019/5/27 16:55
 * @Version 1.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(new TokenFilter()).addPathPatterns("/**");
    }

}
