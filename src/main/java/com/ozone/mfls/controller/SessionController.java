package com.ozone.mfls.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName SessionController
 * @Author Ozone
 * @Description redis管理session
 * @Date 2019/6/4 9:19
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/session")
public class SessionController {
    @Value("${server.port}")
    Integer port ;
    @GetMapping(value = "/set")
    public String set(HttpSession session){
        session.setAttribute("user","javaboy");
        return  String.valueOf(port);
    }
    @GetMapping(value = "/get")
    public String get(HttpSession session){
        return session.getAttribute("user")+ ":" +port;
    }
}
