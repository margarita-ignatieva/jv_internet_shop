package com.internet.shop.web.filters;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/order/complete", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/product/delete", List.of(Role.RoleName.USER));
        protectedUrls.put("/user/orders", List.of(Role.RoleName.USER));
        protectedUrls.put("/orders/delete", List.of(Role.RoleName.USER));
        protectedUrls.put("/order/details", List.of(Role.RoleName.USER));
        protectedUrls.put("shopping-cart/products/delete", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products/add", List.of(Role.RoleName.USER));
        protectedUrls.put("/products/add", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/manage", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/admin/details", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders", List.of(Role.RoleName.ADMIN));

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestedUrl = req.getServletPath();

        if (protectedUrls.get(requestedUrl) == null) {
            filterChain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        User user = userService.get(userId);

        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/user/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole: user.getRoles()) {
                if (authorizedRole.equals((userRole.getRoleName()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
