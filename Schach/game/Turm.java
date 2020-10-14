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
		// TODO Auto-generated method stub
		return super.spielzugMoeglich(sp, von, nach);
	}
}
