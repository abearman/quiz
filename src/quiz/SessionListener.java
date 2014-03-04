package quiz;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener.
 * Creates a new cart for each new session to ensure that if multiple people
 * use the ShoppingCart at the same time, their carts do not coincide.
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
    }

    /** 
	 * Creates a cart associated with this session.	
	 */
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}
	
}
