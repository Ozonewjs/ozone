package com.ozone.mfls.mapper;

import com.ozone.mfls.beans.SA_USERS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @ClassName UserMapper
 * @Author Ozone
 * @Description 用户处理
 * @Date 2019/6/6 9:50
 * @Version 1.0
 * */
@Mapper
@Repository
public interface UserMapper {
    /**
     * @Author Ozone
     * @Description 查询所有用户
     * @Date 2019/6/6 10:01
     * @Param []
     * @return java.util.List<com.ozone.mfls.beans.SA_USERS>
     **/
    List<SA_USERS> getAll();
    /**
     * @Author Ozone
     * @Description 根据userid查询用户
     * @Date 2019/6/6 10:01
     * @Param [userid]
     * @return com.ozone.mfls.beans.SA_USERS
     **/
    SA_USERS getUser(@Param("userid") String userid);
    /**
     * @Author Ozone
     * @Description 用户登录
     * @Date 2019/6/6 10:02
     * @Param [userid, password]
     * @return com.ozone.mfls.beans.SA_USERS
     **/
    SA_USERS getLoginUser(@Param("userid") String userid,@Param("password") String password);
    /**
     *  新增用户
     * @Author Ozone
     * @Description 新增用户
     * @Date 2019/6/6 10:02
     * @Param [sausers]
     * @return int
     **/
    int insertUser(@Param("sausers") SA_USERS sausers);
}
