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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class SQLHousingDB implements IHousingDB {

    /** The name for the SQL table where to store housings. */
    protected String table;

    /** A prepared statement for creations. */
    private PreparedStatement createHousingStatement;
    private PreparedStatement updateHousingStatement;

    /** A prepared statement for retrieval of one housing. */
    private PreparedStatement retrieveAllHousingStatement;
    private PreparedStatement deleteHousingStatement;

    /** A link to the database. */
    protected Connection link;

    /**
     * Builds a new instance.
     * @param link An active connection to an SQL database
     * @param table The name of the table where to store housings
     * @throws SQLException if a database access error occurs
     */
    public SQLHousingDB(Connection link, String table) throws SQLException {
        this.link = link;
        this.table = table;

        this.createHousingStatement = this.link.prepareStatement(
            "INSERT INTO " + this.table + " (country, surface, nbRoom, address,"
            + " gardenSurface, isApartment) VALUES(?,?,?,?,?,?)"
        );

        this.updateHousingStatement = this.link.prepareStatement(
            "UPDATE " + this.table + " SET country=?, surface=?, nbRoom=?, "
            + "gardenSurface=?, isApartment=?, address=? WHERE id=?"
        );

        this.retrieveAllHousingStatement = this.link.prepareStatement(
            "SELECT * FROM " + this.table + " WHERE id=?"
        );

        this.deleteHousingStatement = this.link.prepareStatement(
            "DELETE FROM " + this.table + " WHERE id=?"
        );

        createTable();
    }

    @Override
    public boolean add(Home home) throws SQLException {
        try {
            this.createHousingStatement.setString(1, home.getCountry());
            this.createHousingStatement.setInt(2, home.getSurface());
            this.createHousingStatement.setInt(3, home.getNbRoom());
            this.createHousingStatement.setString(4, home.getAddress());
            this.createHousingStatement.setInt(5, home.getGardenSurface());
            this.createHousingStatement.setBoolean(6, false);
            this.createHousingStatement.execute();
            return true;
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    @Override
    public boolean add(Apartment apartment) throws SQLException {
        try {
            this.createHousingStatement.setString(1, apartment.getCountry());
            this.createHousingStatement.setInt(2, apartment.getSurface());
            this.createHousingStatement.setInt(3, apartment.getNbRoom());
            this.createHousingStatement.setString(4, apartment.getAddress());
            this.createHousingStatement.setInt(5, 0);
            this.createHousingStatement.setBoolean(6, true);
            this.createHousingStatement.execute();
            return true;
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    @Override
    public void update(Home home) throws SQLException {
        this.updateHousingStatement.setInt(1, home.getSurface());
        this.updateHousingStatement.setInt(2, home.getNbRoom());
        this.updateHousingStatement.setString(3, home.getAddress());
        this.updateHousingStatement.setInt(4, home.getGardenSurface());
        this.updateHousingStatement.setBoolean(5, false);
        this.updateHousingStatement.setString(6, home.getCountry());
        this.updateHousingStatement.setLong(7, home.getId());
        this.createHousingStatement.execute();
    }

    @Override
    public void update(Apartment apartment) throws SQLException {
        this.updateHousingStatement.setString(1, apartment.getCountry());
        this.updateHousingStatement.setInt(2, apartment.getSurface());
        this.updateHousingStatement.setInt(3, apartment.getNbRoom());
        this.updateHousingStatement.setString(4, apartment.getAddress());
        this.updateHousingStatement.setInt(5, 0);
        this.updateHousingStatement.setBoolean(6, true);
        this.updateHousingStatement.setLong(7, apartment.getId());
        this.createHousingStatement.execute();
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = this.link.createStatement();

        String query="CREATE TABLE IF NOT EXISTS " + this.table + " (";
        query += " id int NOT NULL AUTO_INCREMENT,";
        query += " country VARCHAR(100) NOT NULL,";
        query += " surface INT NOT NULL,";
        query += " nbRoom INT NOT NULL,";
        query += " gardenSurface INT NOT NULL,";
        query += " address VARCHAR(255) NOT NULL,";
        query += " isApartment boolean NOT NULL,";
        query += " PRIMARY KEY (id)";
        query += ")";

        statement.execute(query);
    }

    @Override
    public List<Housing> getAll() throws SQLException {
        String query="SELECT * FROM " + this.table;
        ResultSet rs = this.link.createStatement().executeQuery(query);
        List<Housing> res = new ArrayList<Housing>();

        while (rs.next()) {
            if (!rs.getBoolean("isApartment")) {
                res.add(new Home(
                    rs.getLong("id"),
                    rs.getString("country"),
                    rs.getInt("surface"),
                    rs.getInt("nbRoom"),
                    rs.getString("address"),
                    rs.getInt("gardenSurface")
                ));
            } else {
                res.add(new Apartment(
                    rs.getLong("id"),
                    rs.getString("country"),
                    rs.getInt("surface"),
                    rs.getInt("nbRoom"),
                    rs.getString("address")
                ));

            }
        }

        return res;
    }

    @Override
    public Housing find(String address) throws SQLException {
        this.retrieveAllHousingStatement.setString(1, address);
        ResultSet rs = this.retrieveAllHousingStatement.executeQuery();

        if (!rs.next()) {
            return null;
        }
        if (!rs.getBoolean("isApartment")) {
            return new Home(
                rs.getLong("id"),
                rs.getString("country"),
                rs.getInt("surface"),
                rs.getInt("nbRoom"),
                rs.getString("address"),
                rs.getInt("gardenSurface")
            );
        } else {
            return new Apartment(
                rs.getLong("id"),
                rs.getString("country"),
                rs.getInt("surface"),
                rs.getInt("nbRoom"),
                rs.getString("address")
            );
        }
    }

    @Override
    public void deleteTable() throws SQLException {
        String query="DROP TABLE IF EXISTS " + this.table;
        Statement statement=this.link.createStatement();
        statement.execute(query);
    }

    @Override
    public void delete(Housing housing) throws SQLException {
        this.deleteHousingStatement.setLong(1, housing.getId());
        this.deleteHousingStatement.execute();
    }
}
