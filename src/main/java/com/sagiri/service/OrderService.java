package com.sagiri.service;

import com.sagiri.dao.OrderDao;
import com.sagiri.pojo.Order;

import java.util.List;

public class OrderService {
    public OrderDao orderDao;
    public OrderService(){orderDao=new OrderDao();}
    public List<Order> selectAll(Order order){return orderDao.selectAll(order);}
    public void updateState(Order order){orderDao.updateState(order);}
}
