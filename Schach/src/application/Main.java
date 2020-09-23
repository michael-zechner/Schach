package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	private boolean clicked1 = false;
	private boolean clicked2 = false;
	private Button b3 = new Button();
	private String idd = "";
	private Node n1;
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		GridPane feld = new GridPane();
		boolean farbe = false;
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Button b = new Button();
				b.setMaxHeight(80);
				b.setMaxWidth(80);
				b.setMinHeight(80);
				b.setMinWidth(80);
				if (farbe) {
					if (j % 2 == 0) {
						b.setStyle("-fx-background-color: #585858");
					}
					if (j == 8 && i % 2 == 0) {
						farbe = false;
					}
				}
				if (!farbe) {
					if (j % 2 != 0) {
						b.setStyle("-fx-background-color: #585858");
					}
					if (j == 8 && i % 2 != 0) {
						farbe = true;
					}
				}
				char id = (char) ('A' + (i - 1));
				String id1 = id + String.valueOf(9 - j);
				b.setId(id1);
				feld.add(b, i, j);
				
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(!clicked1) {
							Node n = (Node)event.getSource();
							idd = n.getId();
							n1 = b.getGraphic();
							clicked1 = true;
						}
						if(clicked2) {
							Node n = (Node)event.getSource();
							b.setGraphic(n1);
						}
					}

				});
			}
		}

		Image im1 = new Image("images/BW.png");
		Image im2 = new Image("images/BB.png");
		for (int j = 2; j < 9; j += 5) {
			for (int i = 0; i <= 8; i++) {
				for (Node node : feld.getChildren()) {
					if (feld.getRowIndex(node) == j && feld.getColumnIndex(node) == i) {
						if (j == 2) {
							Button b1 = (Button) node;
							b1.setGraphic(new ImageView(im1));
						}
						if (j == 7) {
							Button b1 = (Button) node;
							b1.setGraphic(new ImageView(im2));
						}
					}
				}
			}
		}

//		for (int i = 0; i < 8; i+=2) {
//			for (int j = 1; j <= 8; j+=2) {
//				for(Node node  : feld.getChildren()) {
//					if (feld.getRowIndex(node) == i && feld.getColumnIndex(node) == j) {
//			            Button b1 = (Button)node;
//			            b1.setStyle("-fx-background-color: #585858");
//			       }
//				}
//			}
//		}
//		
//		for (int i = 1; i <= 8; i+=2) {
//			for (int j = 0; j < 8; j+=2) {
//				for(Node node  : feld.getChildren()) {
//					if (feld.getRowIndex(node) == i && feld.getColumnIndex(node) == j) {
//			            Button b1 = (Button)node;
//			            b1.setStyle("-fx-background-color: #585858");
//			       }
//				}
//			}	
//		}

		Label ausgabe = new Label("Letzter Zug:");
		root.setCenter(feld);
		root.setBottom(ausgabe);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
