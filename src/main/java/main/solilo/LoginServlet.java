package main.solilo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if called for nothing, redirect to login page
        response.sendRedirect("login.jsp"); // TODO check name of login page

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // read password and authenticate
        String passcode = request.getParameter("passcode");

        if (passcode.equals("honey")) {
            // user is logged in and create session
            HttpSession session = request.getSession();
            session.setAttribute("user",true);
            response.sendRedirect("QuickyForm.jsp");
        } else {
            // send error and redirect to login page
            response.sendRedirect("login.jsp");
        }

    }
}
