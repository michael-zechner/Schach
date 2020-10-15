package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;
import game.Turm;

public class TurmTest {
	
	private static SpielFeld sp;

	@BeforeEach
	public void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("TurmTest.txt");
	}
	
	@Test
	public void bewegungDreiFelder() {
		Turm t1 = (Turm) sp.getFeld(2, 0);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(2, 0), new Position(5, 0));
		assertTrue(ok1);
	}
}
