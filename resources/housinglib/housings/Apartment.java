/**
 * A child class of Housing for representing apartment, with a country, surface, nb rooms and address
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

public class Apartment extends Housing {
	/**
	 * Builds a new apartment.
	 * @param country The country
	 * @param surface The surface
	 * @param nbRoom The nb of rooms
	 * @param address The address
	 */
    public Apartment(String country, int surface, int nbRoom, String address) {
        super(country, surface, nbRoom, address);
    }

    /**
     * Builds a new apartment.
     * @param id The id
     * @param country The country
     * @param surface The surface
     * @param nbRoom The nb of rooms
     * @param address The address
     */
    public Apartment(int id, String country, int surface, int nbRoom, String address) {
        super(id, country, surface, nbRoom, address);
    }

    @Override
    public String toString() {
        return "Ce logement est un apartment.\n" + super.toString();
    }
}
