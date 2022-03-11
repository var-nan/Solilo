<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 10/03/22
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (session.getAttribute("user") == null) {
    // user is not loggedin, redirect to login page
    response.sendRedirect("login.jsp");
}
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <%
        // get confirmation message from servlet
        if (request.getParameter("success") != null) {
            //out.println("<h5 color='green'>Quicky addedd succesfully</h5>");
        }
    %>

    <form action="quickyservlet" method="post">
        <textarea name="quickyMessage" rows="5" cols="50"></textarea><br/>
        <input type="checkbox" id="visibility" name="visibility" value="private">
        <label for="visibility">Private</label><br>
        <input type="submit" value="Send"> <br/><a href="logout.jsp">Logout</a>
    </form>

</center>
</body>
</html>
