package com.ozone.mfls.server;

import com.ozone.mfls.beans.SA_USERS;
import com.ozone.mfls.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServer {

    private UserMapper userMapper;
    @Autowired
    public UserServer(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<SA_USERS> getAll(){
        return userMapper.getAll();
    }
    public SA_USERS getUser(String userid){
        return userMapper.getUser(userid);
    }
    public SA_USERS logIn(String userid,String password){
        return userMapper.getLoginUser(userid,password);
    }
    public int insertUser(SA_USERS sausers){
        return userMapper.insertUser(sausers);
    }
}
