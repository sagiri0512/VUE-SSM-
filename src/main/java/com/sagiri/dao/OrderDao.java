package com.sagiri.dao;

import com.sagiri.mapper.OrderMapper;
import com.sagiri.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderDao {
    static SqlSessionFactory sqlSessionFactory;
    public OrderDao()
    {
        try{
            setup();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void setup() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<Order> selectAll(Order order){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        OrderMapper orderMapper=sqlSession.getMapper(OrderMapper.class);
        try{
            return orderMapper.selectAll(order);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void updateState(Order order){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        OrderMapper orderMapper=sqlSession.getMapper(OrderMapper.class);
        try{
            orderMapper.updateState(order);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
