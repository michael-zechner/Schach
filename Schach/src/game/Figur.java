package game;

public abstract class Figur extends Feld {

	public boolean farbeWeiss;
	public boolean bewegt;

	public Figur(boolean farbeWeiss, boolean bewegt) {
		super();
		this.farbeWeiss = farbeWeiss;
		this.bewegt = bewegt;
	}

	@Override
	public String toString() {
		if (farbeWeiss)
			return "W";
		return "B";
	}

	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		return false;
	}

	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		return false;
	}
}
