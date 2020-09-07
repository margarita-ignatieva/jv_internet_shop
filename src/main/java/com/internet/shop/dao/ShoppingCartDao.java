package com.internet.shop.dao;

import com.internet.shop.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);
}
