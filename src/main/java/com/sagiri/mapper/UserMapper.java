package com.sagiri.mapper;

import com.sagiri.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUserByUName(String uName);
    int addUser(@Param("uName") String uName, @Param("upwd") String upwd);
    User getUserInfo(String uName);
    int phoneChange(User user);
    int emailChange(User user);
    int pwdChange(User user);
    int updateUserNickNameByUname(User user);
    int updateUserAgeByUName(User user);
    int updateUserSexByUName(User user);

    int updateUserHeadByUID(User user);
}
