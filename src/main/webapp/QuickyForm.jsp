<%--
  Created by IntelliJ IDEA.
  User: nandhan
  Date: 10/03/22
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="main.solilo.bean.Quicky" %>
<%@ page import="main.solilo.service.QuickyService" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("user") == null) {
        // user is not logged, redirect to login page
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
        // get all current day's quickies if session variable is null.
        if (session.getAttribute("todayQuickies") == null) {
            // get current day's quickies and add to session variable
            session.setAttribute("todayQuickies", QuickyService.getTodayMessages());
            session.setAttribute("todaySentiment", QuickyService.getTodaySentiment());
        }
        List <Quicky> todayQuickies = (List<Quicky>) session.getAttribute("todayQuickies");
    %>

    <c:set var="nquickies" value="${todayQuickies.size()}" scope="session"> </c:set>
    <c:choose>
        <c:when test="${nquickies>0}">
            <!-- print table -->
            <table id="quickiestable">
                <tr> <th>Time</th> <th>Quicky</th> </tr>
                <c:forEach var="quicky" items="${todayQuickies}">
                    <tr>
                        <td><fmt:parseDate value="${quicky.created}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
                            <fmt:formatDate value="${dateObject}" pattern="MMM dd, HH:mm" /></td>
                        <td>${quicky.message}</td>
                    </tr>
                </c:forEach>
            </table>

            <div id="sentimentdiv">
                <h4> Current Mood:
                <c:set value="${todaySentiment}" var="sentimentValue" scope="page" />
                <c:choose>
                    <c:when test="${sentimentValue==1}">
                        <h4 style="color:indianred">Sad</h4>
                    </c:when>
                    <c:when test="${sentimentValue==2}">
                        <h4>Neutral</h4>
                    </c:when>
                    <c:when test="${sentimentValue==3}">
                        <h4 style="color: darkslateblue">Happy</h4>
                    </c:when>
                    <c:when test="${sentimentValue==4}">
                        <h4 style="color: forestgreen">Amazing</h4>
                    </c:when>
                </c:choose>
                </h4>
            </div>

        </c:when>

        <c:otherwise>
            <h3>Nothing here but Crickets ¯\_(ツ)_/¯</h3>
        </c:otherwise>
    </c:choose>

</div>

<div id="quickyform" style="position:center">

    <h5 id="successmessage">
    <%
        // display confirmation message, if successful.
        if (session.getAttribute("success") != null) {
            if ((boolean)session.getAttribute("success"))
                out.println("<h5 style='color:green'>Quicky addedd succesfully</h5>");
            else out.println("<h5 style='color:red'>Quicky not added</h5>");
            // remove successs message
            session.setAttribute("success", null);
        }
    %>
    </h5>

    <form action="quickyservlet" method="post" class="quickyform">
        <label for="bigtextarea"></label>
        <textarea id="bigtextarea" name="quickyMessage" rows="6" cols="70" placeholder="type something..." required></textarea>
        <br/>
        <input type="checkbox" id="visibility" name="visibility" value="private">
        <label for="visibility">Private</label><br/>
        <button id="submitbutton" class="buttons" type="submit" value="Send">Post</button>
        <button id="resetbutton" class="buttons" type="reset" value="Reset">Reset</button>
        <br/><br>
    </form>
    <a href="logout.jsp"><button>Logout</button></a> <a href="index.jsp"><button>Home Page</button></a>

</div>
</center>
</body>
</html>
