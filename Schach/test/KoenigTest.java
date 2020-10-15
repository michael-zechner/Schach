package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.Koenig;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;

public class KoenigTest {
	
	
	private static SpielFeld sp;

	@Before
	public void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("KoenigTest.txt");
	}
	
	@Test
	public void EinsNachOben() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(4, 3));
		assertTrue(ok1);
	}
	
	@Test
	public void EinsNachLinks() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(3, 2));
		assertTrue(ok1);
	}
	
	@Test
	public void EinsNachRechts() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(3, 2));
		assertTrue(ok1);
	}
	
	@Test
	public void EinsNachRechtsEinsNachOben() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(4, 4));
		assertTrue(ok1);
	}
	
	@Test
	public void EinsNachLinksEinsNachUnten() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(2, 2));
		assertTrue(ok1);
	}
	
	@Test
	public void EinsNachUntenZweiNachRechtsFail() {
		Koenig t1 = (Koenig) sp.getFeld(3, 3);
		t1.setBewegt(true);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 3), new Position(2, 5));
		assertFalse(ok1);
	}
}
