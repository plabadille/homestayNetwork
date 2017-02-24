/**
 * An interface for databases of housing. The interface essentially declares CRUD operations
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

import java.sql.SQLException;
import java.util.List;

public interface IHousingDB {

    /**
     * Create the table
     * @throws SQLException
     */
    public void createTable() throws SQLException;
    /**
     * Delete the table
     * @throws SQLException
     */
    public void deleteTable() throws SQLException;

    /**
     * Adds a house to this database.
     * @param home The home to add
     * @throws SQLException if the argument is not an object from Housing
     */
    public boolean add(Home home) throws SQLException;
    /**
     * Add an apartment
     * @param Apartment apartment The apartment
     * @return True if the apartment is insered
     * @throws SQLException
     */
    public boolean add(Apartment apartment) throws SQLException;

    /**
     * Update the home
     * @param Home home The home
     * @throws SQLException
     */
    public void update(Home home) throws SQLException;
    /**
     * Update the apartment
     * @param Apartment apartment The apartment
     * @throws SQLException
     */
    public void update(Apartment apartment) throws SQLException;

    /**
     * Return each housing present in the database
     * @return An array of housing present in the database
     * @throws SQLException [description]
     */
    public List<Housing> getAll() throws SQLException;

    /**
     * Return a housing if it exist in the database
     * @param address housing The housing address to search
     * @return The housing if it exists or null
     * @throws SQLException
     */
    public Housing find(String address) throws SQLException;

    /**
     * Delete an housing
     * @param Housing housing The housing
     * @throws SQLException
     */
    public void delete(Housing housing) throws SQLException;
}
