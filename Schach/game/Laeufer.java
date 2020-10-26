	package game;

public class Laeufer extends Figur {

	public Laeufer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
	}

	@Override
	public String toString() {
		return "L" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());

		int wieweitX = 0;
		int wieweitY = 0;
		Boolean minus = null;
		Boolean minus2 = null;

		if (von.getX() == nach.getX() && von.getY() == nach.getY()) {
			return false;
		}

		if (von.getX() < nach.getX() && von.getY() < nach.getY()) {
			/* Also von unten links nach oben rechts */
			minus = new Boolean(true);
		}

		if (von.getX() < nach.getX() && von.getY() > nach.getY()) {
			/* Also von oben links nach unten rechts */
			minus = new Boolean(false);
		}

		if (von.getX() > nach.getX() && von.getY() < nach.getY()) {
			/* Also von unten rechts nach oben links */
			minus2 = new Boolean(true);
		}

		if (von.getX() > nach.getX() && von.getY() > nach.getY()) {
			/* Also von oben rechts nach unten links */
			minus2 = new Boolean(false);
		}

		/* Also von oben links nach unten rechts */
		if (minus != null && minus.booleanValue() == false) {
			int i = von.getX();
			int a = von.getY();
			while (i != nach.getX() && a != nach.getY() && !((Figur) sp.getFeld(a - 1, i + 1) instanceof Figur)) {
				wieweitX++;
				wieweitY++;
				a--;
				i++;
			}
			if (i < 7 && a != 0 && (Figur) sp.getFeld(a - 1, i + 1) instanceof Figur
					&& ((Figur) sp.getFeld(a - 1, i + 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
				wieweitY++;
			}
		}

		/* Also von unten links nach oben rechts */
		if (minus != null && minus.booleanValue() == true) {
			int i = von.getX();
			int a = von.getY();
			while (i != nach.getX() && a != nach.getY() && !((Figur) sp.getFeld(a + 1, i + 1) instanceof Figur)) {
				wieweitX++;
				wieweitY++;
				a++;
				i++;
			}
			if (i < 7 && a < 7 && (Figur) sp.getFeld(a + 1, i + 1) instanceof Figur
					&& ((Figur) sp.getFeld(a + 1, i + 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
				wieweitY++;
			}
		}

		/* Also von unten rechts nach oben links */
		if (minus2 != null && minus2.booleanValue() == true) {
			int i = von.getX();
			int a = von.getY();
			while (i != nach.getX() && a != nach.getY() && !((Figur) sp.getFeld(a + 1, i - 1) instanceof Figur)) {
				wieweitX++;
				wieweitY++;
				a++;
				i--;
			}
			if (i != 0 && a < 7 && (Figur) sp.getFeld(a + 1, i - 1) instanceof Figur
					&& ((Figur) sp.getFeld(a + 1, i - 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
				wieweitY++;
			}
		}

		/* Also von oben rechts nach unten links */
		if (minus2 != null && minus2.booleanValue() == false) {
			int i = von.getX();
			int a = von.getY();
			while (i != nach.getX() && a != nach.getY() && !((Figur) sp.getFeld(a - 1, i - 1) instanceof Figur)) {
				wieweitX++;
				wieweitY++;
				a--;
				i--;
			}
			if (i != 0 && a != 0 && (Figur) sp.getFeld(a - 1, i - 1) instanceof Figur
					&& ((Figur) sp.getFeld(a - 1, i - 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
				wieweitY++;
			}
		}
		
		if((absX == wieweitX && absY == wieweitY)) {
			return true;
		}
		
		if (!super.spielzugMoeglich(sp, von, nach) || super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}

		return super.spielzugMoeglich(sp, von, nach);
	}
}
