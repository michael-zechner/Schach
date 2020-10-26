package game;

public class Koenig extends Figur {

	public Koenig(boolean farbeWeiss, boolean bewegt) {
		super(farbeWeiss, bewegt);
	}

	@Override
	public String toString() {
		return "K" + super.toString();
	}

	@Override
	public boolean spielZug(SpielFeld sp, Position von, Position nach) {
		return super.spielZug(sp, von, nach);
	}

	public boolean rochade(SpielFeld sp, Position von, Position nach) {
		
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());
		Turm t = new Turm(farbeWeiss, bewegt);
		
		// TODO Rochade funktioniert so nichtmehr, man muss noch prüfen ob zwischen von
		// und nach eine figur ist und dann die figur so umschreiben dass man
		// grundsätzlich auf ein eigenes feld darf
		if (farbeWeiss) {
			if ((von.getX() == 4 && von.getY() == 0) && (sp.getMat()[von.getY()][von.getX() + 3] instanceof Turm)
					&& nach.getX() == 7 && nach.getY() == 0) {
				t.spielZug(sp, new Position(0, 7), new Position(0,6));
				spielZug(sp, new Position(0,4), new Position(0, 7));
			}
			if ((von.getX() == 4 && von.getY() == 0) && sp.getMat()[von.getY()][von.getX() - 4] instanceof Turm
					&& nach.getX() == 0 && nach.getY() == 0) {
				System.out.println("I will mi bewegen2");
				return true;
			}
		}
		if (!farbeWeiss) {
			if (von.getX() == 4 && von.getY() == 7 && sp.getMat()[von.getY()][von.getX() + 3] instanceof Turm
					&& nach.getX() == 7 && nach.getY() == 7) {
				System.out.println("I will mi bewegen3");
				return true;
			}
			if ((von.getX() == 4 && von.getY() == 0) && sp.getMat()[von.getY()][von.getX() - 4] instanceof Turm
					&& nach.getX() == 0 && nach.getY() == 7) {
				System.out.println("I will mi bewegen4");
				return true;
			}
		}
		return (absX <= 1 && absY <= 1);
	}
	
	@Override
	public boolean spielzugMoeglich(SpielFeld sp, Position von, Position nach) {

		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());


		if (!super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}
		if (!bewegt) {
			return rochade(sp, von, nach);
		} else {
			return (absX <= 1 && absY <= 1);
		}
	}
}