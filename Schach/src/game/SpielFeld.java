package game;

public class SpielFeld {
	
	private Feld[][] mat;
	private boolean werAmZug;
	
	public void ausgabe() {
		mat.toString();
	}
	
	public boolean schach(){
		return false;
	}
	
	public boolean schachMatt() {
		return false;
	}
	
	public void spielzug(String zug) {
		
	}
	
	public Position schach2koordinate(String schach) {
		Position p = new Position();
		p.setX((byte) schach.charAt(0)); 
		p.setY((byte) schach.charAt(1));
		return p;
	}
}
