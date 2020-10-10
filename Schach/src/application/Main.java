package application;

import java.io.FileNotFoundException;

import game.SpielFeld;
import game.SpielFeldIO;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.transform.Rotate;
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
		ImageView imageView = null;

		SpielFeldIO spIO = new SpielFeldIO();
		SpielFeld sp = spIO.einlesen("start.txt");

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Image im1 = new Image("images/" + sp.getMat()[8 - j][8 - i].toString() + ".png");

				imageView = new ImageView(im1);
				Button b = new Button();
				b.setGraphic(imageView);
				b.setMaxWidth(Double.MAX_VALUE);
				b.setMaxHeight(Double.MAX_VALUE);
				b.setStyle("-fx-background-color: #E0E6B6; -fx-background-radius: 0px;");
				if (farbe) {
					if (j % 2 == 0) {
						b.setStyle("-fx-background-color: #585858; -fx-background-radius: 0px;");
					}
					if (j == 8 && i % 2 == 0) {
						farbe = false;
					}
				}
				if (!farbe) {
					if (j % 2 != 0) {
						b.setStyle("-fx-background-color: #585858; -fx-background-radius: 0px;");
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
						Node n = (Node) event.getSource();
						char first = n.getId().charAt(0);
						firstN = (int) first - (int) 'A' + 1;
						second = Integer.parseInt(String.valueOf(n.getId().charAt(1)));

						System.out.println(firstN + " " + second);

						if (!clicked1) {
							n1 = b.getGraphic();
							clicked1 = true;
						}
						if (clicked2) {
							b.setGraphic(n1);
							clicked1 = false;
							RotateTransition rotate = new RotateTransition(Duration.seconds(3), feld);
							rotate.setByAngle(180);
							rotate.play();
							for (int i = 1; i < 9; i++) {
								for (int j = 1; j < 9; j++) {
									Button ro = (Button) getNodeByRowColumnIndex(i , j, feld);
									RotateTransition rotateImage = new RotateTransition(Duration.seconds(3), ro.getGraphic());
									rotateImage.setByAngle(180);
									rotateImage.play();
									
								}
							}

						}
						clicked2 = clicked1;

					}
				});

			}
		}
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(10);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(10);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(10);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setPercentWidth(10);
		ColumnConstraints col6 = new ColumnConstraints();
		col6.setPercentWidth(10);
		ColumnConstraints col7 = new ColumnConstraints();
		col7.setPercentWidth(10);
		ColumnConstraints col8 = new ColumnConstraints();
		col8.setPercentWidth(10);
		ColumnConstraints col9 = new ColumnConstraints();
		col9.setPercentWidth(10);
		feld.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(10);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(10);
		RowConstraints row6 = new RowConstraints();
		row6.setPercentHeight(10);
		RowConstraints row7 = new RowConstraints();
		row7.setPercentHeight(10);
		RowConstraints row8 = new RowConstraints();
		row8.setPercentHeight(10);

		feld.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);

		
		Label ausgabe = new Label("Letzter Zug:");
		root.setCenter(feld);
		root.setBottom(ausgabe);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("application/application.css");
		primaryStage.setScene(scene);
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
	
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
}