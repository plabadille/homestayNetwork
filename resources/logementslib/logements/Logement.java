/**
 * A parent class for representing housing, with a country, surface, nb rooms and address
 * @author Pierre Labadille
 * @since 2016-11-18
 */

package logements;

public class Logement {
	/** The housing country */
    protected String pays;
    /** The housing surface */
    protected int surface;
    /** The housing number of rooms */
    protected int nbPiece;
    /** The housing address */
    protected String adresse;
    
    /**
	 * Builds a new housing.
	 * @param pays The housing country
	 * @param surface The housing surface
	 * @param nbPiece The housing nb of rooms
	 * @param adresse The housing address
	 */
    public Logement (String pays, int surface, int nbPiece, String adresse) {
        this.pays = pays;
        if (surface > 0) {
        	this.surface = surface;
        } else {
        	throw new IllegalArgumentException();
        }
        if (nbPiece > 0) {
        	this.nbPiece = nbPiece;
        } else {
        	throw new IllegalArgumentException();
        }
        this.adresse = adresse;
    }
    
    /**
     * Returns the housing country.
     * @return The housing country
     */
    public String getPays() {
        return this.pays;
    }
    /**
     * Change the housing country.
     * @param The new housing country as a String
     */
    public void setPays(String pays) {
        this.pays = pays;
    }
    /**
     * Returns the housing surface.
     * @return The housing surface
     */
    public int getSurface() {
        return this.surface;
    }
    /**
     * Change the housing surface.
     * @param The new housing surface as a double
     */
    public void setSurface(int surface) {
        this.surface = surface;
    }
    /**
     * Returns the housing nb of rooms.
     * @return The housing nb of rooms
     */
    public int getNbPiece() {
        return this.nbPiece;
    }
    /**
     * Change the housing nb of rooms.
     * @param The new housing nb of rooms as an int
     */
    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }
    /**
     * Returns the housing address.
     * @return The housing address
     */
    public String getAdresse() {
        return this.adresse;
    }
    /**
     * Change the housing address.
     * @param The new housing address as a String
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    /**
     * Returns a representation of this housing as a string.
     * @return A representation of this housing as a string
     */
    public String toString() {
        return "Pays: " + this.getPays() + ". Surface: " + this.getSurface() + "m². Nombre de pièces: " + this.getNbPiece() + " pièces. Adresse: " + this.getAdresse();
    }
}