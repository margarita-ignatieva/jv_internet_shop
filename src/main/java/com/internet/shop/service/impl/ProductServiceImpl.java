package com.internet.shop.service.impl;

import com.internet.shop.dao.interfaces.ProductDao;
import com.internet.shop.library.Inject;
import com.internet.shop.library.Service;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
