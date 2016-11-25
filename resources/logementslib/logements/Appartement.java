/**
 * A child class of Logement for representing apartment, with a country, surface, nb rooms and address
 * @author Pierre Labadille
 * @since 2016-11-18
 */

package logements;

public class Appartement extends Logement {
	/**
	 * Builds a new apartment.
	 * @param pays The apartment country
	 * @param surface The apartment surface
	 * @param nbPiece The apartment nb of rooms
	 * @param adresse The apartment address
	 */
    public Appartement(String pays, int surface, int nbPiece, String adresse) {
        super(pays, surface, nbPiece, adresse);
    }
    /**
     * Returns a representation of this apartment as a string.
     * @return A representation of this apartment as a string
     */
    public String toString() {
        return "Ce logement est un appartement.\n" + super.toString();
    }
}