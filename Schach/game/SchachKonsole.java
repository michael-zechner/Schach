package game;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SchachKonsole {
	private static SpielFeld sp;

	public static void main(String[] args) throws FileNotFoundException {
		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("start.txt");
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			String zug = s.next();
			Zug(zug);
		}
		
		
	

	}
	
	public static void Zug(String zug) {
		sp.spielzug(zug);
		sp.ausgabe();
	}

}
