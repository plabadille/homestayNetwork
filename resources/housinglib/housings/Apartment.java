/**
 * A child class of Housing for representing apartment, with a country, surface, nb rooms and address
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

public class Apartment extends Housing {
	/**
	 * Builds a new apartment.
	 * @param country The apartment country
	 * @param surface The apartment surface
	 * @param nbRoom The apartment nb of rooms
	 * @param address The apartment address
	 */
    public Apartment(String country, int surface, int nbRoom, String address) {
        super(country, surface, nbRoom, address);
    }
    /**
     * Returns a representation of this apartment as a string.
     * @return A representation of this apartment as a string
     */
    public String toString() {
        return "Ce logement est un apartment.\n" + super.toString();
    }
}
