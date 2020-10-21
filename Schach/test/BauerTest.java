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

	//Test für Schwarz
	@Test
	public void bewegungEinFeldSchwarz() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(5, 0));
		assertTrue(ok1);
	}
	
	@Test
	public void bewegungEinFeldWeissTest() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		System.out.println(b1.toString());
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(3, 0));
		assertTrue(ok1);
	}
	@Test
	public void bewegungZweiFelderAnfangSchwarz() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		b1.setBewegt(false);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(4, 0));
		assertTrue(ok1);
	}

	@Test
	public void bewegungZweiFelderSchwarzFail() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(4, 0));
		assertFalse(ok1);
	}
	
	@Test
	public void FigurSchlagenSchwarzRechts() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(5, 1));
		assertTrue(ok1);
	}
	
	//Array außerhalb darum Überprüfung nicht möglich
	@Test
	public void FigurSchlagenSchwarzLinksFailWeilRand() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(5, -1));
		assertFalse(ok1);
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
		sp.ausgabe();
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
	public void FigurSchlagenWeissRechts() {
		Bauer b1 = (Bauer) sp.getFeld(3, 2);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(3, 2), new Position(4, 3));
		assertTrue(ok1);
	}
	@Test
	public void FigurSchlagenWeissLinks() {
		Bauer b1 = (Bauer) sp.getFeld(3, 2);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(3, 2), new Position(4, 1));
		assertTrue(ok1);
	}
	
	//Array außerhalb darum Überprüfung nicht möglich
	@Test
	public void FigurSchlagenWeissLinksFailWeilRand() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(2, -1));
		assertFalse(ok1);
	}
	
	@Test
	public void FigurvonNachPositionGleich() {
		Bauer b1 = (Bauer) sp.getFeld(1, 0);
		System.out.println(b1.toString());
		b1.setBewegt(true);
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(1, 0), new Position(1, 0));
		assertFalse(ok1);
	}
}
