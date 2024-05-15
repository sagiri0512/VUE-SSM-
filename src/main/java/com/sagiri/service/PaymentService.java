package com.sagiri.service;

import com.sagiri.dao.PaymentDao;
import com.sagiri.pojo.Address;
import com.sagiri.pojo.Payment;
import com.sagiri.pojo.ShoppingCart;

import java.util.List;

public class PaymentService {
    public PaymentDao paymentDao;
    // 构造函数
    public PaymentService() {paymentDao = new PaymentDao();}
    public List<Address> selectAddress(Address address){return paymentDao.selectAddress(address);}
    public int insertPayment(Payment payment){return paymentDao.insertPayment(payment);}
    public void deleteShoppingCart(ShoppingCart shoppingCart){paymentDao.deleteShoppingCart(shoppingCart);}

    public void clearAll(){paymentDao.clearAll();}
    public void setDid(Address address){paymentDao.setDid(address);}
}
