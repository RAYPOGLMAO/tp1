package isep.lab1.servlet;

import isep.lab1.util.Html;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * TP 1.1 (POST + HTML table + radio buttons + 2 URLs) and
 * TP 1.2 (cookies firstName, lastName, birthDay, sex).
 */
@WebServlet(name = "FormServlet", urlPatterns = {"/FormServlet", "/Form_Servlet"})
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");              // before the first getParameter
        resp.setContentType("text/html; charset=UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String birthDay  = req.getParameter("birthDay"); // mm/dd/yyyy
        String sex       = req.getParameter("sex");      // null if no radio selected

        // 1.2 -- cookies must be added BEFORE the body is written
        String[] names  = {"firstName", "lastName", "birthDay", "sex"};
        String[] values = {firstName, lastName, birthDay, sex};
        for (int i = 0; i < names.length; i++) {
            if (values[i] == null || values[i].isBlank()) continue;
            // URLEncoder: cookie values cannot contain spaces / special characters
            Cookie c = new Cookie(names[i], URLEncoder.encode(values[i], StandardCharsets.UTF_8));
            c.setMaxAge(60 * 60 * 24);   // 1 day
            c.setPath("/");
            resp.addCookie(c);
        }

        PrintWriter out = resp.getWriter();
        Html.head(out, "Submitted data");
        out.println("<table border=\"1\" cellpadding=\"5\">");
        row(out, "First name", firstName);
        row(out, "Last name", lastName);
        row(out, "Birth date (mm/dd/yyyy)", birthDay);
        row(out, "Sex", sex == null ? "(not specified)" : sex);
        out.println("</table>");
        out.println("<p>Cookies created. <a href=\"ReadCookie\">Read them with ReadCookie</a></p>");
        Html.foot(out);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/form.html");
    }

    private static void row(PrintWriter out, String label, String value) {
        out.println("<tr><th>" + label + "</th><td>" + Html.esc(value) + "</td></tr>");
    }
}
