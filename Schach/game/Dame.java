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
		
		for (int i = 1; i < 9; i++) {
			if(!(sp.getMat()[i][von.getY()] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = 1; i < 9; i++) {
			if(!(sp.getMat()[von.getX()][i] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		return super.spielzugMoeglich(sp, von, nach) && absX <= wieweitX && absY <= wieweitY;
		//TODO Mach des ganze mit || und klammer absx und absy ein
	}
}
