package game;

import java.util.ArrayList;


import application.Main;

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
		Main m = new Main();
		String v1 = "KW";
		String v2 = "KB";
		int x = 0;
		int y = 0;
		String[] w = { "BW", "TW", "KW", "DW", "LW", "SW", "__" };
		String[] b = { "BB", "TB", "KB", "DB", "LB", "SB", "__" };
		String[] auswahl = w;
		if (!werAmZug) {
			v1 = v2;
			auswahl = b;
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (mat[i][j].toString().equals(v1)) {
					y = i;
					x = j;
				}
			}
		}
		
		System.out.println(x);
		System.out.println(y);
		
		ArrayList<Boolean> werte = new ArrayList<Boolean>();

		
		// xrechts
		for (int i = x; i < 8; i++) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// xlinks
		for (int i = x; i >= 0; i--) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// yoben
		for (int i = y; i < 8; i++) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// yunten
		for (int i = y; i >= 0; i--) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// d1NachOben
		y--;
		for (int i = x; i <= 7; i++) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// d1NachUnten
		for (int i = x; i >= 0; i--) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// d2NachOben
		for (int i = x; i < 8; i++) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
						}	
					}
				}
			}
		}

		// d2Nachunten
		for (int i = x; i >= 0; i--) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						if(auswahl[j] == "__") {
							werte.add(false);
						}else {
							if(auswahl[j] == "__") {
								werte.add(false);
							}else {
								werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
							}	
						}	
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

	private static Figur erstelleFigur(String f) {
		char a = f.charAt(0);
		char b = f.charAt(1);
		boolean farbeWeiss = false;		
		Figur c = null;
		
		if (b == 'W') {
			farbeWeiss = true;
		}

		switch (a) {
		case 'B':
			return new Bauer(farbeWeiss, false);
		case 'D':
			return new Dame(farbeWeiss, false);
		case 'K':
			return new Koenig(farbeWeiss, false);
		case 'L':
			return new Laeufer(farbeWeiss, false);
		case 'S':
			return new Springer(farbeWeiss, false);
		case 'T':
			return new Turm(farbeWeiss, false);
		default:
			return c;
		}

	}
	
	public boolean schachMatt() {
		Main m = new Main();
		String v1 = "KW";
		String v2 = "KB";
		int x = 0;
		int y = 0;
		String[] w = { "BW", "TW", "KW", "DW", "LW", "SW" };
		String[] b = { "BB", "TB", "KB", "DB", "LB", "SB" };
		String[] auswahl = w;
		if (!werAmZug) {
			v1 = v2;
			auswahl = b;
		}
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j].toString() == v1) {
					y = i;
					x = j;
				}
			}
		}
		
		ArrayList<Boolean> werte = new ArrayList<Boolean>();

		
		// xrechts
		for (int i = x; i < 8; i++) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// xlinks
		for (int i = x; i >= 0; i--) {
			if (mat[y][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// yoben
		for (int i = y; i < 8; i++) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// yunten
		for (int i = y; i >= 0; i--) {
			if (mat[i][x] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// d1NachOben
		for (int i = x; i < 8; i++) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// d1NachUnten
		for (int i = x; i >= 0; i--) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// d2NachOben
		for (int i = x; i < 8; i++) {
			if (mat[y--][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
					}
				}
			}
		}

		// d2Nachunten
		for (int i = x; i >= 0; i--) {
			if (mat[y++][i] instanceof Figur) {
				for (int j = 0; j < auswahl.length; j++) {
					if (mat[y][i].toString() == auswahl[j]) {
						werte.add(erstelleFigur(auswahl[j]).spielzugMoeglich(m.getSpielfeld(), new Position(y, x), new Position(y, i)));
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
