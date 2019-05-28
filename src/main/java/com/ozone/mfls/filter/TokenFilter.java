package com.ozone.mfls.filter;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName TokenFilter
 * @Author Ozone
 * @Description TODO
 * @Date 2019/5/27 16:56
 * @Version 1.0
 **/
public class TokenFilter implements Filter {
    public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    private Set<String> excludesPattern;
    protected String contextPath;
    protected PatternMatcher pathMatcher = new ServletPathMatcher();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String param = filterConfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (param != null && param.trim().length() != 0) {
            this.excludesPattern = new HashSet(Arrays.asList(param.split("\\s*,\\s*")));
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        TokenFilter.StatHttpServletResponseWrapper responseWrapper = new TokenFilter.StatHttpServletResponseWrapper(httpResponse);
        String requestURI = this.getRequestURI(httpRequest);
        if (this.isExclusion(requestURI)) {
            //不过滤走
            System.out.println("===不进过滤器");
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //这里是过滤方法
            System.out.println("===进了过滤器");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    public boolean isExclusion(String requestURI) {
        if (this.excludesPattern == null) {
            return false;
        } else {
            if (this.contextPath != null && requestURI.startsWith(this.contextPath)) {
                requestURI = requestURI.substring(this.contextPath.length());
                if (!requestURI.startsWith("/")) {
                    requestURI = "/" + requestURI;
                }
            }

            Iterator i$ = this.excludesPattern.iterator();

            String pattern;
            do {
                if (!i$.hasNext()) {
                    return false;
                }

                pattern = (String)i$.next();
            } while(!this.pathMatcher.matches(pattern, requestURI));

            return true;
        }
    }

    public static final class StatHttpServletResponseWrapper extends HttpServletResponseWrapper implements HttpServletResponse {
        private int status = 200;

        public StatHttpServletResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public void setStatus(int statusCode) {
            super.setStatus(statusCode);
            this.status = statusCode;
        }

        public void setStatus(int statusCode, String statusMessage) {
            super.setStatus(statusCode, statusMessage);
            this.status = statusCode;
        }

        @Override
        public void sendError(int statusCode, String statusMessage) throws IOException {
            super.sendError(statusCode, statusMessage);
            this.status = statusCode;
        }

        @Override
        public void sendError(int statusCode) throws IOException {
            super.sendError(statusCode);
            this.status = statusCode;
        }

        @Override
        public int getStatus() {
            return this.status;
        }
    }

    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
