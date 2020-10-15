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
		if (von.getX() == nach.getX() && von.getY() == nach.getY()) {
			return false;
		}

		Figur fvon = (Figur) sp.getFeld(von.getY(), von.getX());
		Figur fnach;
		if(sp.getFeld(nach.getY(), nach.getX()) instanceof Figur) {
			fnach = (Figur) sp.getFeld(nach.getY(), nach.getX());
			if (fvon.isFarbeWeiss() == fnach.isFarbeWeiss()) {
				return false;
			}
		}

		return true;
	}

	public boolean isFarbeWeiss() {
		return farbeWeiss;
	}

	public void setFarbeWeiss(boolean farbeWeiss) {
		this.farbeWeiss = farbeWeiss;
	}

	public boolean isBewegt() {
		return bewegt;
	}

	public void setBewegt(boolean bewegt) {
		this.bewegt = bewegt;
	}

}
