package isep.lab1.listener;


import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent e){
        System.out.println("[RequestAttributeListener] ADDED    " + e.getName() + " = " + e.getValue());
    }

    @Override

    public void attributeReplaced (ServletRequestAttributeEvent e){
        System.out.println("[RequestAttributeListener] REPLACED " + e.getName()
                + " old = " + e.getValue()
                + ", new = " + e.getServletRequest().getAttribute(e.getName()));
    }

    @Override
    public void attributeRemoved (ServletRequestAttributeEvent e){
        System.out.println("[RequestAttributeListener] REMOVED  " + e.getName() + " (was " + e.getValue() + ")");
    }





}
