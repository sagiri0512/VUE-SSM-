package com.sagiri.mapper;

import com.sagiri.pojo.ShoppingCart;
import com.sagiri.pojo.ShoppingProduct;

import java.util.List;

public interface ShoppingCartMapper {
    int addShoppingCart(ShoppingCart shoppingCart);
    int ShoppingCartSNumAdd(ShoppingCart shoppingCart);
    ShoppingCart selectShoppingCartByPidAndUid(ShoppingCart shoppingCart);
    int selectSumOfSNumByUid (long uid);
    List<ShoppingCart> selectShoppingCartByUid(ShoppingCart shoppingCart);

    void deleteShoppingCartById(ShoppingCart shoppingCart);

    void updateNum(ShoppingCart shoppingCart);
    List<ShoppingCart> selectShoppingCartById(ShoppingCart shoppingCart);
}
