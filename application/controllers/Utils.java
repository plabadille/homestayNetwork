package controllers;

import javax.servlet.http.HttpSession;
import persons.PersonDB;

/**
 * A convenience class for factoring some code used in several controllers.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France
 * @since February, 2014
 */
public class Utils {

    /**
     * Loads the list of all products into the HTTP session under key "allProducts".
     * Does nothing if the list is already loaded.
     * @param session The HTTP session into which products must be loaded
     * @param productDAO A DAO object for products
     */
    public static void initializeSession (HttpSession session, PersonDB personDB) {
        personDB.initialize();
        if (session.getAttribute("allPersons")==null) {
            session.setAttribute("allPersons",personDB.getAll());
        }
    }

} 