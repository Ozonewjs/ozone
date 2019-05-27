package com.ozone.mfls.controller;

import com.ozone.mfls.beans.LoginResult;
import com.ozone.mfls.server.imp.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 22:24
 * @Version 1.0
 **/
@Controller
public class HomeController {
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return"/index";
    }

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/user/403";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map,HttpServletRequest request)
    {
        loginService.logout();
        return "/user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Map<String, Object> map, HttpServletRequest request) {
        System.out.println("login()");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        LoginResult loginResult = loginService.login(userName,password);
        if(loginResult.isLogin())
        {
            return "/index";
        }
        else {
            map.put("msg",loginResult.getResult());
            map.put("userName",userName);
            return "/user/login";
        }
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        loginService.logout();
        return "/user/login";
    }

}
