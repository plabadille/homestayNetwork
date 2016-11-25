package persons;

/**
 * A class for representing persons, with a name, a first name, and an email address.
 * @author Charlotte Lecluze and Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France
 * @since January, 2013
 */
public class Person {

    /** The person's name. */
    protected String name;

    /** The person's first name. */
    protected String firstName;

    /** The person's email address. */
    protected String email;

    /**
     * Builds a new person.
     * @param name The person's name
     * @param firstName The person's first name
     * @param email The person's email address
     */
    public Person (String name, String firstName, String email) {
        this.name=name;
        this.firstName=firstName;
        this.email=email;
    }

    /**
     * Returns the person's name.
     * @return The person's name
     */
    public String getName () {
        return this.name;
    }

    /**
     * Returns the person's first name.
     * @return The person's first name.
     */
    public String getFirstName () {
        return this.firstName;
    }

    /**
     * Returns the person's email address.
     * @return The person's email address
     */
    public String getEmail () {
        return this.email;
    }

    /**
     * Returns a representation of this person as a string.
     * @return A representation of this person as a string
     */
    public String toString () {
        return "person "+this.firstName+" "+this.name+" (email "+this.email+")";
    }

}
