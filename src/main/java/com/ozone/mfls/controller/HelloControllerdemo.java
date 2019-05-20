package com.ozone.mfls.controller;

import com.ozone.mfls.beans.RespBean;
import com.ozone.mfls.beans.SA_USERS;
import com.ozone.mfls.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
public class HelloControllerdemo {
    private Logger logger = Logger.getLogger(HelloControllerdemo.class);
    @Autowired
    UserServer userServer;
    /**
     * @Author Ozone
     * @Description 查询所有用户
     * @Date 22:24 2019/5/20 0020
     * @Param []
     * @return java.util.List<com.ozone.mfls.beans.SA_USERS>
     **/
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public List<SA_USERS> Hello(){
        List<SA_USERS> saUsers = userServer.getAll();
        if (saUsers != null && saUsers.size()>0) {
            return saUsers;
        }else{
            return null;
        }
    }
    @RequestMapping(value = "/hello/{userid}",method = RequestMethod.GET)
    public RespBean Hello(@PathVariable String userid){
        SA_USERS saUsers = userServer.getUser(userid);
        logger.info("userid="+userid);
        if (saUsers != null ) {
            return RespBean.ok("ok",saUsers);
        }else{
            return RespBean.error("error");
        }
    }
    @RequestMapping(value = "/insertuser",method = RequestMethod.POST)
    public RespBean insertUser(SA_USERS sausers){
        int insertres = userServer.insertUser(sausers);
        if(insertres == 1){
            return RespBean.ok("insert ok");
        }else {
            return RespBean.error("insert fail");
        }
    }

}
