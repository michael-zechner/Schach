package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Bauer;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;

class BauerTest {
	SpielFeld sp;

	@BeforeEach
	void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("BauerTest.txt");
	}

	@Test
	void bewegungEinFeldSchwarz() {
		Bauer b1 = (Bauer) sp.getFeld(6, 0);
		System.out.println(b1.toString());
		boolean ok1 = b1.spielzugMoeglich(sp, new Position(6, 0), new Position(5, 0));
		assertTrue(ok1);
	}

}
