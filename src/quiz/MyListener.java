package quiz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyListener.
 * Creates a new account manager upon initialization that can be
 * used by the whole login system website.
 */
@WebListener
public class MyListener implements ServletContextListener {

	private AccountManager manager;
	
    /**
     * Default constructor. 
     */
    public MyListener() {
    }

	/**
	 * Creates a new account manager and sets the attribute of the context.
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	manager = new AccountManager();
    	ServletContext context = arg0.getServletContext();
    	context.setAttribute("manager", manager);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
