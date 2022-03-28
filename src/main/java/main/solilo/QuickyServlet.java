package main.solilo;

import main.solilo.bean.Quicky;
import main.solilo.service.QuickyService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name="QuickyServlet", value="/quickyservlet")
public class QuickyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // if user is not logged in , redirect to login page
        HttpSession curSession = request.getSession();
        if (curSession.getAttribute("user") == null) {
            log("QuickyForm is accessed without the user logged in. Redirecting to login page");
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException{

        HttpSession curSession = request.getSession();

        if (request.getParameter("quickyMessage") == null) {
            // add error and redirect to same page
            log ("Null message is entered. show error");
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

        //  add quicky to database
        boolean success;

        try {
            success = QuickyService.addMessage(quickyMessage, isVisible);
            log("Quicky added to database");
        } catch (Exception exp){
            success = false;
            log("quicky not added to database");
            exp.printStackTrace();
        }

        // set success flag in session.
        curSession.setAttribute("success", success);

        // store all the quickies in session variable and update automatically
        List<Quicky> allQuickies = QuickyService.getTodayMessages();
        curSession.setAttribute("todayQuickies", allQuickies);
        curSession.setAttribute("todaySentiment",QuickyService.getTodaySentiment());

        log("Redirecting to quickyform after getting latest quickies");
        response.sendRedirect("QuickyForm.jsp");
    }

    private String performSanitize(String str) {
        return str.trim();
    }
}
