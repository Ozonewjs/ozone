package com.ozone.mfls.interceptor;

import com.ozone.mfls.annotation.UserMobile;
import com.ozone.mfls.beans.HeaderCons;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserMobileMethodArgumentResolver
 * @Author Ozone
 * @Description TODO
 * @Date 2019/5/31 15:36
 * @Version 1.0
 **/
public class UserMobileMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserMobile.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return servletRequest.getAttribute(HeaderCons.USER_MOBILE);
    }

}
