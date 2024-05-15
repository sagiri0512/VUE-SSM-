package com.sagiri.mapper;

import com.sagiri.pojo.Address;
import com.sagiri.pojo.Payment;
import com.sagiri.pojo.ShoppingCart;
import com.sagiri.pojo.ShoppingProduct;

import java.util.List;

public interface PaymentMapper {
    List<Address> selectAddress(Address address);
    int insertPayment(Payment payment);
    void deleteShoppingCart(ShoppingCart shoppingCart);
    void clearAll();
    void setDid(Address address);
}
