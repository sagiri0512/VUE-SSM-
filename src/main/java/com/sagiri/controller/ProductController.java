package com.sagiri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.Product;
import com.sagiri.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @RequestMapping(value = "/getTopProduct", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProduct() throws IOException{
        ProductService productService = new ProductService();
        List<Product> productList = productService.getTopProduct();

        ObjectMapper mapper = new ObjectMapper();
        String product = mapper.writeValueAsString(productList);

        return product;
    }

    @RequestMapping(value = "/getProduct", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllProduct() throws IOException{
        ProductService productService = new ProductService();
        List<Product> productList = productService.getAllProduct();

        ObjectMapper mapper = new ObjectMapper();
        String product = mapper.writeValueAsString(productList);

        return product;
    }

    @RequestMapping(value = "/getProductByTid", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProductByTid(HttpServletRequest req) throws IOException{
        ProductService productService = new ProductService();

        int tid = Integer.parseInt(req.getParameter("tid"));

        List<Product> productList = productService.getProductById(tid);

        ObjectMapper mapper = new ObjectMapper();
        String product = mapper.writeValueAsString(productList);

        return product;
    }
    @RequestMapping(value = "/getProductByKeyword", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProductByKeyword(HttpServletRequest req) throws IOException{
        String keyword = req.getParameter("keyword");


        ProductService productService = new ProductService();

        List<Product> productList = productService.selectProductsByKeyword(keyword);

        ObjectMapper mapper = new ObjectMapper();
        String product = mapper.writeValueAsString(productList);

        return product;
    }

    @RequestMapping(value = "/getProductByPid", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProductByPid(HttpServletRequest req) throws IOException{
        ProductService productService = new ProductService();

        int pid = Integer.parseInt(req.getParameter("pid"));

        Product product = productService.getProductByPId(pid);

        ObjectMapper mapper = new ObjectMapper();
        String product1 = mapper.writeValueAsString(product);

        return product1;
    }
}
