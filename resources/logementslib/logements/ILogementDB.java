/**
 * An interface for databases of housing. The interface essentially declares CRUD operations
 * @author Pierre Labadille
 * @since 2016-11-18
 */

package logements;

import java.util.List;

public interface ILogementDB {
	
	// "C" operations

    /**
     * Adds a housing to this database.
     * @param logement The housing to add as a housing Object
     * @throws Exception if the argument is not an object from Logement
     */
    public void addLogement(Logement logement) throws IllegalArgumentException;
    
    // "R" operations
    
    /**
     * Return each housing present in the database
     * @return An array of housing present in the database
     */
    public List<Logement> getAll();
    
    /**
     * Return if a logement exist or not in the database
     * @return A boolean
     */
    public boolean exist(Logement logement);
    
    /**
     * Return a logement if it exist in the database
     * @return The logement if it exist or null
     */
    public Logement find(String adresse);
    
    // "D" operations
    /**
     * Delete a logement (identified by his adress) if it exist in the database
     * @throws Exception if the argument is not find in the database
     */
    public void delete (String adresse) throws IndexOutOfBoundsException;
    
}
