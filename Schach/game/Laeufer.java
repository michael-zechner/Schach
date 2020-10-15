package game;

public class Laeufer extends Figur {

	public Laeufer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "L" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		// TODO Auto-generated method stub
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());

		int wieweitX = 0;
		int wieweitY = 0;
		boolean minus = false;
		boolean minus2 = false;

		if (von.getX() < nach.getX() && von.getY() < nach.getY()) {
			minus = false;
		}
		if (von.getX() > nach.getX() && von.getY() > nach.getY()) {
			minus = true;
		}
		if (von.getX() < nach.getX() && von.getY() > nach.getY()) {
			minus2 = false;
		}
		if (von.getX() > nach.getX() && von.getY() < nach.getY()) {
			minus2 = true;
		}

		if (minus) {
			for (int i = von.getX(); i > nach.getX(); i--) {
				for (int j = von.getY(); j > nach.getY(); j--) {

					if (!(sp.getMat()[i][j] instanceof Figur)) {
						wieweitX++;
						wieweitY++;
					}
				}
			}
		}
		if (minus2) {
			for (int i = von.getX(); i > nach.getX(); i--) {
				for (int j = von.getY(); j < nach.getY(); j++) {

					if (!(sp.getMat()[i][j] instanceof Figur)) {
						wieweitX++;
						wieweitY++;
					}
				}
			}
		}
		if (!minus2) {
			for (int i = von.getX(); i < nach.getX(); i++) {
				for (int j = von.getY(); j > nach.getY(); j--) {

					if (!(sp.getMat()[i][j] instanceof Figur)) {
						wieweitX++;
						wieweitY++;
					}
				}
			}
		}
		if (!minus) {
			for (int i = von.getX(); i < nach.getX(); i++) {
				for (int j = von.getY(); j < nach.getY(); j++) {

					if (!(sp.getMat()[i][j] instanceof Figur)) {
						wieweitX++;
						wieweitY++;
					}
				}
			}
		}

		return super.spielzugMoeglich(sp, von, nach) || (absX <= wieweitX && absY <= wieweitY);
	}
}
