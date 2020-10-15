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
		
		for (int i = 0; i < 8; i++) {
			if(!(sp.getMat()[i][i] instanceof Figur)) {
				wieweitX++;
				wieweitY++;
			}
		}
		
		return super.spielzugMoeglich(sp, von, nach) || (absX <= wieweitX && absY <= wieweitY);
	}
}
