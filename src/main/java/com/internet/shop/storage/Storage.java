package com.internet.shop.storage;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static Long productId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;
    private static Long shoppingCartId = 0L;

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addUser(User user) {
        user.setUserId(++userId);
        users.add(user);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }
}
