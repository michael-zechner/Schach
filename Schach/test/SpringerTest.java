package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Bauer;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;
import game.Springer;

class SpringerTest {
	private static SpielFeld sp;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("SpringerTest.txt");
	}

	@Test
	public void SpringerDiagonalTest1() {
		Springer s1 = (Springer) sp.getFeld(0, 6);
		System.out.println(s1.toString());
		boolean ok1 = s1.spielzugMoeglich(sp, new Position(0, 6), new Position(2, 7));
		assertTrue(ok1);
	}
	
	@Test
	public void SpringerDiagonalTest2() {
		Springer s1 = (Springer) sp.getFeld(0, 6);
		System.out.println(s1.toString());
		boolean ok1 = s1.spielzugMoeglich(sp, new Position(0, 6), new Position(1, 8));
		assertTrue(ok1);
	}
	

}
