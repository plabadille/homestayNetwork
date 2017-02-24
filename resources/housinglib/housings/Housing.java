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

    /**
     * Builds a new housing.
     * @param id The id
     * @param country The country
     * @param surface The surface
     * @param nbRoom The nb of rooms
     * @param address The address
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

    public Housing(String country, int surface, int nbRoom, String address) {
        this(-1, country, surface, nbRoom, address);
    }

    /**
     * Get the id
     * @return The id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the country.
     * @return The country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Change the country.
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the surface.
     * @return The surface
     */
    public int getSurface() {
        return this.surface;
    }

    /**
     * Change the surface.
     * @param surface The surface
     */
    public void setSurface(int surface) {
        this.surface = surface;
    }

    /**
     * Returns the nb of rooms.
     * @return The nb of rooms
     */
    public int getNbRoom() {
        return this.nbRoom;
    }

    /**
     * Change the nb of rooms.
     * @param nbRoom The nb of rooms
     */
    public void setNbRoom(int nbRoom) {
        this.nbRoom = nbRoom;
    }

    /**
     * Returns the address.
     * @return The address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Change the address.
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
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
