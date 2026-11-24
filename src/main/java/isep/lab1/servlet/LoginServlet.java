package isep.lab1.servlet;

import isep.lab1.listener.SessionUser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        String username = req.getParameter("username");

        HttpSession session = req.getSession();
        session.setAttribute("user", username);
        session.setAttribute("sessionUser", new SessionUser(username));        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<p>Welcome <b>" + username + "</b>, you are now connected.</p>");
        out.println("<p><a href=\"items.html\">Go to the items page</a></p>");
        out.println("</body></html>");
    }
}
