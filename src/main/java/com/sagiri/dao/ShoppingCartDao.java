package com.sagiri.dao;

import com.sagiri.mapper.ShoppingCartMapper;
import com.sagiri.pojo.ShoppingCart;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class    ShoppingCartDao {
    static SqlSessionFactory sqlSessionFactory;

    public ShoppingCartDao() {
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

    public int addShoppingCart(ShoppingCart shoppingCart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        if (selectShoppingCartByPidAndUid(shoppingCart)){
            return ShoppingCartSNumAdd(shoppingCart);
        }
        try {
            int i = shoppingCartMapper.addShoppingCart(shoppingCart);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public int ShoppingCartSNumAdd(ShoppingCart shoppingCart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            int i = shoppingCartMapper.ShoppingCartSNumAdd(shoppingCart);
            sqlSession.commit();  // 提交事务
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public Boolean selectShoppingCartByPidAndUid(ShoppingCart shoppingCart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            ShoppingCart shoppingCart1 = shoppingCartMapper.selectShoppingCartByPidAndUid(shoppingCart);
            return shoppingCart1 != null;
        } catch (Exception e){
            return false;
        }
    }
    public int getSumOfSNumByUid (long uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            return shoppingCartMapper.selectSumOfSNumByUid(uid);
        } catch (Exception e){
            return 0;
        }
    }

    public List<ShoppingCart> selectShoppingCartByUidAndPid(ShoppingCart shoppingCart){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            return shoppingCartMapper.selectShoppingCartByUid(shoppingCart);
        }catch (Exception e){
            return null;
        }
    }

    public void deleteShoppingCartById(ShoppingCart shoppingCart) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            shoppingCartMapper.deleteShoppingCartById(shoppingCart);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            sqlSession.rollback();
        }
    }

    public void updateNum(ShoppingCart shoppingCart){
        SqlSession sqlSession= sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try {
            shoppingCartMapper.updateNum(shoppingCart);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            sqlSession.rollback();
        }
    }

    public List<ShoppingCart> selectShoppingCartById(ShoppingCart shoppingCart){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try{
            return shoppingCartMapper.selectShoppingCartById(shoppingCart);
        }catch (Exception e){
            return null;
        }
    }
}
