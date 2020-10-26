package game;

public class Turm extends Figur {
	public Turm(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
	}

	@Override
	public String toString() {
		return "T" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {

		/*
		 * Es muss mit der Wrapper-Boolean gearbeitet werden, da man einen "normalen"
		 * boolean ja nur true/false setzten kann. So würden immer die moeglich in der
		 * boolean = false wäre vorgeschlagen werden, was man natürlichnicht will
		 */
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());

		int wieweitX = 0;
		int wieweitY = 0;
		Boolean x = null;
		Boolean y = null;
		int xI = von.getX();
		int yI = von.getY();

		if (von.getX() == nach.getX() && von.getY() == nach.getY()) {
			return false;
		}

		if (von.getX() < nach.getX() && von.getY() == nach.getY()) {
			x = new Boolean(true);
		}

		if (von.getX() > nach.getX() && von.getY() == nach.getY()) {
			x = new Boolean(false);
		}

		if (von.getY() < nach.getY() && von.getX() == nach.getX()) {
			y = new Boolean(true);
		}

		if (von.getY() > nach.getY() && von.getX() == nach.getX()) {
			y = new Boolean(false);
		}

		if (x != null && x.booleanValue() == true) {
			int i = von.getX();
			while (i < 7 && !((Figur) sp.getFeld(yI, i + 1) instanceof Figur)) {
				wieweitX++;
				i++;

			}
			if (i < 7 && (Figur) sp.getFeld(yI, i + 1) instanceof Figur
					&& ((Figur) sp.getFeld(yI, i + 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
			}

		}

		if (x != null && x.booleanValue() == false) {
			int i = von.getX();
			while (i != nach.getX() && !((Figur) sp.getFeld(yI, i - 1) instanceof Figur)) {
				wieweitX++;
				i--;
			}
			if (i != 0 && (Figur) sp.getFeld(yI, i - 1) instanceof Figur
					&& ((Figur) sp.getFeld(yI, i - 1)).isFarbeWeiss() != farbeWeiss) {
				wieweitX++;
			}

		}

		if (y != null && y.booleanValue() == true) {
			int a = von.getY();
			while (a != nach.getY() && !((Figur) sp.getFeld(a + 1, xI) instanceof Figur)) {
				wieweitY++;
				a++;

			}
			if (a < 7 && (Figur) sp.getFeld(a + 1, xI) instanceof Figur
					&& ((Figur) sp.getFeld(a + 1, xI)).isFarbeWeiss() != farbeWeiss) {
				wieweitY++;
			}
		}

		if (y != null && y.booleanValue() == false) {
			int a = von.getY();
			while (a != nach.getY() && !((Figur) sp.getFeld(a - 1, xI) instanceof Figur)) {
				wieweitY++;
				a--;
			}
			if (a != 0 && (Figur) sp.getFeld(a - 1, xI) instanceof Figur
					&& ((Figur) sp.getFeld(a - 1, xI)).isFarbeWeiss() != farbeWeiss) {
				wieweitY++;

			}

		}

		if ((absX <= wieweitX) && (absY <= wieweitY)) {
			return true;
		}

		if (!super.spielzugMoeglich(sp, von, nach) || super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}

		return super.spielzugMoeglich(sp, von, nach);

	}
}
