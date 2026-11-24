package isep.lab1.servlet;

import isep.lab1.util.Html;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/** TP 1.2 step 4 (extra) -- counts the visits with a cookie. */
@WebServlet("/VisitCounter")
public class VisitCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int visits = 0;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visits")) {
                    try { visits = Integer.parseInt(c.getValue()); }
                    catch (NumberFormatException ignored) { }
                }
            }
        }
        visits++;
        Cookie c = new Cookie("visits", String.valueOf(visits));
        c.setMaxAge(60 * 60 * 24 * 365);
        c.setPath("/");
        resp.addCookie(c);                              // before writing the body

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Html.head(out, "Visit counter (cookie)");
        out.println("<p>This is visit number <b>" + visits + "</b>.</p>");
        Html.foot(out);
    }
}
