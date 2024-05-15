package com.sagiri.dao;

import com.sagiri.mapper.CarouselMapper;
import com.sagiri.pojo.Carousel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class CarouselDao {
    static SqlSessionFactory sqlSessionFactory;

    public CarouselDao() {
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

    public List<Carousel> getAllCCarousel() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CarouselMapper carouselMapper = sqlSession.getMapper(CarouselMapper.class);
        try {
            return carouselMapper.selectAllCarousel();
        } catch (Exception e) {
            return null;
        }
    }
}
