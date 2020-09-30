package game;

public class SpielFeld {

	private Feld[][] mat;
	private boolean werAmZug;

	public void ausgabe() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mat[i][j].toString();
			}
		}
	}

	public boolean schach() {
		return false;
	}

	public boolean schachMatt() {
		return false;
	}

	public void spielzug(String zug) {
		String[] p = zug.split("-");
		Position von = schach2koordinate(p[0]);
		Position nach = schach2koordinate(p[1]);

		if (mat[von.getX()][von.getY()] instanceof Figur) {
			Figur f = (Figur) mat[von.getX()][von.getY()];
			f.spielZug(this, von, nach);
		}
	}

	public Position schach2koordinate(String schach) {
		Position p = new Position();
		p.setX((byte) schach.charAt(0));
		p.setY((byte) schach.charAt(1));
		return p;
	}
}
