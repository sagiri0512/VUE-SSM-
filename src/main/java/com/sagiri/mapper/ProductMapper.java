package com.sagiri.mapper;

import com.sagiri.pojo.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> selectTopProducts();
    List<Product> selectAllProducts();
    List<Product> selectProductsByTid(int tid);
    List<Product>selectProductsByKeyword(String keyword);
    Product selectProductById(int pid);
    int updatePSalesByPID(Product product);
}
