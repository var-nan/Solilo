package main.solilo;

import main.solilo.dao.QuickyDAOImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="formProcess", value = "/form-process")
public class FormProcess extends HttpServlet {
    public String message;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // get input parameters
        String quickyMessage = request.getParameter("quickyMessage");
        boolean quickyVisible = (request.getParameter("quickyVisible") == "yes") ? true: false;

        // perform text cleaning?
        quickyMessage = quickyMessage.trim();

        // add to database, and redirect to same (form.jsp) page with success message.
        // create quicky and add it
        //boolean success = QuickyDAOImpl.addQuicky(qu);
        response.sendRedirect("login.jsp"); // TODO: add correct file name
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        boolean status;
        // check if the user is logged in
        //Cookie c = new Cookie("user", "nandhan")
    }
}
