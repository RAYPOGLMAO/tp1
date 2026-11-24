package isep.lab1.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String action = req.getParameter("action");
        resp.setContentType("text/html");

        if (session != null && "user".equals(action)) {
            session.removeAttribute("user");
            resp.getWriter().println("<p>Attribute 'user' removed.</p>");
            return;
        }

        if (session != null) {
            session.invalidate();
        }

        if (session != null && "remove".equals(action)) {
            session.removeAttribute("sessionUser");
            resp.getWriter().println("<p>SessionUser removed.</p>");
            return;
        }
        resp.getWriter().println("<p>Session invalidated. <a href=\"ListItems\">Try ListItems</a></p>");
    }
}