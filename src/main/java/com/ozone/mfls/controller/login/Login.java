package com.ozone.mfls.controller.login;

import com.ozone.mfls.beans.RespBean;
import com.ozone.mfls.beans.SA_USERS;
import com.ozone.mfls.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Login
 * @Author Ozone
 * @Description 用户登录
 * @Date 2019/6/6 9:50
 * @Version 1.0
 **/
@RestController
public class Login {

    UserServer userServer;
    @Autowired
    public Login(UserServer userServer) {
        this.userServer = userServer;
    }

    @RequestMapping(value = "/login/login",method = RequestMethod.POST)
    public RespBean Login(String username,String password){
        SA_USERS sa_users = userServer.logIn(username,password);
        if (sa_users!=null){
            return RespBean.error("用户名或密码错误");
        }else{
            return RespBean.error("登录成功",sa_users);
        }

    }
}
