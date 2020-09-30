package game;

import java.io.FileNotFoundException;

public class SpielFeldIO {

	private static Feld[][] mat;
	static public SpielFeld einlesen(String fName) throws FileNotFoundException {
		leseFeld(f)
	}

	private static Feld leseFeld(String f) {
		char a = f.charAt(0);
		char b = f.charAt(1);
		boolean farbeWeiss = false;
		Feld feld = new Feld();

		if (b == 'W') {
			farbeWeiss = true;
		}

		switch (a) {
		case 'B':
			Bauer bauer = new Bauer(farbeWeiss, false);
			break;
		case 'D':
			Dame dame = new Dame(farbeWeiss, false);
			break;
		case 'K':
			Koenig koenig = new Koenig(farbeWeiss, false);
		case 'L':
			Laeufer laeufer = new Laeufer(farbeWeiss, false);
			break;
		case 'S':
			Springer springer = new Springer(farbeWeiss, false);
			break;
		case 'T':
			Turm turm = new Turm(farbeWeiss, false);
			break;

		default:
			break;
		}
		
		mat[0][0] = ;

	}
}
