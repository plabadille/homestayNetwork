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
     * Loads the list of all persons into the HTTP session under key "allPersons".
     * Does nothing if the list is already loaded.
     * @param session The HTTP session into which persons must be loaded
     * @param productDAO A DAO object for persons
     */
    public static void initializeSession (HttpSession session, PersonDB personDB) {
        if (session.getAttribute("allPersons")==null) {
            session.setAttribute("allPersons",personDB.getAll());
        }
    }

} 