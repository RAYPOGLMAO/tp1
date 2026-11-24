package isep.lab1.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;


@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent e){
        System.out.println("[SessionAttributeListener] ADDED    " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced (HttpSessionBindingEvent e){
        System.out.println("[SessionAttributeListener] REPLACED " + e.getName()
                + " old = " + e.getValue()
                + ", new = " + e.getSession().getAttribute(e.getName()));
    }
    @Override
    public void attributeRemoved(HttpSessionBindingEvent e){
        System.out.println("[SessionAttributeListener] REMOVED  " + e.getName() + " (was " + e.getValue() + ")");
    }

}
