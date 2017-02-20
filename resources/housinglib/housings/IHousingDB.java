/**
 * An interface for databases of housing. The interface essentially declares CRUD operations
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

import java.sql.SQLException;
import java.util.List;

public interface IHousingDB {

	// "C" operations

    /**
     * Adds a house to this database.
     * @param home The home to add
     * @throws SQLException if the argument is not an object from Housing
     */
    public void add(Home home) throws SQLException;
    public void add(Apartment apartment) throws SQLException;

    public void update(Home home) throws SQLException;
    public void update(Apartment apartment) throws SQLException;

    // "R" operations

    /**
     * Return each housing present in the database
     * @return An array of housing present in the database
     */
    public List<Housing> getAll() throws SQLException;

    /**
     * Return a housing if it exist in the database
     * @param address housing The housing address to search
     * @return The housing if it exist or null
     * @throws SQLException
     */
    public Housing find(String address) throws SQLException;

    // "D" operations

    public void delete(Housing housing) throws SQLException;
}
