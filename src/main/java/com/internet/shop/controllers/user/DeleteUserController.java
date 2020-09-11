package com.internet.shop.controllers.user;

import com.internet.shop.library.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        userService.delete(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        shoppingCartService.delete(shoppingCart.getId());
        resp.sendRedirect(req.getContextPath() + "/user/all");
    }
}
