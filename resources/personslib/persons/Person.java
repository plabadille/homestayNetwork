package persons;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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

    /** The encrypted person's password */
    protected String password;

    /** The unique person's id in database */
    protected Long id;

    /**
     * Empty constructor for hibernate
     * @param name The person's name
     * @param firstName The person's first name
     * @param email The person's email address
     */
    public Person()
    {
    }
    /**
     * Builds a new person.
     * @param name The person's name
     * @param firstName The person's first name
     * @param email The person's email address
     */
    public Person (String name, String firstName, String email, String rawPassword) {
        this.name=name;
        this.firstName=firstName;
        this.email=email;
        this.password = BCrypt.hashpw(rawPassword,"$2a$10$uk.6XVzc.FdIAxBcvlOSquglioTN.0JUcqpp72BvY5Si.YMFRv0se");
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
     * Return the person's password
     * @return the person's password
     */
    public String getPassword (){
        return this.password;
    }

    /**
     * Set the Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Set the firstName
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Set the email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Set the password
     */
    public void setPassword(String password){
      this.password = password;
    }

    public void updatePasswordRaw(String rawPassword) {
        this.password = BCrypt.hashpw(rawPassword,"$2a$10$uk.6XVzc.FdIAxBcvlOSquglioTN.0JUcqpp72BvY5Si.YMFRv0se");
    }

    /**
     * Returns a representation of this person as a string.
     * @return A representation of this person as a string
     */
    public String toString () {
        return "person "+this.firstName+" "+this.name+" (email "+this.email+")";
    }

    /**
     * Create a password hash from a specific password
     * @author Pierre Labadille
     * @since November, 2016
     */
    // public String passFromEmail(String email) {
    //
    // 	if(null == email) return null;
    //
    // 	//gen password first Word before a special carak
    // 	Pattern r = Pattern.compile("^\\w*");
    // 	Matcher m = r.matcher(email);
    //
    // 	if (m.find()) {
    // 		//hash the generated password
    // 		String password = m.group(0);
    // 		String hash = md5(password);
    // 		return hash;
    // 	} else {
    // 		throw new IllegalArgumentException("Cannot generating password: email format issue: " + email);
    // 	}
    // }

    /**
     * Create a password hash from a specific password
     * @author Viral Patel
     * @since June, 2012
     */
    public static String md5(String input) {
		String md5 = null;

		if(null == input) return null;

		try {
			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			//Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

}
