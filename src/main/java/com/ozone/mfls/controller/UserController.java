package com.ozone.mfls.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 22:29
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    /** //权限管理;**/
    @RequiresPermissions("user:view")
    public String userInfo(){
        return "userList";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    /** 权限管理;**/
    @RequiresPermissions("user:add")
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    /** 权限管理;**/
    @RequiresPermissions("user:del")
    public String userDel(){
        return "userDel";
    }
}
