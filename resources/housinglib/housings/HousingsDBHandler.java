package housings;

import housings.SQLHousingDB;
import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A class for accessing the database for housings. This class can be used as a bean with (read-only) property "db".
 * Strings host, database, username, password, and table are expected to be found.
 * At any moment there is only one instance of the link to the database, and one instance of the SQLHousingsDB class, maintained by this class.
 * Connector/J is used for accessing the DBMS.
 * @author Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France.
 * @since February, 2012
 */
public class HousingsDBHandler {

    /** The unique link to the database (null if none active). */
    private static Connection link;

    /** The unique instance of class SQLHousingsDB (null if none). */
    private static SQLHousingDB db;

    /**
     * Returns the instance of SQLHousingDB.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    public static SQLHousingDB getDb () throws NamingException, SQLException {
        if (HousingsDBHandler.db==null) {
            HousingsDBHandler.initialize();
        }
        return HousingsDBHandler.db;
    }

    /**
     * Releases the connection to the database.
     * @throws SQLException if any problem occurs while closing the connection
     */
    public static void close () throws SQLException {
        if (HousingsDBHandler.link!=null) {
            HousingsDBHandler.link.close();
        }
    }

    // Helper methods =====================================================================

    /**
     * Initializes the connection to the database and the instance of SQLHousingsDB.
     * For each of these objects, nothing occurs if it is already initialized.
     * @throws NamingException if strings host, database, username, password, or table cannot be found
     * @throws SQLException if any problem occurs for accessing the database
     */
    private static void initialize () throws NamingException, SQLException {
        // InitialContext initialContext = new InitialContext();
        String host="mysql.info.unicaen.fr";
        String database="21101555_3";
        String username="21101555";
        String password="empty42";
        String table="housing";
        // String host=initialContext.doLookup("java:comp/env/host");
        // String database=initialContext.doLookup("java:comp/env/database");
        // String username=initialContext.doLookup("java:comp/env/username");
        // String password=initialContext.doLookup("java:comp/env/password");
        // String table=initialContext.doLookup("java:comp/env/table");
        HousingsDBHandler.db=new SQLHousingDB(HousingsDBHandler.getLink(host,database,username,password),table);
    }

    /**
     * Returns the link to the database, which is active.
     * @param host The hostname for the DBMS
     * @paam database The name for the database to use in the DBMS
     * @param username The username for connecting to the database
     * @param password The password for connecting to the database
     * @return An active link to the database
     * @throws SQLException if no active link can be established
     */
    private static Connection getLink (String host, String database, String username, String password) throws SQLException {
        if (HousingsDBHandler.link==null) {
            MysqlDataSource ds=new MysqlDataSource();
            ds.setServerName(host);
            ds.setDatabaseName(database);
            HousingsDBHandler.link=ds.getConnection(username,password);
        }
        if (!HousingsDBHandler.link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        return HousingsDBHandler.link;
    }

}
