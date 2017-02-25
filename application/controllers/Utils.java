package controllers;

import javax.servlet.http.HttpSession;
import persons.PersonDB;
import persons.Person;

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
     * @param personDB A DAO object for persons
     */
    public static void initializeSession(HttpSession session, PersonDB personDB) {
        if (session.getAttribute("allPersons") == null) {
            session.setAttribute("allPersons", personDB.getAll());
        }
    }

    /**
     * Get the status of the connexion
     * @param session The session
     * @return True if the user is connected
     */
    public static boolean isConnected(HttpSession session) {
        if (Utils.getConnectedUser(session) != null) {
            return true;
        }
        return false;
    }

    /**
     * Get the connected user
     * @param session The session
     * @return The connected user / null
     */
    public static Person getConnectedUser(HttpSession session) {
        return (Person) session.getAttribute("activeUser");
    }

    /**
     * Connect a user
     * @param session The session
     * @param person The user to connect
     */
    public static void connectUser(HttpSession session, Person person) {
        session.setAttribute("activeUser", person);
    }

    /**
     * Disconnect the logged user
     * @param session The session
     */
    public static void disconectUser(HttpSession session) {
        session.removeAttribute("activeUser");
    }

    /**
     * Set the user as an admin
     * @param session The session
     */
    public static void connectAdmin(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            session.setAttribute("admin", true);
        }
    }

    /**
     * Unset the admin grant to the current user
     * @param session The session
     */
    public static void disconectAdmin(HttpSession session) {
        session.removeAttribute("admin");
    }
}
