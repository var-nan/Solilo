<%@ page import="java.util.List" %>
<%@ page import="main.solilo.bean.Quicky" %>
<%@ page import="main.solilo.service.QuickyService" %>
<%@ page import="java.util.ArrayList" %>
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
    <title>Quicky</title>
    <link rel="stylesheet" type="text/css" href="stylesheets/style.css">
</head>
<body>
<center>
<div id="allquickies" style="position: center">
    <%
        // all quickies in session variable
        ArrayList<Quicky> allQuickies;
        if (session.getAttribute("allQuickies") == null) {
            // connect to database and get quickies
            allQuickies = QuickyService.getMessages(10);
            session.setAttribute("allQuickies", allQuickies);
        }
        allQuickies = (ArrayList<Quicky>) session.getAttribute("allQuickies");
    %>
    <table id="quickiestable">
        <tr>
            <th>Time</th> <th>Quicky</th>
        </tr>
        <%
            for (Quicky q: allQuickies) {
                String message = q.getMessage();
                String qtime = q.getCreated();
        %>
            <tr> <td><%=qtime%></td> <td><%=message%></td> </tr>
            <%
            }
            %>

    </table>
</div>
<div id="quickyform" style="position:center">

    <h5 id="successmessage">
    <%
        // display confirmation message, if successful.
        if (session.getAttribute("success") != null) {
            out.println("<h5 style='color:green'>Quicky addedd succesfully</h5>");
            // remove successs message
            session.setAttribute("success", null);
        }
    %>
    </h5>

    <form action="quickyservlet" method="post" class="quickyform">
        <textarea id="bigtextarea" name="quickyMessage" rows="6" cols="70" placeholder="type something..." required></textarea>
        <br/>
        <input type="checkbox" id="visibility" name="visibility" value="private">
        <label for="visibility">Private</label><br/>
        <button id="submitbutton" class="buttons" type="submit" value="Send">Send</button>
        <button id="resetbutton" class="buttons" type="reset" value="Reset">Reset</button>
        <br/><br>
    </form>
    <a href="logout.jsp"><button>Logout</button></a>

</div>
</center>
</body>
</html>
