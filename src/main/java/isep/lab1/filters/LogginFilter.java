package isep.lab1.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogginFilter implements Filter {

    private String prefix = "[LoggingFilter]";

    @Override
    public void init (FilterConfig config){
        String p = config.getInitParameter("logPrefix");
        if (p != null){
            System.out.println(prefix + " init()");
        }
    }

    @Override
    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain)
        throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)  req;
        System.out.println(prefix + " BEFORE -> " + request.getRequestURL());
        long start = System.nanoTime();

        chain.doFilter(req, resp);

        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println(prefix + " AFTER (" + ms + " ms)");
    }
    @Override
    public void destroy (){
        System.out.println(prefix + " destory");
    }
}
