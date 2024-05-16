package com.sagiri.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sagiri.pojo.*;
import com.sagiri.service.PaymentService;
import com.sagiri.service.ProductService;
import com.sagiri.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Controller
public class PaymentController {
    @RequestMapping(value = "/selectAddress", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectAddress(HttpServletRequest req){
        String uname = req.getParameter("uname");
        Integer did =Integer.parseInt(req.getParameter("did"));
        Address address=new Address();
        UserService userService=new UserService();
        Long uid = userService.getUser(uname).getUid();
        address.setUid(uid);
        address.setDid(did);
        PaymentService paymentService=new PaymentService();
        List<Address> addresses = paymentService.selectAddress(address);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(addresses);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    class Payload{
        int pid;
        int aid;
        float pPice;
        long sNum;
        String uname;
    }
    @RequestMapping(value = "/addPayment", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addPayment(HttpServletRequest req) throws JsonProcessingException {
        StringBuilder buffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String data = buffer.toString();

        // 使用JSON库解析数据
        Gson gson = new Gson();
        Payload payload = gson.fromJson(data, Payload.class);

        ProductService productService=new ProductService();
        Product product1 = productService.getProductByPId(payload.pid);
        // 判断库存是否充足
        if(product1.getPInventory() == 0){
            PFailed pFailed = new PFailed(product1.getPName(), 0);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(pFailed);
        }
        Boolean isSuccess = payload.sNum > product1.getPInventory();

        if(isSuccess){
            payload.sNum = product1.getPInventory();
        }

        UserService userService=new UserService();
        User user = userService.getUser(payload.uname);

        double payPrice = payload.pPice*payload.sNum;

        Payment payment=new Payment();
        payment.setPid(payload.pid);
        payment.setAid(payload.aid);
        payment.setUid(user.getUid());
        payment.setPayPrice(payPrice);
        payment.setNumber(payload.sNum);

        Product product = new Product();
        product.setPid(payload.pid);
        product.setPSales(payload.sNum);
        product.setPInventory(payload.sNum);

        productService.updatePSalesByPID(product);
        productService.updatePInventoryByPID(product);

        PaymentService paymentService=new PaymentService();
        paymentService.insertPayment(payment);

        if (isSuccess){
            PFailed pFailed = new PFailed(product1.getPName(), payload.sNum);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(pFailed);
        }else{
            return "success";
        }
    }

    @RequestMapping(value = "/deleteShoppingCart",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void deleteShoppingCart(HttpServletRequest req){
        String uname = req.getParameter("uname");
        Integer pid =Integer.parseInt(req.getParameter("pid"));
        UserService userService=new UserService();
        Long uid = userService.getUser(uname).getUid();
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUid(uid);
        shoppingCart.setPid(pid);
        PaymentService paymentService=new PaymentService();
        try{
            paymentService.deleteShoppingCart(shoppingCart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //清空did
    @RequestMapping(value = "/clearAll",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void clearAll(HttpServletRequest req){
        PaymentService paymentService=new PaymentService();
        try{
            paymentService.clearAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据aid设置did
    @RequestMapping(value = "/setDid",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void setDid(HttpServletRequest req){
        Integer aid =Integer.parseInt(req.getParameter("aid"));
        Address address=new Address();
        address.setAid(aid);
        PaymentService paymentService=new PaymentService();
        try{
            paymentService.setDid(address);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

