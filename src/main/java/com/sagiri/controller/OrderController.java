package com.sagiri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagiri.pojo.Order;
import com.sagiri.pojo.Payment;
import com.sagiri.service.OrderService;
import com.sagiri.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    //查询所有信息
    @RequestMapping(value = "/getAllOrder", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectAll(HttpServletRequest req){
        String uname = req.getParameter("uname");
        UserService userService = new UserService();
        long uid = userService.getUser(uname).getUid();
        OrderService orderService = new OrderService();
        Order order =new Order();
        order.setUid(uid);
        List<Order> list = orderService.selectAll(order);
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            return null;
        }
    }

    //确认收货
    @RequestMapping(value = "/updateState", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void updateState(HttpServletRequest req){
        Integer paid =Integer.parseInt(req.getParameter("paid"));
        Order order = new Order();
        order.setPaid(paid);
        OrderService orderService = new OrderService();
        try{
            orderService.updateState(order);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
