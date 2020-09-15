package com.internet.shop.controllers.product;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.ProductService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllProductsController extends HttpServlet {
    public static final Injector injector = Injector.getInstance("com.internet.shop");
    private ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/product/userAll.jsp").forward(req, resp);
    }
}
