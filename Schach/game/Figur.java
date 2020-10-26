package game;

import java.util.ArrayList;

public abstract class Figur extends Feld {

	public boolean farbeWeiss;
	public boolean bewegt;
	public ArrayList<String> suggestions = new ArrayList<String>();

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
		return spielzugMoeglich(sp, von, nach);
	}

	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		if (von.getX() == nach.getX() && von.getY() == nach.getY()) {
			return false;
		}

		Figur fvon = (Figur) sp.getFeld(von);
		Figur fnach;
		if (sp.getFeld(nach) instanceof Figur) {
			fnach = (Figur) sp.getFeld(nach);
			if (fvon.isFarbeWeiss() == fnach.isFarbeWeiss()) {
				return false;
			}
		}

		return true;
	}

	public ArrayList<String> suggest(SpielFeld sp, Position von, boolean farbeWeiss) {
		suggestions.clear();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Position nach = new Position(j, i);
				setFarbeWeiss(farbeWeiss);
				if (sp.getFeld(von) instanceof Figur && spielzugMoeglich(sp, von, nach)) {
					suggestions.add(nach.getX() + "" + nach.getY());

				}

				if (sp.getFeld(von) instanceof Koenig && ((Koenig) sp.getFeld(von)).rochade(sp, von, nach)) {
					System.out.println("Bin koenig" + nach.getX() + " " + nach.getY());
					suggestions.add(nach.getX() + "" + nach.getY());
				}

			}
		}
		return suggestions;
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

	public ArrayList<String> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(ArrayList<String> suggestions) {
		this.suggestions = suggestions;
	}

}
