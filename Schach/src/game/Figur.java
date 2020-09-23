package game;

public abstract class Figur {
	
	public boolean farbeWeiss;
	public boolean bewegt;
	
	public boolean spielZug(SpielFeld sp, Position von, Position nach){
		return false;
	}
	
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		return false;
	}
}
