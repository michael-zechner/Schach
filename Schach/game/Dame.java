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

		Laeufer l = new Laeufer(farbeWeiss, bewegt);
		if (l.spielzugMoeglich(sp, von, nach)) {
			return l.spielzugMoeglich(sp, von, nach);
		} else {

			Turm t = new Turm(farbeWeiss, bewegt);
			if (t.spielzugMoeglich(sp, von, nach)) {
				return t.spielzugMoeglich(sp, von, nach);
			}
		}

		if (!super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}

		return false;
	}
}
