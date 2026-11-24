package isep.lab1.servlet;

import isep.lab1.util.Html;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/** TP 1.2 step 3 -- reads the four cookies and displays them. */
@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Html.head(out, "Cookies read from the browser");

        String[] wanted = {"firstName", "lastName", "birthDay", "sex"};
        Cookie[] cookies = req.getCookies();            // null when the browser sent none
        out.println("<table border=\"1\" cellpadding=\"5\">");
        for (String name : wanted) {
            String value = null;
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(name)) {
                        value = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                    }
                }
            }
            out.println("<tr><th>" + name + "</th><td>"
                    + (value == null ? "<i>(no cookie)</i>" : Html.esc(value)) + "</td></tr>");
        }
        out.println("</table>");
        Html.foot(out);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
