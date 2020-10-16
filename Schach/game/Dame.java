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
		int absX = Math.abs(von.getX() - nach.getX());
		int absY = Math.abs(von.getY() - nach.getY());
		
		int wieweitX = 0;
		int wieweitY = 0;

		for (int i = von.getX(); i <= nach.getX(); i++) {
			if(!(sp.getMat()[von.getY()][i] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = von.getY(); i <= nach.getY(); i++) {
			if(!(sp.getMat()[i][von.getX()] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		for (int i = von.getX(); i >= nach.getX(); i--) {
			if(!(sp.getMat()[von.getY()][i] instanceof Figur)) {
				wieweitX++;
			}
		}
		for (int i = von.getY(); i >= nach.getY(); i--) {
			if(!(sp.getMat()[i][von.getX()] instanceof Figur)) {
				wieweitY++;
			}
		}
		
		if(absX == 1 && absY == 2) {
			return false;
		}
		
		int d1wieweitX = 0;
		int d1wieweitY = 0;
		int d2wieweitX = 0;
		int d2wieweitY = 0;
		boolean ulinks = false;
		boolean olinks = false;

		if ((von.getX() < nach.getX() && von.getY() < nach.getY()) || (von.getX() > nach.getX() && von.getY() > nach.getY())) {
			ulinks = true;
		}
		if ((von.getY() > nach.getY() && von.getX() < nach.getX()) || (von.getY() < nach.getY() && von.getX() > nach.getX())) {
			olinks = true;
		}

		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;
		
		boolean onu = false;
		boolean uno = false;
		
		if(ulinks) {
			if(von.getX() < nach.getX() && von.getY() < nach.getY()) {
				uno = true;
			}
			if(von.getX() > nach.getX() && von.getY() > nach.getY()) {
				onu = true;
			}
		}
		if(olinks) {
			if(von.getY() > nach.getY() && von.getX() < nach.getX()) {
				onu = true;
			}
			if(von.getY() < nach.getY() && von.getX() > nach.getX()) {
				uno = true;
			}
		}
		
		if(olinks) {
			if(uno) {
				x1=nach.getX();
				x2=von.getX();
				y1=nach.getY();
				y2=von.getY();
			}
			if(onu) {
				x1=von.getX();
				x2=nach.getX();
				y1=von.getY();
				y2=nach.getY();
			}
			
			for (int i = y1; i >= y2; i--) {
				if(onu) {
					if(!(sp.getMat()[i][x1++] instanceof Figur)) {
						d2wieweitX++;
						d2wieweitY++;
					}
				}
				if(uno) {
					if(!(sp.getMat()[i][x1++] instanceof Figur)) {
						d2wieweitX++;
						d2wieweitY++;
					}
				}
			}
			
		}
		
		if(ulinks) {
			if(uno) {
				x1=von.getX();
				x2=nach.getX();
				y1=von.getY();
				y2=nach.getY();
			}
			if(onu) {
				x1=nach.getX();
				x2=von.getX();
				y1=nach.getY();
				y2=von.getY();
			}
			
			for (int i = x1; i <= x2; i++) {
				if(onu) {
					if(!(sp.getMat()[y1++][i] instanceof Figur)) {
						d1wieweitX++;
						d1wieweitY++;
					}
				}
				if(uno) {
					if(!(sp.getMat()[y1++][i] instanceof Figur)) {
						d1wieweitX++;
						d1wieweitY++;
					}
				}
			}
		}
		
		if(!super.spielzugMoeglich(sp, von, nach)) {
			return false;
		}
		
		return (absX <= wieweitX && absY <= wieweitY) || (absX <= d1wieweitX && absY <= d1wieweitY) || (absX <= d2wieweitX && absY <= d2wieweitY);
		
	}
}
