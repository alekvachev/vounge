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
    //allow access only if session exists
    String sess = null;

    if(session.getAttribute("sessAttribute") == null) {
        response.sendRedirect("login.html");
    } else {
        sess = (String) session.getAttribute("sessAttribute");
    }

    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();

    if(cookies !=null ){
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }

            if(cookie.getName().equals("JSESSIONID")) {
                sessionID = cookie.getValue();
            }
        }
    }
%>
    <h3>Hi <%= userName %>, Login successful. Your Session ID=<%= sessionID %></h3><br>
    Session confirmed: <%=sess %><br>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" >
    </form>
</body>
</html>
