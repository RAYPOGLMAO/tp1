package isep.lab1.servlet;

import isep.lab1.util.Html;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * TP 1.3.1 -- welcome / welcome back + visit counter (in the session)
 * Extra: asks the full name + date of birth the first time, then shows days to the birthday.
 * TP2 1.4.2 (2): also displays the number of active sessions (set by ActiveSessionListener).
 */
@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);

        Integer visits = (Integer) session.getAttribute("visits");
        boolean newcomer = (visits == null);
        visits = newcomer ? 1 : visits + 1;
        session.setAttribute("visits", visits);

        String fullName = (String) session.getAttribute("fullName");
        LocalDate dob = (LocalDate) session.getAttribute("dob");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Html.head(out, newcomer ? "Welcome on my site" : "Welcome back");
        out.println("<p>Number of visits in this session: <b>" + visits + "</b></p>");
        out.println("<p>Currently active sessions: <b>"
                + getServletContext().getAttribute("activeSessions") + "</b></p>");

        if (fullName == null || dob == null) {
            // first time: ask for the information (extra)
            out.println("<form method=\"post\" action=\"ShowSession\">");
            out.println("Full name: <input type=\"text\" name=\"fullName\" required><br>");
            out.println("Date of birth: <input type=\"date\" name=\"dob\" required><br>");
            out.println("<input type=\"submit\" value=\"Save\"></form>");
        } else {
            long days = daysToNextBirthday(dob);
            if (days == 0) {
                out.println("<p>Hi, " + Html.esc(fullName) + ". Happy birthday today!</p>");
            } else {
                out.println("<p>Hi, " + Html.esc(fullName) + ". There are <b>" + days
                        + "</b> days to your birthday.</p>");
            }
        }
        Html.foot(out);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String fullName = req.getParameter("fullName");
        String dobText = req.getParameter("dob");          // yyyy-MM-dd (input type=date)
        if (fullName == null || fullName.isBlank() || dobText == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "fullName and dob are required");
            return;
        }
        try {
            LocalDate dob = LocalDate.parse(dobText);
            HttpSession session = req.getSession(true);
            session.setAttribute("fullName", fullName.trim());
            session.setAttribute("dob", dob);
        } catch (DateTimeParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid date (expected yyyy-MM-dd)");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/ShowSession");   // Post-Redirect-Get
    }

    private static long daysToNextBirthday(LocalDate dob) {
        LocalDate today = LocalDate.now();
        LocalDate next = dob.withYear(today.getYear());
        if (next.isBefore(today)) next = dob.withYear(today.getYear() + 1);
        return ChronoUnit.DAYS.between(today, next);
    }
}
