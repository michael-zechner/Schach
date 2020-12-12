package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.Main;

public class SpielFeld {

	private Feld[][] mat = new Feld[8][8];
	private boolean werAmZug = true;
	private boolean newFigure = false;

	public boolean isNewFigure() {
		return newFigure;
	}

	public void setNewFigure(boolean newFigure) {
		this.newFigure = newFigure;
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

	public void rochade(Position von, Position nach) {
		if (getFeld(von) instanceof Koenig) {

			Koenig k = (Koenig) getFeld(von);

			if (k.rochade(this, von, nach)) {
				setFeld(nach, getFeld(von));
				setFeld(new Position(0, 6), new Turm(werAmZug, true));
				setFeld(von, new Feld());
			}
		}

	}

	public boolean schach(Position pos) {
		Main m = new Main();
		if (scan().size() != 0 && scan().size() != 8 && m.isWeiss() != werAmZug
				&& !(((Figur) this.getFeld(pos)) instanceof Koenig)) {
			return true;
		}
		return false;
	}

	public boolean schachMatt(Position pos) {
		Main m = new Main();
		if (scan().size() == 8 && m.isWeiss() != werAmZug && !(((Figur) this.getFeld(pos)) instanceof Koenig)) {
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
					System.out.println("XK: " + x + " YK: " + y);
					System.out.println(auswahl[1]);
					System.out.println("------");
				}
			}
		}

		ArrayList<Boolean> werte = new ArrayList<Boolean>();

		werte.clear();
		/* xRechts */
		XRight_LOOP: for (int xWert = x + 1; xWert < 8; xWert++) {
			if (mat[y][xWert] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					Figur f = (Figur) getFeld(y, xWert);
					if (f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(y, xWert), new Position(y, x))) {

						werte.add(true);
						System.out.println("XRight");
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
						System.out.println("XLeft");
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

					if (f.isFarbeWeiss() == werAmZug && f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, x), new Position(y, x))) {
						werte.add(true);
						System.out.println("YTop");
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
					if (f.isFarbeWeiss() == werAmZug && f.toString().equals(auswahl[j])
							&& f.spielzugMoeglich(this, new Position(yWert, x), new Position(y, x))) {
						werte.add(true);
						System.out.println("YBottom");
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
						System.out.println("D1Up");
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
						System.out.println("D1Down");
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
						System.out.println("D2Up");
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
						System.out.println("D2Down");
						break D2Bottom_LOOP;
					}

				}
				yWert--;
			}
		}

		System.out.println("Wert: " + werte.size());
		return werte;
	}

	public void spielzug(String zug) {
		String[] p = zug.split("-");
		Position von = schach2koordinate(p[0]);
		Position nach = schach2koordinate(p[1]);

		rochade(von, nach);
		if (getFeld(von) instanceof Bauer) {
			Bauer b = (Bauer) getFeld(von);
			if (b.getFigure(this, von, nach))
				newFigure = true;

		}

		if (getFeld(von) instanceof Figur) {

			Figur f = (Figur) getFeld(von);

			if (f.farbeWeiss == werAmZug && !schachMatt(von) && !schach(von)) {

				if (f.spielzugMoeglich(this, von, nach)) {
					setFeld(nach, f);
					setFeld(von, new Feld());
					f.setBewegt(true);
				}
			}

		}
	}

	public ArrayList<Figur> holeFigurenObj(boolean weiss) {
		ArrayList<Figur> alleFig = new ArrayList<Figur>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (mat[i][j] instanceof Figur && ((Figur) mat[i][j]).isFarbeWeiss() == weiss) {
					alleFig.add((Figur) mat[i][j]);
//					System.out.print(((Figur) mat[i][j]).toString());
				}

			}
		}

		return alleFig;
	}

	public ArrayList<Figur> minus(SpielFeld sf, boolean weiss) {
		ArrayList<Figur> fs1 = holeFigurenObj(weiss); // startspielfeld alle figuren
		System.out.println("fs1: " + Arrays.toString(fs1.toArray()));
		ArrayList<Figur> fs2 = sf.holeFigurenObj(weiss); // jetztiges spielfeld
		System.out.println(Arrays.toString(fs2.toArray()));

		for (Figur figur : fs2) {
			if (fs1.contains(figur)) {
				fs1.remove(figur);
			}
		}
		System.out.println("fs1:" + fs1.size());
		return fs1;
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
		setFeld(nach, f);
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

	public Feld getFeld(Position pos) {
		return getFeld(pos.getY(), pos.getX());
	}

	public void setFeld(Position pos, Feld f) {
		mat[pos.getY()][pos.getX()] = f;
	}

}
