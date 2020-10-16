package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.Dame;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;

public class DameTest {
	
	private static SpielFeld sp;
	
	@Before
	public void setUp() throws Exception {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("DameTest.txt");
	}
	
	@Test
	public void ZweiNachObenDreiNachRechts() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(5, 7));
		assertTrue(ok1);
	}
	
	@Test
	public void VierNachLinks() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(5, 0));
		assertTrue(ok1);
	}
	
	@Test
	public void ZweiNachObenEinsNachLinksFail() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(5, 3));
		assertFalse(ok1);
	}
	
	@Test
	public void ZweiNachObenZweiNachLinks() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(5, 2));
		assertTrue(ok1);
	}
	
	@Test
	public void DreiNachUntenDreiNachRechtsFail() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(0, 7));
		assertFalse(ok1);
	}
	
	
	@Test
	public void ZuNullNull() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(0, 0));
		assertFalse(ok1);
	}
	
	@Test
	public void bewegungstest() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(7, 4));
		assertFalse(ok1);
	}
	
	@Test
	public void bewegungstest1() {
		Dame t1 = (Dame) sp.getFeld(3, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(3, 4), new Position(0, 4));
		assertFalse(ok1);
	}
	
	@Test
	public void bewegungstest2() {
		Dame t1 = (Dame) sp.getFeld(0, 4);
		System.out.println(t1.toString());
		boolean ok1 = t1.spielzugMoeglich(sp, new Position(0, 4), new Position(2, 4));
		assertFalse(ok1);
	}
}
