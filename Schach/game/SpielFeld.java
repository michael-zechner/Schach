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
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print(mat[i][j].toString());
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

		if (mat[von.getY()][von.getX()] instanceof Figur) {

			Figur f = (Figur) mat[von.getY()][von.getX()];
			System.out.println(f.toString());
			System.out.println(f.spielzugMoeglich(this, von, nach));
			if (f.spielzugMoeglich(this, von, nach)) {
				mat[nach.getY()][nach.getX()] = f;
				mat[von.getY()][von.getX()] = new Feld();
				f.setBewegt(true);
			}

		}
	}

	public Position schach2koordinate(String schach) {
		Position p = new Position();
		p.setX((byte) Byte.parseByte(String.valueOf(schach.charAt(0))));
		p.setY((byte) Byte.parseByte(String.valueOf(schach.charAt(1))));
		return p;
	}

	public Feld getFeld(int y, int x) {
		if (mat[y][x] instanceof Figur) {
			Figur f = (Figur) mat[y][x];
			return f;
		}
		return null;
	}
}
