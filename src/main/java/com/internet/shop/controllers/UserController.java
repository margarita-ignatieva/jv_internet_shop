package com.internet.shop.controllers;

import com.internet.shop.library.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userJoe = new User("Joe", "joe_hann", "meteora");
        User userChes = new User("Chester", "ches_benning", "hybrid");
        User userMike = new User("Mike", "mike_shinoda", "numb");
        userService.create(userJoe);
        userService.create(userChes);
        userService.create(userMike);

        List<User> allUsers = userService.getAll();

        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/views/users/all.jsp").forward(req, resp);

    }
}
