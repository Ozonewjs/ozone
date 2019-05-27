package com.ozone.mfls.server.imp;

import com.ozone.mfls.beans.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 21:39
 * @Version 1.0
 **/
public interface UserService {
    User findByUserName(String userName);
}
