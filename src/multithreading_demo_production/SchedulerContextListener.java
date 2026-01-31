package multithreading_demo_production;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SchedulerContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Application started - starting scheduler");
        MyScheduler.start();
    }

    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("Application stopping - stopping scheduler");
        MyScheduler.stop();
    }
}

