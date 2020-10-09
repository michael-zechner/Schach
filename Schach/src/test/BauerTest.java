package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Bauer;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;

public class BauerTest {
	private static SpielFeld sp;

	@BeforeEach
	public void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("BauerTest.txt");
	}

	@Test
	public void bewegungEinFeldSchwarz() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(5, 0));
		assertTrue(ok1);
	}

	// Test für Weiss
	@Test
	public void bewegungEinFeldWeiss() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(2, 0));
		assertTrue(ok1);
	}

	@Test
	public void bewegungZweiFelderAnfangWeiss() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		b1.setBewegt(false);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(3, 0));
		assertTrue(ok1);
	}

	@Test
	public void bewegungZweiFelderWeissFail() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(3, 0));
		assertFalse(ok1);
	}
	
	@Test
	public void FigurSchlagenWeiss() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(0, 0));
		assertTrue(ok1);
	}
}
