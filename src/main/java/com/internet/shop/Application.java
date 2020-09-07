package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;

import java.util.List;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        Product banana = new Product("banana", 10.1);
        Product orange = new Product("orange", 4.1);
        Product lemon = new Product("lemon", 6.3);

        productService.create(banana);
        productService.create(orange);
        productService.create(lemon);

        User user1 = new User("Joe", "jor_hann", "meteora");
        User user2 = new User("Chester", "tim_benning", "hybrid");
        User user3 = new User("Mike", "mike_shinoda", "numb");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);

        ShoppingCart shoppingCartUser1 = new ShoppingCart(user1.getUserId());
        ShoppingCart shoppingCartUser2 = new ShoppingCart(user2.getUserId());
        ShoppingCart shoppingCartUser3 = new ShoppingCart(user3.getUserId());
        ShoppingCart shoppingCartUser2new = new ShoppingCart(user2.getUserId());
        shoppingCartService.create(shoppingCartUser1);
        shoppingCartService.create(shoppingCartUser3);
        shoppingCartService.create(shoppingCartUser2);
        shoppingCartService.create(shoppingCartUser2new);

        System.out.println("Add Product to Cart:");
        shoppingCartService.addProduct(shoppingCartUser1, playStation);
        shoppingCartService.addProduct(shoppingCartUser1, xbox);
        shoppingCartService.addProduct(shoppingCartUser1, nintendo);
        shoppingCartService.addProduct(shoppingCartUser2, tetris);
        shoppingCartService.addProduct(shoppingCartUser2, sega);
        shoppingCartService.addProduct(shoppingCartUser3, sega);
        shoppingCartService.addProduct(shoppingCartUser3, dendy);
        shoppingCartService.addProduct(shoppingCartUser3, subor);
        shoppingCartService.addProduct(shoppingCartUser2new, playStation);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Delete xbox from Cart#1:");
        shoppingCartService.deleteProduct(shoppingCartUser1, xbox);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("get ID by User#3");
        System.out.println(shoppingCartService.getByUserId(user3.getId()));

        System.out.println("delete Cart ID #2");
        shoppingCartService.delete(shoppingCartUser3);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Clear all product by Cart#1");
        shoppingCartService.clear(shoppingCartUser1);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("complete order User#2");
        orderService.completeOrder(shoppingCartUser2);
        orderService.completeOrder(shoppingCartUser2new);
        Storage.orders.forEach(System.out::println);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("get User#2 orders");
        System.out.println(orderService.getUserOrders(user2.getId()));

        System.out.println("get User#2 order by ID");
        System.out.println(orderService.get(user2.getId()));

        System.out.println("get All orders");
        orderService.getAll();
        Storage.orders.forEach(System.out::println);
        List<Product> products = productService.getAll();

        System.out.println("del order by ID");
        orderService.delete(user2.getId());
        Storage.orders.forEach(System.out::println);

        System.out.println("Before changes");
        products.forEach(System.out::println);
        lemon.setName("plump");
        lemon.setPrice(3);
        productService.update(lemon);

        System.out.println("After changes");
        productService.getAll().forEach(System.out::println);

        System.out.println(productService.get(banana.getId()));

        productService.delete(orange.getId());
        productService.getAll().forEach(System.out::println);

    }
}
