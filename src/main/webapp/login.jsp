<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 03/03/22
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // create a new session and set error messages

    // logic: create session and set errors according to that
    //boolean error = false;
    //if (request.getParameter("error") != null)
    // check if session started or not

    if (request.getParameter("login-password") != null) {

        String passcode = request.getParameter("login-password");
        if (passcode.equals("hello")) {
            session.setAttribute("user", true);

            response.sendRedirect("QuickyForm.jsp");
        } else {
            //error = true;
            session.setAttribute("incorrectPassword", true);
            session.setAttribute("errorMessage", "Invalid password");
            response.sendRedirect("login.jsp");
        }
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
<div id="formdiv">

    <form method="post" action="login.jsp" >
        <c:if test="${incorrectPassword}">
            <h5>wrong</h5>
            <c:out value="Hmm... Incorrect Password"/>
            <c:remove var="incorrectPassword"/>
        </c:if>

        <h5>Please log in..</h5> <br/>
        Passcode: <input type="password" name="login-password" required>
        <button type="submit" value="OK">GO</button>
    </form>
</div>
</center>
</body>
</html>
