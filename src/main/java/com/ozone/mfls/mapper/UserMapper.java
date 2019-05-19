package com.ozone.mfls.mapper;

import com.ozone.mfls.beans.SA_USERS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    List<SA_USERS> getAll();
    SA_USERS getUser(@Param("userid") String userid);
    int insertUser(@Param("sausers") SA_USERS sausers);
}
