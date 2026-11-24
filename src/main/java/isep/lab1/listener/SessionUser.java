package isep.lab1.listener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

public class SessionUser implements HttpSessionBindingListener, Serializable {

    private final String username;

    public SessionUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("[SessionUser] valueBound -> " + username
                + " added to the session as '" + event.getName() + "'");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("[SessionUser] valueUnbound -> " + username
                + " removed from the session ('" + event.getName() + "')");
    }
}