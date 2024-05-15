package com.sagiri.service;

import com.sagiri.dao.ProductDao;
import com.sagiri.pojo.Product;

import java.util.List;

public class ProductService {
    public ProductDao productDao;
    public ProductService() {
        productDao = new ProductDao();
    }
    public List<Product> getTopProduct() {
        return productDao.getTopProduct();
    }
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
    public List<Product> getProductById(int pid) {
        return productDao.getProductByTid(pid);
    }
    public List<Product> selectProductsByKeyword(String keyword) {
        return productDao.selectProductsByKeyword(keyword);
    }
    public Product getProductByPId(int pid) {
        return productDao.getProductByPId(pid);
    }
    public int updatePSalesByPID(Product product) {
        return productDao.updatePSalesByPID(product);
    }
}
