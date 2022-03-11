package main.solilo;

import main.solilo.service.QuickyService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        String quickyMessage = request.getParameter("quickyMessage"); // todo perform validation
        boolean isValid = false;
        if (request.getParameter("visibiltity") != null) {
            isValid = (boolean) request.getParameter("visibility").equals("private");
        }
        // call service layer methods
        log("reading parameters");
        try {
            QuickyService.addMessage(quickyMessage, isValid);
            log("Quicky added succesfully");
        } catch (Exception exp){
            exp.printStackTrace();
        }

        // send confirmation
        HttpSession curSession = request.getSession();
        curSession.setAttribute("success", true);
        log("redirecting to quickyform");
        response.sendRedirect("QuickyForm.jsp");
    }
}
