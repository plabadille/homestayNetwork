/**
 * A class for SQL storage of housings in a database
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLHousingDB implements IHousingDB {

    /** The name for the SQL table where to store housings. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createHousingStatement;

    /** A prepared statement for retrieval of one housing. */
    private PreparedStatement retrieveHousingStatement;

    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store housings
     * @throws SQLException if a database access error occurs
     */
    public SQLHousingDB (Connection link, String table) throws SQLException {
        this.link=link;
        this.table=table;
        String query=null;
        query="INSERT INTO `"+this.table+"` VALUES(?,?,?)";
        this.createHousingStatement=this.link.prepareStatement(query);
        query="SELECT * FROM `"+this.table+"` WHERE address=?";
        this.retrieveHousingStatement=this.link.prepareStatement(query);
    }

    @Override
    public void addHousing (Housing housing) throws SQLException {
        this.create(housing);
    }

    // Methods

    /**
     * Resets the link to the database.
     * This method can be used in case the connection breaks down.
     * @param link An active link to the database
     */
    public void setLink (Connection link) {
        this.link=link;
    }

    /**
     * Creates the necessary table in the database. Nothing occurs if the table already exists.
     * @throws SQLException if a database access error occurs
     */
    public void createTables () throws SQLException {
        String query="CREATE TABLE IF NOT EXISTS `"+this.table+"` (";
        query+="`country` VARCHAR(100) NOT NULL, ";
        query+="`surface` INT(5000) NOT NULL, ";
        query+="`nbRoom` INT(20) NOT NULL, ";
        query+="`address` VARCHAR(255) NOT NULL, ";
        query+="PRIMARY KEY (`address`) ";
        query+=")";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Stores a new housing in the database.
     * @param housing The housing to store
     * @throws SQLException if a database access error occurs
     */
    public void create (Housing housing) throws SQLException {
        this.createHousingStatement.setString(1,housing.getName());
        this.createHousingStatement.setFloat(2,housing.getPricePerKg());
        this.createHousingStatement.setFloat(3,housing.getWeight());
        this.createHousingStatement.execute();
    }

    /**
     * Retrieves all the housings in the database.
     * @return A list of all housings in the database
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Housing> getAll1 () throws SQLException {
        String query="SELECT * FROM `"+this.table+"`";
        ResultSet rs=null;
        Statement statement=this.link.createStatement();
        rs=statement.executeQuery(query);
        List<Housing> res=new ArrayList<Housing>();
        while (rs.next()) {
            res.add(new Housing(rs.getString("country"), rs.getInt("surface"), rs.getFloat("nbRoom"), rs.getString("address")));
        }
        return res;
    }

    /**
     * Retrieves a housing in the database.
     * @param name The name of the housing
     * @return A housing, or null if none with the given name exists in the database
     * @throws SQLException if a database access error occurs
     */
    public Housing find (String address) throws SQLException {
        this.retrieveHousingStatement.setString(1,name);
        ResultSet rs=this.retrieveHousingStatement.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new Housing(rs.getString("country"), rs.getInt("surface"), rs.getFloat("nbRoom"), rs.getString("address")));
    }

    /**
     * Drops the table from the database. Nothing occurs if the table does not exist.
     * @throws SQLException if a database access error occurs
     */
    public void deleteTables () throws SQLException {
        String query="DROP TABLE IF EXISTS `"+this.table+"`";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    /**
     * Deletes a housing. Nothing occurs in case the housing does not exist in the database.
     * @param housing The housing
     * @throws SQLException if a database access error occurs
     */
    public void delete (Housing housing) throws SQLException {
        String query="DELETE FROM `"+this.table+"` WHERE name=\""+housing.getName()+"\"";
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

}
