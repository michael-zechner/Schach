package game;

public class Koenig extends Figur {
	
	public Koenig(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "K" + super.toString();
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
		
		if(!bewegt) {
			return super.spielzugMoeglich(sp, von, nach);
		}else {
			return super.spielzugMoeglich(sp, von, nach) && absX <= 1 && absY <=1;
		}
	}
}