package com.ozone.mfls.configer;

import com.ozone.mfls.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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
public class WebConfig {
    @Bean
    FilterRegistrationBean tokenFilter() {
        FilterRegistrationBean filterReg = new FilterRegistrationBean(new TokenFilter());
        //优先级
        filterReg.setOrder(70);
        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");
        filterReg.addUrlPatterns("/*");
        filterReg.addInitParameter("exclusions","/test,/hello");
//        filterReg.setUrlPatterns(urlPatterns);
        System.out.println("====来了");
        return filterReg;
    }
}
