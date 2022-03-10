<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 10/03/22
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logging....</title>
</head>
<body>

<%
    boolean error = false;
    String passcode = request.getParameter("login-password");
    if (passcode.equals("hello")){
        // redirect to quickyform
        session.setAttribute("user",true);
        response.sendRedirect("QuickyForm.jsp");
    } else {
        error = true;
        response.sendRedirect("login.jsp");
    }
%>

</body>
</html>
