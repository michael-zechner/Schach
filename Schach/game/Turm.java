package game;

public class Turm extends Figur {
	public Turm(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "T" + super.toString();
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
		
		for (int i = von.getX(); i <= nach.getX(); i++) {
			if(!(sp.getMat()[von.getY()][i] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = von.getY(); i <= nach.getY(); i++) {
			if(!(sp.getMat()[i][von.getX()] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		for (int i = von.getX(); i >= nach.getX(); i--) {
			if(!(sp.getMat()[von.getY()][i] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = von.getY(); i >= nach.getY(); i--) {
			if(!(sp.getMat()[i][von.getX()] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		if(!super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}

		
		return (absX <= wieweitX && absY <= wieweitY);
	}
}
