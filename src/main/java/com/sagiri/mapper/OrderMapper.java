package com.sagiri.mapper;

import com.sagiri.pojo.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> selectAll(Order order);
    void updateState(Order order);
}
