package com.sagiri.service;

import com.sagiri.dao.UserDao;
import com.sagiri.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User getUser (String uName) {
        return  userDao.getUser(uName);
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }
    public User getUserInfo(String uName){
        return userDao.getUserInfo(uName);
    }
    public int phoneChange(User user) {
        return userDao.phoneChange(user);
    }
    public int  emailChange(User user){
        return userDao.emailChange(user);
    }
    public int pwdChange(User user){
        return userDao.pwdChange(user);
    }
    public int updateUserNickNameByUname (User user){
        return userDao.updateUserNickNameByUname(user);
    }
    public int updateUserAgeByUName(User user){
        return userDao.updateUserAgeByUName(user);
    }
    public int updateUserSexByUName(User user){
        return userDao.updateUserSexByUName(user);
    }
    public int updateUserHeadByUID(User user){
        return userDao.updateUserHeadByUID(user);
    }
}
