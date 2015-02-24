package com.vounge.server;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alek on 2/23/2015.
 */

@WebServlet(
        description = "Login Servlet",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "jamsterdam"),
                @WebInitParam(name = "password", value = "edri4kii")
        })
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        //we can create DB connection resource here and set it to Servlet context
        if(getServletContext().getInitParameter("dbURL").equals("jdbc:postgres://localhost/db") &&
                getServletContext().getInitParameter("dbUser").equals("postgres") &&
                getServletContext().getInitParameter("dbUserPwd").equals("postgres")) {
            getServletContext().setAttribute("DB_Success", "True");
        }
        else throw new ServletException("DB Connection error");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        //logging example
        log("User="+user+"::password="+pwd);

        if(userID.equals(user) && password.equals(pwd)) {
            response.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);

        }

    }
}
