package com.internet.shop.web.filters;

import com.internet.shop.model.Role;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorisationFilter implements Filter {
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


    }

    @Override
    public void destroy() {

    }
}
