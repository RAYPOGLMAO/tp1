package isep.lab1.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
        System.out.println("[SecurityFilter] init()");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);   // does NOT create a session
        boolean connected = (session != null && session.getAttribute("user") != null);

        if (connected) {
            System.out.println("[SecurityFilter] BEFORE chain (authorized)");
            chain.doFilter(req, resp);
            System.out.println("[SecurityFilter] AFTER chain");
        } else {
            System.out.println("[SecurityFilter] REFUSED -> redirect to login.html");
            response.sendRedirect(request.getContextPath() + "/login.html");
            // no chain.doFilter(): the request stops here
        }
    }

    @Override
    public void destroy() {
        System.out.println("[SecurityFilter] destroy()");
    }
}