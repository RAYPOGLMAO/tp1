package lab.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.mvc.model.Person;

import java.io.IOException;

/**
 * TP 3.3 -- the Controller. Only doPost (it only receives the form).
 * Reads the form, builds the Person (Model), stores it in the request and forwards to the right View.
 *
 * NOTE: the TP text shows dispatcher.include(...) but step 4 of 3.1 says "forwards".
 * For MVC the controller must TRANSFER control to the view, so we use forward().
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String birthDay  = request.getParameter("birthDay");   // mm/dd/yyyy
        String sex       = request.getParameter("sex");

        if (birthDay == null || !birthDay.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "birth date must be mm/dd/yyyy");
            return;
        }
        int year = Integer.parseInt(birthDay.substring(birthDay.lastIndexOf('/') + 1));

        Person person = new Person(firstName, lastName, birthDay, sex);
        request.setAttribute("person", person);               // the model travels with the request

        String page = (year < 1990) ? "/old.jsp" : "/young.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/mvc.html");
    }
}
