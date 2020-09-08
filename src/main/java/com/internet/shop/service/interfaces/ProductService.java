package com.internet.shop.service.interfaces;

import com.internet.shop.model.Product;
import com.internet.shop.service.GenericService;
import java.util.List;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product);

    Product get(Long id);

    List<Product> getAll();

    Product update(Product product);

    boolean delete(Long id);
}
