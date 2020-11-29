package application;

import javafx.scene.control.Button;

public class Externer_Thread implements Runnable {
	
	public void clearButton(Button b) throws InterruptedException {
		Thread.currentThread().sleep(1000);
		b.setStyle("-fx-background-color: rgba(255,0,0, 1);");
	}

	@Override
	public void run() {
	}

}
