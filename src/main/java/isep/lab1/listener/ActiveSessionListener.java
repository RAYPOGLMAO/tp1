package isep.lab1.listener;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;


@WebListener
public class ActiveSessionListener implements HttpSessionListener {
    private static final AtomicInteger ACTIVE = new AtomicInteger(0);

    @Override
    public void sessionCreated (HttpSessionEvent se){
        int n = ACTIVE.incrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionsListener] session CREATED, active = " + n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        int n = ACTIVE.decrementAndGet();
        se.getSession().getServletContext().setAttribute("activesessions",n);
        System.out.println("[ActiveSessionsListener] session DESTORYED, active = " + n);
    }


}
