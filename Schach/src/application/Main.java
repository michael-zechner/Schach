package application;

import java.io.FileNotFoundException;

import game.SpielFeld;
import game.SpielFeldIO;
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
import javafx.scene.layout.ColumnConstraints;

public class Main extends Application {
	private boolean clicked1 = false;
	private boolean clicked2 = false;
	private Node n1;
	private int firstN;
	private int second;

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		BorderPane root = new BorderPane();
		GridPane feld = new GridPane();
		boolean farbe = false;

		SpielFeldIO spIO = new SpielFeldIO();
		SpielFeld sp = spIO.einlesen("start.txt");

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Image im1 = new Image("images/" + sp.getMat()[j - 1][i - 1].toString() + ".png");
				ImageView imageView = new ImageView(im1);

				Button b = new Button();
				b.setGraphic(imageView);
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
							Node n = (Node)event.getSource();
							char first = n.getId().charAt(0);
							firstN = (int)first - (int)'A' + 1;
							second = Integer.parseInt(String.valueOf(n.getId().charAt(1)));
							
							System.out.println(firstN + " " + second);
							
							if (!clicked1) {
								n1 = b.getGraphic();
								clicked1 = true;
							}
							if (clicked2) {
								b.setGraphic(n1);
								clicked1 = false;
							}
							clicked2 = clicked1;
						}
					});
				
			}
		}
		ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(12.5);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(12.5);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        ColumnConstraints col4 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        ColumnConstraints col5 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        ColumnConstraints col6 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        ColumnConstraints col7 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        ColumnConstraints col8 = new ColumnConstraints();
        col3.setPercentWidth(12.5);
        feld.getColumnConstraints().addAll(col1,col2,col3, col4, col5, col6, col7, col8);
		Label ausgabe = new Label("Letzter Zug:");
		root.setCenter(feld);
		root.setBottom(ausgabe);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public int getFirstN() {
		return firstN;
	}

	public void setFirstN(int firstN) {
		this.firstN = firstN;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
}