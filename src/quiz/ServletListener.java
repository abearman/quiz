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
public class ServletListener implements ServletContextListener {

	private AccountManager manager;
	
    /**
     * Default constructor. 
     */
    public ServletListener() {
    }

	/**
	 * Creates a new account manager and sets the attribute of the context.
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	//create account manager
    	manager = new AccountManager();
    	ServletContext context = arg0.getServletContext();
    	context.setAttribute("manager", manager);
    	
    	//TODO keep track of announcements array of strings (from database)
    	//TODO keep track of array list of popular quizzes by name
    	//TODO keep track of recently created quizzes
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
