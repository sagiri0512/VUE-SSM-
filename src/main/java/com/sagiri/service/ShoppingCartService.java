package com.sagiri.service;

import com.sagiri.dao.ProductDao;
import com.sagiri.dao.ShoppingCartDao;
import com.sagiri.pojo.ShoppingCart;

import java.util.List;

public class ShoppingCartService {
    public ShoppingCartDao shoppingCartDao;
    public ShoppingCartService() {
        shoppingCartDao = new ShoppingCartDao();
    }
    public int addShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartDao.addShoppingCart(shoppingCart);
    }
    public int getSumOfSNumByUid(long uid) {
        return shoppingCartDao.getSumOfSNumByUid(uid);
    }
    public List<ShoppingCart> selectShoppingCartByUid(ShoppingCart shoppingCart) {return shoppingCartDao.selectShoppingCartByUidAndPid(shoppingCart);}

    public void deleteShoppingCartById(ShoppingCart shoppingCart) {shoppingCartDao.deleteShoppingCartById(shoppingCart);}

    public void updateNum(ShoppingCart shoppingCart) {shoppingCartDao.updateNum(shoppingCart);}
    public List<ShoppingCart> selectShoppingCartById(ShoppingCart shoppingCart) {return shoppingCartDao.selectShoppingCartById(shoppingCart);}
}
