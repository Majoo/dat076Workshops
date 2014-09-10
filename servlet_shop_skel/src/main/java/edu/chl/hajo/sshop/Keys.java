package edu.chl.hajo.sshop;

/**
 * Application global keys for all the maps (session, applicationScope,...)
 * Trying to avoid duplicate strings all over
 *
 * @author hajo
 */
public enum Keys {
    
    SHOP,// Refence to the shop
    USER, // The logged in user
    PRODUCT_LIST,  
    CURRENT_PAGE,  // Needed for navigation
    PAGE_SIZE, 
    COUNT,
    PARTIALS   // Path to all partials to include in JSPs 
}
