package test;

import java.io.FileNotFoundException;

import game.SpielFeld;
import game.SpielFeldIO;

public class test {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		SpielFeldIO spIO = new SpielFeldIO();
		SpielFeld sp = spIO.einlesen("start.txt");
		
		sp.ausgabe();
		
	}
}
