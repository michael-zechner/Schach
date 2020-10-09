package game;

public class Bauer extends Figur {

	public Bauer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "B" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		// TODO Auto-generated method stub
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {

		if (!bewegt) {
			if (farbeWeiss) {
				if (von.getY() + 2 == nach.getY() && von.getX() == nach.getX()) {
					return true;
				}
			}
			if (!farbeWeiss) {
				if (von.getY() - 2 == nach.getY() && von.getX() == nach.getX()) {
					return true;
				}
			}
		}

		if (farbeWeiss) {
			if (von.getY() + 1 == nach.getY() && von.getX() == nach.getX()) {
				return true;
			}
			
			if(von.getX() != 0) {
				if(sp.getMat()[(int) von.getY() + 1][(int) von.getX() - 1] instanceof Figur
							&& von.getY() + 1 == nach.getY() && von.getX() - 1 == nach.getX()) {
					return true;
				}
			}
			if (sp.getMat()[(int) von.getY() + 1][(int) von.getX() + 1] instanceof Figur
					&& von.getY() + 1 == nach.getY() && von.getX() + 1 == nach.getX()
					) {
				return true;
			}
		}
		if (!farbeWeiss) {
			if (von.getY() - 1 == nach.getY() && von.getX() == nach.getX()) {
				return true;
			}
			if (von.getX() != 0) {
				if (sp.getMat()[(int) von.getY() - 1][(int) von.getX() - 1] instanceof Figur
						&& von.getY() - 1 == nach.getY() && von.getX() - 1 == nach.getX() && von.getX() != 0) {
					return true;
				}
			}

			if (sp.getMat()[(int) von.getY() - 1][(int) von.getX() + 1] instanceof Figur
					&& von.getY() - 1 == nach.getY() && von.getX() + 1 == nach.getX()) {
				return true;
			}

		}

		bewegt = true;
		return false;
	}
}
