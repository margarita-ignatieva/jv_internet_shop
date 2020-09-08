package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import com.internet.shop.storage.Storage;
import java.util.List;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        final ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);
        final ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        final UserService userService = (UserService) injector.getInstance(UserService.class);
        final OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        Product banana = new Product("banana", 10.1);
        Product orange = new Product("orange", 4.1);
        Product lemon = new Product("lemon", 6.3);

        productService.create(banana);
        productService.create(orange);
        productService.create(lemon);

        System.out.println("Before changes");
        Storage.products.forEach(System.out::println);
        lemon.setName("plump");
        lemon.setPrice(3);
        productService.update(lemon);

        System.out.println("After changes");
        productService.getAll().forEach(System.out::println);

        System.out.println(productService.get(banana.getId()));

        productService.delete(orange.getId());
        productService.getAll().forEach(System.out::println);

        System.out.println("Before changes: user list");

        User userJoe = new User("Joe", "joe_hann", "meteora");
        User userChes = new User("Chester", "ches_benning", "hybrid");
        User userMike = new User("Mike", "mike_shinoda", "numb");
        userService.create(userJoe);
        userService.create(userChes);
        userService.create(userMike);

        userService.getAll().forEach(System.out::println);

        User userFeniks =
                new User("Feniks", "feniks_smith", "guitar");
        userFeniks.setUserId(userMike.getUserId());
        userService.update(userFeniks);
        userService.getAll().forEach(System.out::println);

        userService.delete(userChes.getUserId());
        userService.getAll().forEach(System.out::println);

        ShoppingCart shoppingCartUserFeniks = new ShoppingCart(userFeniks.getUserId());
        ShoppingCart shoppingCartUserJoe = new ShoppingCart(userJoe.getUserId());
        shoppingCartService.create(shoppingCartUserFeniks);
        shoppingCartService.create(shoppingCartUserJoe);

        System.out.println("Add Product to Cart:");
        shoppingCartService.addProduct(shoppingCartUserFeniks, lemon);
        shoppingCartService.addProduct(shoppingCartUserFeniks, lemon);
        shoppingCartService.addProduct(shoppingCartUserFeniks, banana);
        shoppingCartService.addProduct(shoppingCartUserJoe, orange);
        shoppingCartService.addProduct(shoppingCartUserJoe, banana);
        shoppingCartService.addProduct(shoppingCartUserJoe, orange);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Delete banana from first Cart:");
        shoppingCartService.deleteProduct(shoppingCartUserFeniks, banana);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Get User Feniks ID");
        System.out.println(shoppingCartService.getByUserId(userFeniks.getUserId()));

        System.out.println("Delete first Cart by ID");
        shoppingCartService.deleteCart(shoppingCartUserFeniks);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("New Cart added");
        ShoppingCart shoppingCartUserMike = new ShoppingCart(userMike.getUserId());
        shoppingCartService.create(shoppingCartUserMike);
        shoppingCartService.addProduct(shoppingCartUserMike, lemon);
        shoppingCartService.addProduct(shoppingCartUserMike, lemon);
        shoppingCartService.addProduct(shoppingCartUserMike, banana);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Clear all products in Mikes's Cart");
        shoppingCartService.clear(shoppingCartUserMike);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Complete orders for User Feniks and Joe");
        final Order firstOrder = orderService.completeOrder(shoppingCartUserFeniks);
        final Order secondOrder = orderService.completeOrder(shoppingCartUserJoe);
        Storage.orders.forEach(System.out::println);
        Storage.shoppingCarts.forEach(System.out::println);

        System.out.println("Get User Joe orders");
        System.out.println(orderService.getUsersOrders(userJoe.getUserId()));

        System.out.println("Get All orders");
        orderService.getAll();
        Storage.orders.forEach(System.out::println);
        List<Product> products = productService.getAll();

        System.out.println("Delete first order");
        orderService.delete(firstOrder.getId());
        Storage.orders.forEach(System.out::println);
    }
}
