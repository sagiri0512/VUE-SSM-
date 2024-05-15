package com.sagiri;

import com.sagiri.dao.UserDao;
import com.sagiri.pojo.Carousel;
import com.sagiri.pojo.Product;
import com.sagiri.pojo.User;
import com.sagiri.service.CarouselService;
import com.sagiri.service.ProductService;
import com.sagiri.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args)
    {
        ProductService productService = new ProductService();
        List<Product> products = productService.selectProductsByKeyword("果");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
