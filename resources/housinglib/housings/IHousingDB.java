/**
 * An interface for databases of housing. The interface essentially declares CRUD operations
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

import java.util.List;

public interface IHousingDB {

	// "C" operations

    /**
     * Adds a housing to this database.
     * @param housing The housing to add as a housing Object
     * @throws IllegalArgumentException if the argument is not an object from Housing
     */
    public void create(Housing housing) throws IllegalArgumentException;

    // "R" operations

    /**
     * Return each housing present in the database
     * @return An array of housing present in the database
     */
    public List<Housing> getAll();

    /**
     * Return if a housing exist or not in the database
     * @param housing The housing to check as a housing Object
     * @return A boolean
     */
    public boolean exist(Housing housing);

    /**
     * Return a housing if it exist in the database
     * @param address housing The housing address to search
     * @return The housing if it exist or null
     */
    public Housing find(String address);

    // "D" operations

    /**
     * Delete a housing (identified by his adress) if it exist in the database
     * @param address housing The housing address to search
     * @throws IndexOutOfBoundsException if the argument is not find in the database
     */
    public void delete (String address) throws IndexOutOfBoundsException;

}
