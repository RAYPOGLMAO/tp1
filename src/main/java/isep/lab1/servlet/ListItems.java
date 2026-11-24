package isep.lab1.servlet;

import isep.lab1.util.Html;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TP 1.3.2 -- stores the items in the session (ArrayList<String>) and shows them.
 * Extra: also shows how many times each item was entered.
 * Protected by SecurityFilter (TP2 1.4.1).
 */
@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        Map<String, Integer> counts = (Map<String, Integer>) session.getAttribute("itemCounts");
        if (items == null) {                       // first request: the list does not exist yet
            items = new ArrayList<>();
            counts = new LinkedHashMap<>();
            session.setAttribute("items", items);
            session.setAttribute("itemCounts", counts);
        }

        String newItem = req.getParameter("newItem");
        if (newItem != null && !newItem.isBlank()) {
            newItem = newItem.trim();
            items.add(newItem);
            counts.merge(newItem, 1, Integer::sum);
        }

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Html.head(out, "Your items");
        out.println("<ol>");
        for (String item : items) out.println("<li>" + Html.esc(item) + "</li>");
        out.println("</ol>");
        out.println("<h3>Number of times each item was entered</h3><ul>");
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            out.println("<li>" + Html.esc(e.getKey()) + " : " + e.getValue() + "</li>");
        }
        out.println("</ul><p><a href=\"items.html\">Add another item</a></p>");
        Html.foot(out);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);        // GET simply displays the current list
    }
}
