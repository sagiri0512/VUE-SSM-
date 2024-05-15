package com.sagiri.dao;

import com.sagiri.mapper.PaymentMapper;
import com.sagiri.mapper.ShoppingCartMapper;
import com.sagiri.pojo.Address;
import com.sagiri.pojo.Payment;
import com.sagiri.pojo.ShoppingCart;
import com.sagiri.pojo.ShoppingProduct;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PaymentDao {
    static SqlSessionFactory sqlSessionFactory;
    public PaymentDao(){
        try{
            setup();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setup() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }

    /*查询地址*/
    public List<Address> selectAddress(Address address){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaymentMapper paymentMapper = sqlSession.getMapper(PaymentMapper.class);
        try{
            return paymentMapper.selectAddress(address);
        }catch (Exception e){
            return null;
        }
    }

    public int insertPayment(Payment payment){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaymentMapper paymentMapper = sqlSession.getMapper(PaymentMapper.class);
        try{
            int i = paymentMapper.insertPayment(payment);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }catch (Exception e){
            return 0;
        }
    }

    public void deleteShoppingCart(ShoppingCart shoppingCart){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        try{
            shoppingCartMapper.deleteShoppingCartById(shoppingCart);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void clearAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaymentMapper paymentMapper = sqlSession.getMapper(PaymentMapper.class);
        try{
            paymentMapper.clearAll();
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDid(Address address){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaymentMapper paymentMapper = sqlSession.getMapper(PaymentMapper.class);
        try{
            paymentMapper.setDid(address);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
