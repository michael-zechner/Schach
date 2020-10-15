package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Laeufer;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;
import game.Springer;

class LaeuferTest {
	private static SpielFeld sp;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("LaueferTest.txt");
	}

	@Test
	public void testLäufer2() {
		Laeufer l1 = (Laeufer) sp.getFeld(5, 0);
		System.out.println(l1.toString());
		boolean ok1 = l1.spielzugMoeglich(sp, new Position(5, 0), new Position(0, 5));
		assertTrue(ok1);
	}
	
	@Test
	public void testLäufer2Fail() {
		Laeufer l1 = (Laeufer) sp.getFeld(0, 5);
		System.out.println(l1.toString());
		boolean ok1 = l1.spielzugMoeglich(sp, new Position(0, 5), new Position(5, 0));
		assertFalse(ok1);
	}
	
	@Test
	public void testLäufer1() {
		Laeufer l1 = (Laeufer) sp.getFeld(0, 2);
		System.out.println(l1.toString());
		boolean ok1 = l1.spielzugMoeglich(sp, new Position(0, 2), new Position(5, 7));
		assertTrue(ok1);
	}
	
	@Test
	public void testLäufer1Fail() {
		Laeufer l1 = (Laeufer) sp.getFeld(5, 7);
		System.out.println(l1.toString());
		boolean ok1 = l1.spielzugMoeglich(sp, new Position(5, 7), new Position(0, 2));
		assertFalse(ok1);
	}
}
