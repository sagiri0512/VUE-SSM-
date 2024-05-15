package com.sagiri.dao;

import com.sagiri.mapper.DetailMapper;
import com.sagiri.mapper.ProductMapper;
import com.sagiri.pojo.Detail;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DetailDao {
    static SqlSessionFactory sqlSessionFactory;

    public DetailDao() {
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

    public List<Detail> getDetailByPID(int pid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DetailMapper mapper = sqlSession.getMapper(DetailMapper.class);
        try {
            return mapper.selectDetailByPID(pid);
        } catch (Exception e) {
            return null;
        }
    }

}
