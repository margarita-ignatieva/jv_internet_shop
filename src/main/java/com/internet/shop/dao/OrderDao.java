package com.internet.shop.dao;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    List<Order> getUserOrders(Long userId);

    boolean delete(Long id);
}
