/**
 * Persons test class
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-25
 */

package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import persons.Person;
import persons.PersonDB;
import persons.IPersonDB;

import org.junit.Test;

public class TestPersonsDB {

	protected static PersonDB l = new PersonDB();
	//protected static IPersonDB instance;

	//test hash
	@Test
	public void test_find() {
		System.out.println("-----------------------------------");
				try{
					l.initialize();
					l.create(new Person("Dupont","Marie","marie.dupont@mail.fr"));
				} catch (Exception e){
					System.out.println("create fail ");
				e.printStackTrace();
				}
				try{
					Person test1 = l.find("marie.dupont@mail.fr");
					System.out.println(test1.toString());
				} catch (Exception e){
					System.out.println("find fail");
				}

	}
	@Test
	public void test_getAll() {
		System.out.println("-----------------------------------");
				try{
					l.initialize();
					l.create(new Person("Dupont2","Marie2","marie2.dupont@mail.fr"));
					l.create(new Person("Dupont3","Marie2","marie3.dupont@mail.fr"));
				} catch (Exception e){
					System.out.println("create fail ");
				e.printStackTrace();
				}
				try{
					Collection<Person> test1 = l.getAll();
					System.out.println(test1);
				} catch (Exception e){
					System.out.println("getAll fail");
				}

	}
	@Test
	public void test_getAllEmails() {
		System.out.println("-----------------------------------");
				try{
					l.initialize();
					Collection<String> test2 = l.getAllEmails();
					System.out.println(test2);
				} catch (Exception e){
					System.out.println("getAllEmails fail");
					e.printStackTrace();
				}

	}
	
	

	// @Test
	// public void test_hashFromEmail() {
	// 	String hash = l.passFromEmail("test");
    //
	// 	assertEquals(l.passFromEmail("test"), hash);
	// }
    //
	// // "C" operations
	// @Test
	// public void test_createPerson() {
	// 	String email = "mathieu.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
    //
	// 	assertEquals(true, l.exists(email));
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void test_createUserWithExistingEmail() {
	// 	String email = "alreadyExist.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
    //
	// 	Person person2 = new Person("Lebae", "gaefgea", email);
	// 	l.create(person2, l.passFromEmail(email));
	// }
    //
	// // "R" operations
	// @Test
	// public void test_getAll() {
	// 	int countInitPersons = l.getAll().size();
	// 	String email = "getAll.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
	// 	int countPersons = l.getAll().size();
    //
	// 	assertEquals(countInitPersons + 1, countPersons);
	// }
    //
	// @Test
	// public void test_getAllEmail() {
	// 	int countInitEmail = l.getAllEmails().size();
	// 	String email = "getAllEmails.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
	// 	int countEmail = l.getAllEmails().size();
    //
	// 	assertEquals(countInitEmail + 1, countEmail);
	// }
    //
	// @Test
	// public void test_findExist() {
	// 	String email = "iExist.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
	// 	Person find = l.find(email);
    //
	// 	assertEquals(email, find.getEmail());
	// }
    //
	// @Test(expected=IndexOutOfBoundsException.class)
	// public void test_findDoesntExist() {
	// 	l.find("gjgji.iugh@guizeauz.com");
	// }
    //
	// @Test
	// public void test_isValid() {
	// 	String email = "turpis@leoin.edu";
	// 	boolean valid = l.isValid(email, "turpis");
    //
	// 	assertEquals(true, valid);
	// }
    //
	// @Test
	// public void test_isInValid() {
	// 	String email = "turpis@leoin.edu";
	// 	boolean valid = l.isValid(email, "pistur");
    //
	// 	assertEquals(false, valid);
	// }
    //
	// @Test
	// public void test_exist() {
	// 	String email = "igezExist.lebosky@gmail.com";
	// 	Person person = new Person("Lebosky", "Mathieu", email);
	// 	l.create(person, l.passFromEmail(email));
	// 	boolean find = l.exists(email);
    //
	// 	assertEquals(true, find);
	// }
    //
	// @Test
	// public void test_Doesntexist() {
	// 	boolean find = l.exists("rtgrtgtrg@grtgrt.com");
    //
	// 	assertEquals(false, find);
	// }

}
