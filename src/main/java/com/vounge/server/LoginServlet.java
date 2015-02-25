package com.vounge.server;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alek on 2/23/2015.
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final String userID = "jamsterdam";
    private final String password = "edri4kii";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if(userID.equals(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("sessAttribute", "sess");
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            //Get the encoded URL string
            String encodedURL = response.encodeRedirectURL("LoginSuccess.jsp");
            response.sendRedirect(encodedURL);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }
    }
}
