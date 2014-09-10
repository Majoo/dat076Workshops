package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.Shop;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application life cycle listener.
 *
 * @author hajo
 */
@WebListener()
public class ShopListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOG = Logger.getLogger(ShopListener.class.getName());

    // I.e. application starts
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.log(Level.INFO, "******* Putting Shop in application scope");
        sce.getServletContext().setAttribute(Keys.SHOP.toString(), Shop.newInstance());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nothing
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOG.log(Level.INFO, "******* Session Created {0}", se.getSession());
        se.getSession().setAttribute(Keys.PAGE_SIZE.toString(), 4); // Hardcoded
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Nothing
    }
}
