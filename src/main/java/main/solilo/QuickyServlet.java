package main.solilo;

import main.solilo.bean.Quicky;
import main.solilo.service.QuickyService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="QuickyServlet", value="/quickyservlet")
public class QuickyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if user is not logged in , redirect to login page
        HttpSession curSession = request.getSession();
        if (curSession.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //System.out.println("doPost method is called here");
        log("entered doPost method");
        HttpSession curSession = request.getSession();

        if (request.getParameter("quickyMessage") == null) {
            // add error and redirect to same page
            curSession.setAttribute("error", true);
            curSession.setAttribute("errorMessage", "Some message should be entered");
            response.sendRedirect("QuickyForm.jsp");
        }

        String quickyMessage = request.getParameter("quickyMessage"); // todo perform validation
        quickyMessage = performSanitize(quickyMessage);

        boolean isVisible = true;
        if (request.getParameter("visibiltity") != null) {
            isVisible = !(request.getParameter("visibility").equals("private"));
        }
        // call service layer methods
        log("reading parameters, isvisible: "+isVisible);

        try {
            QuickyService.addMessage(quickyMessage, isVisible);
            log("Quicky added succesfully");
        } catch (Exception exp){
            exp.printStackTrace();
        }

        // send confirmation
        curSession.setAttribute("success", true);

        // store all the quickies in session variable and update automatically
        ArrayList<Quicky> allQuickies = QuickyService.getMessages(10);
        curSession.setAttribute("allQuickies", allQuickies);

        log("redirecting to quickyform");
        response.sendRedirect("QuickyForm.jsp");
    }

    private String performSanitize(String str) {
        return str.trim();
    }
}
