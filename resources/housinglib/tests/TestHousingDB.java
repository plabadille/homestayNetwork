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


import org.junit.Test;
import org.junit.Before;

public class TestHousingDB {
	protected static SQLHousingDB l;

    @Before
    public void initialize() {
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

	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionApartment1() {
	// 	new Apartment("France", -3, 4, "truc sur Mer");
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionApartment2() {
	// 	new Apartment("France", 5, -4, "truc sur Mer");
	// }
    //
	// //Test d'ajout de home:
	// //-----------------------
    //
	// @Test
	// public void test_existHome() {
	// 	Home home = new Home("France", 75, 4, "truc  ger sur Lac", 200);
	// 	l.add(home);
	// 	assertNotNull(l.find(home.getAddress()));
	// }
    //
	// @Test
	// public void test_existHomeWithoutGarden() {
	// 	Home home = new Home("France", 75, 4, "trucge gesur Lac", 0);
	// 	l.add(home);
	// 	assertNotNull(l.find(home.getAddress()));
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionHome1() {
	// 	new Home("France", -3, 4, "truc sur gerg Mer", 200);
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionHome2() {
	// 	new Home("France", 5, -4, "truegrc sur Mer", 200);
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionHome3() {
	// 	new Home("France", 70, 4, "truc sur Megergr", -2);
	// }
    //
	// @Test(expected=IllegalArgumentException.class)
	// public void testIllegalArgumentExceptionDoublon() {
	// 	l.add(new Home("France", 70, 4, "truc sur Megergr", 500));
	// 	l.add(new Home("France", 70, 4, "truc sur Megergr", 500));
	// }
    //
	// //Tests getAll:
	// //-------------
	// @Test
	// public void test_retrieveAllCountHouse() {
	// 	int countInitHousing = l.getAll().size();
	// 	l.add(new Home("France", 80, 5, "truc", 800));
	// 	int countHousings = l.getAll().size();
    //
	// 	assertEquals(countInitHousing + 1, countHousings);
	// }
    //
	// @Test
	// public void test_retrieveAllCountApartment() {
	// 	int countInitHousing = l.getAll().size();
	// 	l.add(new Apartment("France", 80, 5, "545f ez 1fez8 14f8zef1ez8f48 ze11 fez 1f8ze"));
	// 	int countHousings = l.getAll().size();
    //
	// 	assertEquals(countInitHousing + 1, countHousings);
	// }
    //
	// @Test
	// public void test_retrieveAllCountBoth() {
	// 	int countInitHousing = l.getAll().size();
	// 	l.add(new Home("France", 80, 5, "fez fez  787478 187fez78 1fz", 800));
	// 	l.add(new Apartment("France", 80, 5, "561f89161 fze1 89f1ze 9181981"));
	// 	int countHousings = l.getAll().size();
    //
	// 	assertEquals(countInitHousing + 2, countHousings);
	// }
    //
	// //Tests find:
	// //-----------
	// @Test
	// public void test_findApartmentExist() {
	// 	Apartment apartment = new Apartment("France", 80, 5, "jfezh ,lfa 55 faz");
	// 	l.add(apartment);
	// 	assertEquals(apartment, l.find("jfezh ,lfa 55 faz"));
	// }
    //
	// @Test
	// public void test_findHomeExist() {
	// 	Home home = new Home("France", 80, 5, "la la 80 , truc", 800);
	// 	l.add(home);
	// 	assertEquals(home, l.find("la la 80 , truc"));
	// }
    //
	// @Test
	// public void test_findhousingWitchDontExist() {
	// 	assertEquals(null, l.find("trugfaigho 258645 gagkjkjehgl 561ga ofnia"));
	// }
    //
	// //Tests delete:
	// //-------------
	// @Test
	// public void test_deleteApartment() {
	// 	Apartment apartment = new Apartment("France", 80, 5, "fze 158935fz5ef82fze4fezf218fz8");
	// 	l.add(apartment);
	// 	l.delete(apartment);
	// 	assertEquals(null, l.find("fze 158935fz5ef82fze4fezf218fz8"));
	// }
    //
	// @Test
	// public void test_deleteHome() {
	// 	Home home = new Home("France", 80, 5, "45648g1re ger81ger1 8919g1erg", 800);
	// 	l.add(home);
	// 	l.delete(home);
	// 	assertEquals(null, l.find("45648g1re ger81ger1 8919g1erg"));
	// }
    //
	// @Test(expected=IndexOutOfBoundsException.class)
	// public void testIndexOutOfBoundsExceptionDelete() {
	// 	l.delete(new Apartment("Italy", 56, 4, "unknown"));
	// }

}
