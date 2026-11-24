package isep.lab1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/AttributeDemo")
public class AttributeDemoServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException{
        req.setAttribute("demoreq","one"); //added
        req.setAttribute("demoReq", "two");      // replaced
        req.removeAttribute("demoReq");          // removed

        // application scope
        getServletContext().setAttribute("demoApp", "one");   // added
        getServletContext().setAttribute("demoApp", "two");   // replaced
        getServletContext().removeAttribute("demoApp");       // removed

        resp.setContentType("text/html");
        resp.getWriter().println("<p>Done. Look at the console.</p>");
    }

}
