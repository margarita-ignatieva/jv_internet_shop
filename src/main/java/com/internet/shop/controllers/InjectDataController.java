package com.internet.shop.controllers;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userJoe = new User("Joe", "joe_hann", "meteora");
        userJoe.setRoles(List.of(Role.of("USER")));
        User userChes = new User("Chester", "ches_benning", "hybrid");
        userChes.setRoles(List.of(Role.of("USER")));
        User admin = new User("Admin", "lp", "admin");
        admin.setRoles(List.of(Role.of("ADMIN")));
        userService.create(userJoe);
        userService.create(userChes);
        userService.create(admin);

        req.getRequestDispatcher("/WEB-INF/views/injectUsers.jsp").forward(req, resp);
    }
}
