package game;

public class Springer extends Figur {

	public Springer(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
	}

	@Override
	public String toString() {
		return "S" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		return super.spielZug(sp, von, nach);
	}

	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());

			if (!super.spielzugMoeglich(sp, von, nach)) {
				return false;
			}

			return (absX == 2 && absY == 1) || (absX == 1 && absY == 2);
		
	}

	
}
