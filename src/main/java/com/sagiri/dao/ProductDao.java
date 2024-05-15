package com.sagiri.dao;

import com.sagiri.mapper.CarouselMapper;
import com.sagiri.mapper.ProductMapper;
import com.sagiri.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductDao {
    static SqlSessionFactory sqlSessionFactory;

    public ProductDao() {
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

    public List<Product> getTopProduct() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            return productMapper.selectTopProducts();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> getAllProduct() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            return productMapper.selectAllProducts();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Product> getProductByTid(int pid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            return productMapper.selectProductsByTid(pid);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Product> selectProductsByKeyword(String keyword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            return productMapper.selectProductsByKeyword(keyword);
        } catch (Exception e) {
            return null;
        }
    }
    public Product getProductByPId(int pid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            return productMapper.selectProductById(pid);
        } catch (Exception e) {
            return null;
        }
    }
    public int updatePSalesByPID(Product product) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            int i = productMapper.updatePSalesByPID(product);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }catch (Exception e) {
            return 0;
        }
    }
    public int updatePInventoryByPID(Product product){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        try {
            int i = productMapper.updatePInventoryByPID(product);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }catch (Exception e){
            return 0;
        }
    }
}
