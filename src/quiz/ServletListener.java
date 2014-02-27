package quiz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyListener.
 * Upon initialization, creates a new product catalog and populates 
 * the array list of products. Sets the catalog attribute of the ServletContext.
 *
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
	 * Creates a new product catalog and populates the array list of products.
	 * Sets the catalog attribute of the ServletContext.
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	//create account manager
    	DAL dal = new DAL();
    	manager = new AccountManager(dal);
    	ServletContext context = arg0.getServletContext();
    	context.setAttribute("DAL", dal);
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
