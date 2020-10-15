package game;

public class Springer extends Figur{
	
	public Springer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "S" + super.toString();
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
		
		
		
		return super.spielzugMoeglich(sp, von, nach) ||  (absX == 2 && absY == 1);
	}
	//TODO wir müssen überall noch die randbedingungen machen
}
