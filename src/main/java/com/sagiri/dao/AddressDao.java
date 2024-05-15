package com.sagiri.dao;

import com.sagiri.mapper.AddressMapper;
import com.sagiri.mapper.CarouselMapper;
import com.sagiri.pojo.Address;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddressDao {
    static SqlSessionFactory sqlSessionFactory;

    public AddressDao() {
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
    public List<Address> getAddressByUid(long uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AddressMapper addressMapper =   sqlSession.getMapper(AddressMapper.class);
        try {
            return addressMapper.selectAddressByUid(uid);
        } catch (Exception e) {
            return null;
        }
    }
    public int updateByAid(Address address){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AddressMapper addressMapper =   sqlSession.getMapper(AddressMapper.class);
        try {
            int i =  addressMapper.updateByAid(address);
            sqlSession.commit();
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int addAddress(Address address){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AddressMapper addressMapper =   sqlSession.getMapper(AddressMapper.class);
        try {
            int i =  addressMapper.addAddress(address);
            sqlSession.commit();
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
    public int deleteByAid(long aid){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AddressMapper addressMapper =   sqlSession.getMapper(AddressMapper.class);
        try {
            int i =  addressMapper.deleteByAid(aid);
            sqlSession.commit();
            sqlSession.close();
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
}
