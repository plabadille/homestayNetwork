package tests;

import static org.junit.Assert.*;

import logements.Appartement;
import logements.LogementDBStub;
import logements.Maison;

import org.junit.Test;

public class TestLogementDBStub {
	protected static LogementDBStub l = new LogementDBStub();
	
	//Test d'ajout d'appartement:
	//---------------------------
	@Test
	public void test_existAppartement() {
		Appartement appartement = new Appartement("France", 80, 4, "truc sur Mer");
		l.addLogement(appartement);
		assertEquals(true, l.exist(appartement));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionAppartement1() {
		new Appartement("France", -3, 4, "truc sur Mer");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionAppartement2() {
		new Appartement("France", 5, -4, "truc sur Mer");
	}
	
	//Test d'ajout de maison:
	//-----------------------
	
	@Test
	public void test_existMaison() {
		Maison maison = new Maison("France", 75, 4, "truc  ger sur Lac", 200);
		l.addLogement(maison);
		assertEquals(true, l.exist(maison));
	}
	
	@Test
	public void test_existMaisonWithoutGarden() {
		Maison maison = new Maison("France", 75, 4, "trucge gesur Lac", 0);
		l.addLogement(maison);
		assertEquals(true, l.exist(maison));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionMaison1() {
		new Maison("France", -3, 4, "truc sur gerg Mer", 200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionMaison2() {
		new Maison("France", 5, -4, "truegrc sur Mer", 200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionMaison3() {
		new Maison("France", 70, 4, "truc sur Megergr", -2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionDoublon() {
		l.addLogement(new Maison("France", 70, 4, "truc sur Megergr", 500));
		l.addLogement(new Maison("France", 70, 4, "truc sur Megergr", 500));
	}
	
	//Tests getAll:
	//-------------
	@Test
	public void test_retrieveAllCountHouse() {
		int countInitLogement = l.getAll().size();
		l.addLogement(new Maison("France", 80, 5, "truc", 800));
		int countLogements = l.getAll().size();
		
		assertEquals(countInitLogement + 1, countLogements);
	}
	
	@Test
	public void test_retrieveAllCountApartment() {
		int countInitLogement = l.getAll().size();
		l.addLogement(new Appartement("France", 80, 5, "545f ez 1fez8 14f8zef1ez8f48 ze11 fez 1f8ze"));
		int countLogements = l.getAll().size();
		
		assertEquals(countInitLogement + 1, countLogements);
	}
	
	@Test
	public void test_retrieveAllCountBoth() {
		int countInitLogement = l.getAll().size();
		l.addLogement(new Maison("France", 80, 5, "fez fez  787478 187fez78 1fz", 800));
		l.addLogement(new Appartement("France", 80, 5, "561f89161 fze1 89f1ze 9181981"));
		int countLogements = l.getAll().size();
		
		assertEquals(countInitLogement + 2, countLogements);
	}
	
	//Tests find:
	//-----------
	@Test
	public void test_findAppartementExist() {
		Appartement appartement = new Appartement("France", 80, 5, "jfezh ,lfa 55 faz");
		l.addLogement(appartement);
		assertEquals(appartement, l.find("jfezh ,lfa 55 faz"));
	}
	
	@Test
	public void test_findMaisonExist() {
		Maison maison = new Maison("France", 80, 5, "la la 80 , truc", 800);
		l.addLogement(maison);
		assertEquals(maison, l.find("la la 80 , truc"));
	}
	
	@Test
	public void test_findlogementWitchDontExist() {
		assertEquals(null, l.find("trugfaigho 258645 gagkjkjehgl 561ga ofnia"));
	}
	
	//Tests delete:
	//-------------
	@Test
	public void test_deleteAppartement() {
		Appartement appartement = new Appartement("France", 80, 5, "fze 158935fz5ef82fze4fezf218fz8");
		l.addLogement(appartement);
		l.delete("fze 158935fz5ef82fze4fezf218fz8");
		assertEquals(null, l.find("fze 158935fz5ef82fze4fezf218fz8"));
	}
	
	@Test
	public void test_deleteMaison() {
		Maison maison = new Maison("France", 80, 5, "45648g1re ger81ger1 8919g1erg", 800);
		l.addLogement(maison);
		l.delete("45648g1re ger81ger1 8919g1erg");
		assertEquals(null, l.find("45648g1re ger81ger1 8919g1erg"));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsExceptionDelete() {
		l.delete("51478g4r 4g89re4gre4 g8489er 48");
	}
	
}
