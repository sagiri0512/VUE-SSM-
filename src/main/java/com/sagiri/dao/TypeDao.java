package com.sagiri.dao;

import com.sagiri.mapper.TypeMapper;
import com.sagiri.pojo.Type;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TypeDao {
    static SqlSessionFactory sqlSessionFactory;

    public TypeDao() {
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
    public List<Type> selectAllType(){
        try {
            return sqlSessionFactory.openSession().getMapper(TypeMapper.class).selectAllType();
        } catch (Exception e){
            return null;
        }
    }
}
