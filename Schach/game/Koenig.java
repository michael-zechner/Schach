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
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {

		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());
		
		Turm t = new Turm(farbeWeiss, bewegt);
		
		if (!super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}
		if (!bewegt) {
			if(farbeWeiss) {
				if((von.getX() == 4 && von.getY() == 0) && (sp.getMat()[von.getY()][von.getX()+3] instanceof Turm)) {
					return true;
				}
				if((von.getX() == 4 && von.getY() == 0) && sp.getMat()[von.getY()][von.getX()-4] instanceof Turm) {
					return true;
				}
			}
			if(!farbeWeiss) {
				if(von.getX() == 4 && von.getY() == 7 && sp.getMat()[von.getY()][von.getX()+3] instanceof Turm){
					return true;
				}
				if((von.getX() == 4 && von.getY() == 0) && sp.getMat()[von.getY()][von.getX()-4] instanceof Turm) {
					return true;
				}
			}
			return (absX <= 1 && absY <= 1);
		} else {
			return (absX <= 1 && absY <= 1);
		}
	}
}