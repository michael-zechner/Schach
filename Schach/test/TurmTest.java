package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;
import game.Turm;

public class TurmTest {
	
	private static SpielFeld sp;

	@Before
	public void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("TurmTest.txt");
	}
	
	@Test
	public void bewegungDreiFeldernachoben() {
		Turm t1 = (Turm) sp.getFeld(2, 0);
		System.out.println(t1.toString());
		
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(2, 0), new Position(5, 0));
		assertTrue(ok1);
	}
	
	@Test
	public void bewegungDreiFelderzurSeite() {
		Turm t1 = (Turm) sp.getFeld(2, 0);
		System.out.println(t1.toString());
		
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(2, 0), new Position(2, 5));
		assertTrue(ok1);
	}
}
