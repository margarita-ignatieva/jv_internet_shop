package com.internet.shop.dao;

import com.internet.shop.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUserId(Long userId);

    boolean delete(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);
}
