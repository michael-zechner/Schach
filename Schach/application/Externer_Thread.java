package application;

import javafx.scene.control.Button;

public class Externer_Thread implements Runnable {
	
	private Button b;
	
	public void clearButton(Button b) throws InterruptedException {
		Thread.sleep(1000);
		b.setGraphic(null);
	}

	@Override
	public void run() {
		try {
			clearButton(b);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
