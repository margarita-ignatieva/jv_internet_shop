package com.internet.shop.controllers.cart;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToCartController extends HttpServlet {
    private static final long USER_ID = 1;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        String productId = req.getParameter("productId");
        Product currentProduct = productService.get(Long.valueOf(productId));
        shoppingCartService.addProduct(shoppingCart, currentProduct);
        resp.sendRedirect(req.getContextPath() + "/product/all");
    }
}
