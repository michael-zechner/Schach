package test;

import java.io.FileNotFoundException;

import game.SpielFeld;
import game.SpielFeldIO;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		SpielFeld sp = SpielFeldIO.einlesen("start.txt");
		SpielFeld start = SpielFeldIO.einlesen("start.txt");
		
		sp.setWerAmZug(true);
		sp.spielzug("51-53");
		

	}
}
