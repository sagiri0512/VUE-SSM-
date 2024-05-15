package com.sagiri.dao;

import com.sagiri.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sagiri.pojo.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDao {
    static SqlSessionFactory sqlSessionFactory;

    //UserDao运行执行setUp()方法
    public UserDao() {
        try {
            setUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUp() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public User getUser(String uName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            return userMapper.selectUserByUName(uName);
        } catch (Exception e) {
            return null;
        }
    }
    public int addUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        if (getUser(user.getUName()) != null){
            return 0;
        }
        try {
            int i =  userMapper.addUser(user.getUName(), user.getUpwd());
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return -1;
        }
    }
    public User getUserInfo(String uName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try{
            return userMapper.getUserInfo(uName);
        } catch (Exception e) {
            return null;
        }
    }
    public int phoneChange(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.phoneChange(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int emailChange(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.emailChange(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int pwdChange(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.pwdChange(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int updateUserNickNameByUname (User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.updateUserNickNameByUname(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateUserAgeByUName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.updateUserAgeByUName(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int updateUserSexByUName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            int i = userMapper.updateUserSexByUName(user);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
}

