package src.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpielFeldIO {
	private static Scanner s;

	private static Feld[][] mat = new Feld[8][8];

	static public SpielFeld einlesen(String fName) throws FileNotFoundException {
		s = new Scanner(new File(fName));

		for (int i = mat.length - 1; i >= 0; i--) {
			String a = s.nextLine();
			for (int j = 0; j < mat[0].length; j++) {
				
				String[] figuren = a.split("\\|");
				System.out.print(mat[i][j] = leseFeld(figuren[j]));
			}
			System.out.println();
		}

		return new SpielFeld(mat, false);
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
			return new Bauer(farbeWeiss, false);
		case 'D':
			return new Dame(farbeWeiss, false);
		case 'K':
			return new Koenig(farbeWeiss, false);
		case 'L':
			return new Laeufer(farbeWeiss, false);
		case 'S':
			return new Springer(farbeWeiss, false);
		case 'T':
			return new Turm(farbeWeiss, false);
		default:
			return feld;
		}

	}
}
