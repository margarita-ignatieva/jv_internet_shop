package com.internet.shop.controllers.order;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.OrderService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.online.store");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/order/userOrders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        orderService.completeOrder(shoppingCartService.getByUserId(USER_ID));
        resp.sendRedirect(req.getContextPath() + "/order/userOrders");
    }
}
