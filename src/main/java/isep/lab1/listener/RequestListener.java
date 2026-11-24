package isep.lab1.listener;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.net.URI;


@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized (ServletRequestEvent sre){
        System.out.println("[RequestListener] request INITIALIZED" + uri(sre.getServletRequest()));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("[RequestListener] request DESTROYED " + uri(sre.getServletRequest()));
    }

    private String uri(ServletRequest sr){
        if (sr instanceof HttpServletRequest){
            return ((HttpServletRequest) sr).getRequestURI();
        }
        return "?";
    }
}
