package game;

public class Bauer extends Figur {

	public Bauer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}
	
	public boolean getFigure(SpielFeld sp, Position von, Position nach) {
		if (farbeWeiss) {
			if (nach.getY() == 7) {
				return true;
			}
			
		}
		if (!farbeWeiss) {
			if (nach.getY() == 0) {
				return true;
			}
		}
		return false;
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

		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());
		if (absY == 1 && absX == 0) {
			if (sp.getMat()[(int) nach.getY()][(int) nach.getX()] instanceof Figur) {
				return false;
			}
		}

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

			if (von.getX() != 0) {

				if (sp.getMat()[(int) von.getY() + 1][(int) von.getX() - 1] instanceof Figur
						&& von.getY() + 1 == nach.getY() && von.getX() - 1 == nach.getX()) {
					Figur schlag = (Figur) sp.getFeld((int) von.getY() + 1, (int) von.getX() - 1);
					if (schlag.isFarbeWeiss() == false) {

						return true;
					}
				}
			}
			if (von.getX() < 7) {
				if (sp.getMat()[(int) von.getY() + 1][(int) von.getX() + 1] instanceof Figur
						&& von.getY() + 1 == nach.getY() && von.getX() + 1 == nach.getX()) {
					Figur schlag = (Figur) sp.getFeld((int) von.getY() + 1, (int) von.getX() + 1);
					if (schlag.isFarbeWeiss() == false) {

						return true;
					}
				}
			}
		}
		if (!farbeWeiss) {
			if (von.getY() - 1 == nach.getY() && von.getX() == nach.getX()) {
				return true;
			}
			if (von.getX() != 0) {
				if (sp.getMat()[(int) von.getY() - 1][(int) von.getX() - 1] instanceof Figur
						&& von.getY() - 1 == nach.getY() && von.getX() - 1 == nach.getX()) {
					Figur schlag = (Figur) sp.getFeld((int) von.getY() - 1, (int) von.getX() - 1);
					;
					if (schlag.isFarbeWeiss() == true) {

						return true;
					}
				}
			}

			if (von.getX() < 7 && sp.getMat()[(int) von.getY() - 1][(int) von.getX() + 1] instanceof Figur
					&& von.getY() - 1 == nach.getY() && von.getX() + 1 == nach.getX()) {
				Figur schlag = (Figur) sp.getFeld((int) von.getY() - 1, (int) von.getX() + 1);
				if (schlag.isFarbeWeiss() == true) {

					return true;
				}
			}
		}
		

		if (!super.spielzugMoeglich(sp, von, nach) || super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}
		
		bewegt = true;
		return super.spielzugMoeglich(sp, von, nach);
	}
}
