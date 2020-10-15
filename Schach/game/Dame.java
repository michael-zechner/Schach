package game;

public class Dame extends Figur {

	public Dame(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "D" + super.toString();
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
		
		for (int i = 0; i < 8; i++) {
			if(!(sp.getMat()[i][von.getY()] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = 0; i < 8; i++) {
			if(!(sp.getMat()[von.getX()][i] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		int dwieweitX = 0;
		int dwieweitY = 0;
		
		for (int i = 0; i < 8; i++) {
			if(!(sp.getMat()[i][i] instanceof Figur)) {
				dwieweitX++;
				dwieweitY++;
			}
		}
		
		if(absX == 1 && absY == 2) {
			return false;
		}
		
		return super.spielzugMoeglich(sp, von, nach) || (absX <= wieweitX && absY <= wieweitY) || (absX <= dwieweitX && absY <= dwieweitY);
		
	}
}
