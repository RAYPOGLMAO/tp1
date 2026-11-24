package isep.lab1.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent e){
        System.out.println("[ApplicationAttributeListener] ADDED    " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent e){
        System.out.println("[ApplicationAttributeListener] REPLACED " + e.getName()
                + " old = " + e.getValue()
                + ", new = " + e.getServletContext().getAttribute(e.getName()));
    }

    @Override
    public void attributeRemoved (ServletContextAttributeEvent e){
        System.out.println("[ApplicationAttributeListener] REMOVED  " + e.getName() + " (was " + e.getValue() + ")");

    }


}
