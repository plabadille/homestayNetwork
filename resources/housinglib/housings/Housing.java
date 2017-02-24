/**
 * A parent class for representing housing, with a country, surface, nb rooms and address
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

public abstract class Housing {
    protected int id;
    /** The housing country */
    protected String country;
    /** The housing surface */
    protected int surface;
    /** The housing number of rooms */
    protected int nbRoom;
    /** The housing address */
    protected String address;

    public Housing(String country, int surface, int nbRoom, String address) {
        this(-1, country, surface, nbRoom, address);
    }

    /**
     * Builds a new housing.
     * @param country The housing country
     * @param surface The housing surface
     * @param nbRoom The housing nb of rooms
     * @param address The housing address
     */
    public Housing(int id, String country, int surface, int nbRoom, String address) {
        this.id = id;
        this.country = country;
        if (surface > 0) {
            this.surface = surface;
        } else {
            throw new IllegalArgumentException();
        }
        if (nbRoom > 0) {
            this.nbRoom = nbRoom;
        } else {
            throw new IllegalArgumentException();
        }
        this.address = address;
    }

    /**
     * Get the id
     * @return The id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the housing country.
     * @return The housing country
     */
    public String getCountry() {
        return this.country;
    }
    /**
     * Change the housing country.
     * @param country The new housing country as a String
     */
    public void setCountry(String country) {
        this.country = country;
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
     * @param surface The new housing surface as a double
     */
    public void setSurface(int surface) {
        this.surface = surface;
    }
    /**
     * Returns the housing nb of rooms.
     * @return The housing nb of rooms
     */
    public int getNbRoom() {
        return this.nbRoom;
    }
    /**
     * Change the housing nb of rooms.
     * @param nbRoom The new housing nb of rooms as an int
     */
    public void setNbRoom(int nbRoom) {
        this.nbRoom = nbRoom;
    }
    /**
     * Returns the housing address.
     * @return The housing address
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * Change the housing address.
     * @param address The new housing address as a String
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns a representation of this housing as a string.
     * @return A representation of this housing as a string
     */
    public String toString() {
        return "Pays: " + this.getCountry() + ". Surface: " + this.getSurface() + "m². Nombre de pièces: " + this.getNbRoom() + " pièces. addresse: " + this.getAddress();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (this == object) { return true; }

        Housing otherHousing = (Housing) object;
        return this.getAddress().equals(otherHousing.getAddress())
            && this.getNbRoom() == otherHousing.getNbRoom()
            && this.getCountry().equals(otherHousing.getCountry())
            && this.getSurface() == otherHousing.getSurface();
    }
}
