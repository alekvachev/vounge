<%--
  User: Alek
  Date: 2/23/2015
  Time: 9:45 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success Page</title>
</head>
<body>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();

    if(cookies != null) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("user")) {
                userName = cookie.getValue();
                break;
            }
        }
    }

    if(userName == null) {
        response.sendRedirect("login.html");
    }
%>
    <h3>Hi <%= userName %>, Login successful.</h3><br>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
