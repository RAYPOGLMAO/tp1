package isep.lab1.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("appName", "ING1 Web Application");
        System.out.println("[ApplicationListener] application STARTED, appName = "
                + ctx.getAttribute("appName"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[ApplicationListener] application STOPPED");
    }
}