<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 03/03/22
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //boolean error = false;
    boolean error = false;
    //if (request.getParameter("error") != null)
    if (request.getParameter("login-password") != null) {

        String passcode = request.getParameter("login-password");
        if (passcode.equals("hello")) {
            session.setAttribute("user", true);
            response.sendRedirect("QuickyForm.jsp");
        } else {
            error = true;
            response.sendRedirect("login.jsp?error=true");
        }
    } else {
        error = true;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html">
    <title>Login</title>
</head>
<body>
<center>
<div>
    <% /*
        if (error){
            out.println("<h4>Incorrect password</h4>");
        }
        */
    %>
    <form method="post" action="login.jsp" >
        <h5>Please log in..</h5> <br/>
        passphrase: <input type="password" name="login-password"><br/>
        <input type="submit" value="OK">
    </form>
</div>
</center>
</body>
</html>
