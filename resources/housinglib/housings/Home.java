/**
 * A child class of Housing for representing house, with a country, surface, nb rooms, address and a garden surface
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
*/

package housings;

public class Home extends Housing {
    /** The house garden surface */
    protected int gardenSurface;
    /**
     * Builds a new house.
     * @param country The house country
     * @param surface The house surface
     * @param nbRoom The house nb of rooms
     * @param address The house address
     * @param gardenSurface The house garden surface
     */
    public Home (String country, int surface, int nbRoom, String address, int gardenSurface) {
        super(country, surface, nbRoom, address);
        if (gardenSurface >= 0){
            this.gardenSurface = gardenSurface;
        } else {
            throw new IllegalArgumentException();
        }

    }
    /**
     * Returns the house garden surface.
     * @return The house garden surface
     */
    public int getSurfaceJardin() {
        return this.gardenSurface;
    }
    /**
     * Change the house garden surface.
     * @param gardenSurface The new house garden surface as a double
     */
    public void setSurfaceJardin(int gardenSurface) {
        this.gardenSurface = gardenSurface;
    }
    /**
     * Returns a representation of this house as a string.
     * @return A representation of this house as a string
     */
    public String toString() {
        return "Ce logement est une maison.\n" + super.toString() + "\n Jardin d'une surface de " + this.getSurfaceJardin() + "m².";
    }
}
