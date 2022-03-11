<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 03/03/22
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            session.setAttribute("error", true);
            session.setAttribute("errorMessage", "Invalid password");
            response.sendRedirect("login.jsp");
        }
    } else {
        //error = true;
        session.setAttribute("error", true);
        session.setAttribute("errorMesage", "Password cannot be empty");
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
    <%
        if (session.getAttribute("error") != null){
            if ((boolean)session.getAttribute("error")) {
                //System.out.println(session.getAttribute("<h5>errorMessage</h5>"));
                String errorMessage = (String)session.getAttribute("errorMessage");
                // todo: handle firsttime requests.
                //out.println("<h5 color='red'>"+errorMessage);
                session.setAttribute("error", null);
                session.setAttribute("errorMessage","");

            }
        }
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
