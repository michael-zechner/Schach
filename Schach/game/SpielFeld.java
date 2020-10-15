package game;

public class SpielFeld {

	private Feld[][] mat = new Feld[8][8];
	private boolean werAmZug;

	public Feld[][] getMat() {
		return mat;
	}

	public SpielFeld(Feld[][] mat, boolean werAmZug) {
		super();
		this.mat = mat;
		this.werAmZug = werAmZug;
	}

	public void ausgabe() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mat[i][j].toString();
			}
			System.out.println();
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

	public Feld getFeld(int i, int j) {
		if (mat[i][j] instanceof Figur) {
			Figur f = (Figur) mat[i][j];
			return f;
		}
		return null;
	}
}
