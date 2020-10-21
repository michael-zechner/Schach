package game;

import java.util.ArrayList;

import application.Main;

public class SpielFeld {

	private Feld[][] mat = new Feld[8][8];
	private boolean werAmZug = true;
	private boolean moeglich = false;
	private boolean newFigure = false;

	public boolean isNewFigure() {
		return newFigure;
	}

	public void setNewFigure(boolean newFigure) {
		this.newFigure = newFigure;
	}

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
		System.out.println("----------------");
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print(mat[i][j].toString());
			}
			System.out.println();
		}

	}

	public boolean schach(int y, int x) {
		Main m = new Main();
		if (scan().size() != 0 && scan().size() != 8 && m.isWeiss() != werAmZug
				&& !(((Figur) this.getFeld(y, x)) instanceof Koenig)) {
			return true;
		}
		return false;
	}

	public boolean schachMatt(int y, int x) {
		Main m = new Main();
		if (scan().size() == 8 && m.isWeiss() != werAmZug && !(((Figur) this.getFeld(y, x)) instanceof Koenig)) {
			return true;
		}
		return false;
	}

	public ArrayList<Boolean> scan() {
		String v1 = "KW";
		String v2 = "KB";
		int x = 0;
		int y = 0;
		String[] w = { "BW", "TW", "KW", "DW", "LW", "SW" };
		String[] b = { "BB", "TB", "KB", "DB", "LB", "SB" };
		String[] auswahl = b;
		if (!werAmZug) {
			v1 = v2;
			auswahl = w;
		}

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j].toString().equals(v1)) {
					y = i;
					x = j;

				}
			}
		}

		ArrayList<Boolean> werte = new ArrayList<Boolean>();

		/* xRechts */
		XRight_LOOP: for (int xWert = x + 1; xWert < 8; xWert++) {
			if (mat[y][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(y, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(y, xWert), new Position(y, x))) {

						werte.add(true);
						break XRight_LOOP;
					}
				}
			}
		}

		// xlinks
		XLeft_LOOP: for (int xWert = x - 1; xWert >= 0; xWert--) {
			if (mat[y][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(y, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(y, xWert), new Position(y, x))) {
						werte.add(true);
						break XLeft_LOOP;
					}

				}
			}
		}

		// yoben
		YTop_LOOP: for (int yWert = y + 1; yWert < 8; yWert++) {
			if (mat[yWert][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, x);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, x), new Position(y, x))) {
						werte.add(true);
						break YTop_LOOP;
					}

				}
			}
		}

		// vunten
		YBottom_LOOP: for (int yWert = y - 1; yWert >= 0; yWert--) {
			if (mat[yWert][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, x);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, x), new Position(y, x))) {
						werte.add(true);
						break YBottom_LOOP;
					}

				}
			}
		}

		// d1NachOben
		D1Up_LOOP: for (int xWert = x + 1; xWert < 8; xWert++) {
			int yWert = y + 1;
			if (yWert < 7 && xWert < 7 && mat[yWert][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, xWert), new Position(y, x))) {
						werte.add(true);
						break D1Up_LOOP;
					}

				}
				yWert++;
			}
		}

		D1Down_LOOP:
		// d1NachUnten
		for (int xWert = x - 1; xWert >= 0; xWert--) {
			int yWert = y - 1;
			if (yWert >= 0 && xWert >= 0 && mat[yWert][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, xWert), new Position(y, x))) {
						werte.add(true);
						break D1Down_LOOP;
					}

				}
				yWert--;
			}
		}

		D2Up_LOOP:
		// d2NachOben
		for (int xWert = x - 1; xWert >= 0; xWert--) {
			int yWert = y + 1;
			if (xWert != 0 && yWert < 7 && mat[yWert][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, xWert), new Position(y, x))) {
						werte.add(true);
						break D2Up_LOOP;
					}

				}
				yWert++;
			}
		}

		// d2Nachunten
		D2Bottom_LOOP: for (int xWert = x + 1; xWert >= 0; xWert--) {
			int yWert = y - 1;
			if (y != 0 && x < 7 && mat[yWert][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(yWert, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, xWert), new Position(y, x))) {
						werte.add(true);
						break D2Bottom_LOOP;
					}

				}
				yWert--;
			}
		}

		return werte;
	}

	public void spielzug(String zug) {
		String[] p = zug.split("-");
		Position von = schach2koordinate(p[0]);
		Position nach = schach2koordinate(p[1]);

		if (mat[von.getY()][von.getX()] instanceof Bauer) {
			Bauer b = (Bauer) getFeld(von.getY(), von.getX());
			if (b.getFigure(this, von, nach))
				newFigure = true;

		}

		if (mat[von.getY()][von.getX()] instanceof Figur) {

			Figur f = (Figur) getFeld(von.getY(), von.getX());

			if (f.farbeWeiss == werAmZug && !schachMatt(von.getY(), von.getX()) && !schach(von.getY(), von.getX())) {

				if (f.spielzugMoeglich(this, von, nach)) {
					mat[nach.getY()][nach.getX()] = f;
					mat[von.getY()][von.getX()] = new Feld();
					f.setBewegt(true);
				}
			}

		}
	}

	public void newFigureChoice(char name, boolean farbeWeiss, Position nach) {
		Figur f = null;
		switch (name) {

		case 'D':
			f = new Dame(farbeWeiss, true);
			break;

		case 'L':
			f = new Laeufer(farbeWeiss, true);
			break;
		case 'S':
			f = new Springer(farbeWeiss, true);
			break;
		case 'T':
			f = new Turm(farbeWeiss, true);
			break;
		default:
			System.out.println("Default");

		}
		mat[nach.getY()][nach.getX()] = f;
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
