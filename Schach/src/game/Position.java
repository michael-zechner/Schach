package game;

public class Position {
	
	private byte x;
	private byte y;
	
	public Position() {}
	
	public Position(int x, int y) {
		super();
		this.x = (byte) x;
		this.y = (byte) y;
	}
	
	public byte getX() {
		return x;
	}
	public void setX(byte x) {
		this.x = x;
	}
	public byte getY() {
		return y;
	}
	public void setY(byte y) {
		this.y = y;
	}
	
	
}
