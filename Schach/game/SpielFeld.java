package game;

import java.util.ArrayList;

public class SpielFeld {

	private Feld[][] mat = new Feld[8][8];
	private boolean werAmZug = true;
	private boolean moeglich = false;
	
	public boolean isMoeglich() {
		return moeglich;
	}

	public void setMoeglich(boolean moeglich) {
		this.moeglich = moeglich;
	}

	public boolean isWerAmZug() {
		return werAmZug;
	}

	public void setWerAmZug(boolean werAmZug) {
		this.werAmZug = werAmZug;
	}

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
		int x = 0;
		int y = 0;
		String[] w = { "BW", "TW", "KW", "DW", "LW", "SW" };
		String[] b = { "BB", "TB", "KB", "DB", "LB", "SB" };
		String[] auswahl = w;
		if (!werAmZug) {
			auswahl = b;
		}

		ArrayList<Boolean> werte = new ArrayList<Boolean>();

		// xrechts
		for (int i = x; i < 8; i++) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
					}
				}
			}
		}

		// xlinks
		for (int i = x; i >= 0; i--) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// yoben
		for (int i = y; i < 8; i++) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// yunten
		for (int i = y; i >= 0; i--) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// d1NachOben
		for (int i = x; i < 8; i++) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// d1NachUnten
		for (int i = x; i >= 0; i--) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// d2NachOben
		for (int i = x; i < 8; i++) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		// d2Nachunten
		for (int i = x; i >= 0; i--) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(true);
					}
				}
			}
		}

		int anz = 0;

		for (int i = 0; i < werte.size(); i++) {
			if (werte.get(i)) {
				anz++;
			}
			if (anz == werte.size()) {
				return true;
			}
		}

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
			
			
			if (f.farbeWeiss == werAmZug) {
				if (f.spielzugMoeglich(this, von, nach)) {
					mat[nach.getY()][nach.getX()] = f;
					mat[von.getY()][von.getX()] = new Feld();
					f.setBewegt(true);
				}
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

	public char boolToChar(boolean werAmZug) {
		char color = 0;
		if (werAmZug == true) {
			color = 'W';
		} else if (werAmZug == false) {
			color = 'B';
		}
		return color;
	}
}
