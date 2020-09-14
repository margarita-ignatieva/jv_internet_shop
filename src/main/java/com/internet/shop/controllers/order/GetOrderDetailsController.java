package com.internet.shop.controllers.order;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("productId"));
        List<Product> products = orderService.get(id).getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/order/details.jsp").forward(req, resp);
    }
}
