<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 10/03/22
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // delete session data
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
<html>
<head>
    <title>Logout</title>
</head>
<body>

</body>
</html>
