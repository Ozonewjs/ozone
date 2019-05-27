package com.ozone.mfls.server;

import com.ozone.mfls.beans.User;
import com.ozone.mfls.server.imp.UserRepository;
import com.ozone.mfls.server.imp.UserService;

import javax.annotation.Resource;
/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 21:48
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
