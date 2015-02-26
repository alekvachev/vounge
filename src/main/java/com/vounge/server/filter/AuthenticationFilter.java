package com.vounge.server.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alek on 2/25/2015.
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String uri = httpReq.getRequestURI();
        HttpSession session = httpReq.getSession(false);

        //reject access to direct LoginServlet requests or requests to non-html resources before session is created
        if(session == null && !(uri.endsWith("html") || uri.endsWith("LoginServlet"))) {
            System.out.println("Unauthorized. Redirecting to login page");
            httpResp.sendRedirect("login.html");
        } else {
            // pass the request down the filter chain
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
