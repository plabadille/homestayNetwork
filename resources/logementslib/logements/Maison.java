/**
 * A child class of Logement for representing house, with a country, surface, nb rooms, address and a garden surface
 * @author Pierre Labadille
 * @since 2016-11-18
 */

package logements;

public class Maison extends Logement {
	/** The house garden surface */
    protected int surfaceJardin;
    /**
	 * Builds a new house.
	 * @param pays The house country
	 * @param surface The house surface
	 * @param nbPiece The house nb of rooms
	 * @param adresse The house address
	 * @param surfaceJardin The house garden surface
	 */
    public Maison (String pays, int surface, int nbPiece, String adresse, int surfaceJardin) {
        super(pays, surface, nbPiece, adresse);
        if (surfaceJardin >= 0){
        	this.surfaceJardin = surfaceJardin;
        } else {
        	throw new IllegalArgumentException();
        }
        
    }
    /**
     * Returns the house garden surface.
     * @return The house garden surface
     */
    public int getSurfaceJardin() {
        return this.surfaceJardin;
    }
    /**
     * Change the house garden surface.
     * @param The new house garden surface as a double
     */
    public void setSurfaceJardin(int surfaceJardin) {
        this.surfaceJardin = surfaceJardin;
    }
    /**
     * Returns a representation of this house as a string.
     * @return A representation of this house as a string
     */
    public String toString() {
        return "Ce logement est une maison.\n" + super.toString() + "\n Jardin d'une surface de " + this.getSurfaceJardin() + "m².";
    }
}