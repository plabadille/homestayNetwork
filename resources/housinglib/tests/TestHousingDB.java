/**
 * Housing test class
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package tests;

import static org.junit.Assert.*;

import housings.Home;
import housings.Apartment;
import housings.SQLHousingDB;
import housings.HousingsDBHandler;
import java.sql.SQLException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


import org.junit.Test;
import org.junit.Before;

public class TestHousingDB {
	protected static SQLHousingDB l;

    @Before
    public void initialize() throws SQLException {
        try {
            l = HousingsDBHandler.getDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	//Test d'ajout d'apartment:
	//---------------------------
	@Test
	public void test_existApartment() throws SQLException {
		Apartment apartment = new Apartment("France", 80, 4, "truc sur Mer");
		l.add(apartment);
		assertNotNull(l.find(apartment.getAddress()));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionApartment1() {
		new Apartment("France", -3, 4, "truc sur Mer");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionApartment2() {
		new Apartment("France", 5, -4, "truc sur Mer");
	}

	//Test d'ajout de home:
	//-----------------------

	@Test
	public void test_existHome() throws SQLException {
		Home home = new Home("France", 75, 4, "truc  ger sur Lac", 200);
		l.add(home);
		assertNotNull(l.find(home.getAddress()));
	}

	@Test
	public void test_existHomeWithoutGarden() throws SQLException {
		Home home = new Home("France", 75, 4, "trucge gesur Lac", 0);
		l.add(home);
		assertNotNull(l.find(home.getAddress()));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionHome1() {
		new Home("France", -3, 4, "truc sur gerg Mer", 200);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionHome2() {
		new Home("France", 5, -4, "truegrc sur Mer", 200);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionHome3() {
		new Home("France", 70, 4, "truc sur Megergr", -2);
	}

	@Test
	public void testIllegalArgumentExceptionDoublon() throws SQLException {
		l.add(new Home("France", 70, 4, "truc sur Megergr", 500));
		assertEquals(-1, l.add(new Home("France", 70, 4, "truc sur Megergr", 500)));
	}

	//Tests getAll:
	//-------------
	@Test
	public void test_retrieveAllCountHouse() throws SQLException {
        l.delete(l.find("truc"));
		int countInitHousing = l.getAll().size();
		l.add(new Home("France", 80, 5, "truc", 800));

		assertEquals(countInitHousing + 1, l.getAll().size());
	}

	@Test
	public void test_retrieveAllCountApartment() throws SQLException {
        l.delete(l.find("545f ez 1fez8 14f8zef1ez8f48 ze11 fez 1f8ze"));
		int countInitHousing = l.getAll().size();
		l.add(new Apartment("France", 80, 5, "545f ez 1fez8 14f8zef1ez8f48 ze11 fez 1f8ze"));

		assertEquals(countInitHousing + 1, l.getAll().size());
	}

	@Test
	public void test_retrieveAllCountBoth() throws SQLException {
        l.delete(l.find("fez fez  787478 187fez78 1fz"));
        l.delete(l.find("561f89161 fze1 89f1ze 9181981"));
		int countInitHousing = l.getAll().size();
		l.add(new Home("France", 80, 5, "fez fez  787478 187fez78 1fz", 800));
		l.add(new Apartment("France", 80, 5, "561f89161 fze1 89f1ze 9181981"));
		int countHousings = l.getAll().size();

		assertEquals(countInitHousing + 2, countHousings);
	}

	//Tests find:
	//-----------
	@Test
	public void test_findApartmentExist() throws SQLException {
		Apartment apartment = new Apartment("France", 80, 5, "jfezh ,lfa 55 faz");
		l.add(apartment);
		assertEquals(apartment, l.find("jfezh ,lfa 55 faz"));
	}

	@Test
	public void test_findHomeExist() throws SQLException {
		Home home = new Home("France", 80, 5, "la la 80 , truc", 800);
		l.add(home);
		assertEquals(home, l.find("la la 80 , truc"));
	}

	@Test
	public void test_findhousingWitchDontExist() throws SQLException {
		assertEquals(null, l.find("trugfaigho 258645 gagkjkjehgl 561ga ofnia"));
	}

	//Tests delete:
	//-------------
	@Test
	public void test_deleteApartment() throws SQLException {
		String address = "fze 158935fz5ef82fze4fezf218fz8";
		Apartment apartment = new Apartment("France", 80, 5, address);
		l.add(apartment);
		l.delete(l.find(address));
		assertEquals(null, l.find(address));
	}

	@Test
	public void test_deleteHome() throws SQLException {
		String address = "45648g1re ger81ger1 8919g1erg";
		Home home = new Home("France", 80, 5, address, 800);
		l.add(home);
		l.delete(l.find(address));
		assertEquals(null, l.find(address));
	}

	@Test
	public void testIndexOutOfBoundsExceptionDelete() throws SQLException {
		l.delete(new Apartment("Italy", 56, 4, "unknown"));
	}
}
