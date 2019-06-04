package com.ozone.mfls.filter;


import com.ozone.mfls.annotation.UserAuthenticate;
import com.ozone.mfls.beans.HeaderCons;
import com.ozone.mfls.myexception.FastRuntimeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.method.HandlerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @ClassName TokenFilter
 * @Author Ozone
 * @Description 拦截器
 * @Date 2019/5/27 16:56
 * @Version 1.0
 **/
public class TokenFilter extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        UserAuthenticate userAuthenticate = method.getAnnotation(UserAuthenticate.class);
        //如果没有加注解则userAuthenticate为null
        if (Objects.nonNull(userAuthenticate)) {
            Long userId= getUserId(request);
            //userAuthenticate.permission()取出permission判断是否需要校验权限
            boolean checkres = userId == null || (userAuthenticate.permission() && !checkAuth(userId,request.getRequestURI()));
            if (checkres){
                throw new FastRuntimeException(20001,"No access");
            }
        }
        return true;
    }

    /**
     * 根据token获取用户ID
     * @param request
     * @return
     */
    private Long getUserId(HttpServletRequest request){
        //添加业务逻辑根据token获取用户UserId
        request.getHeader("H-User-Token");
        Long userId = 1L;
        String userMobile = "18888888888";
        request.setAttribute(HeaderCons.USER_ID,userId);
        request.setAttribute(HeaderCons.USER_MOBILE,userMobile);
        return userId;
    }

    /**
     * 校验用户访问权限
     * @param userId
     * @param requestURI
     * @return
     */
    private boolean checkAuth(Long userId,String requestURI){
        //添加业务逻辑根据UserId获取用户的权限组然后校验访问权限
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}

}
