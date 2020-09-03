package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.util.List;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService)
                injector.getInstance(ProductService.class);
        Product banana = new Product("banana", 10.1);
        Product orange = new Product("orange", 4.1);
        Product lemon = new Product("lemon", 6.3);

        productService.create(banana);
        productService.create(orange);
        productService.create(lemon);
        List<Product> products = productService.getAll();

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
