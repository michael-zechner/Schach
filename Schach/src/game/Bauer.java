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
//		if(von.getY() > nach.getY() && von.getX() > nach.getX()) {
//			return false;
//		}
//		return super.spielzugMoeglich(sp, von, nach);
		if(farbeWeiss) {
			if(von.getY()+1 == nach.getY() && von.getX() == nach.getX()) {
				return true;
			}
		}
		if(!farbeWeiss) {
			if(von.getY()-1 == nach.getY() && von.getX() == nach.getX()) {
				return true;
			}
		}

		return false;
	}
}
