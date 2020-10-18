package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import game.Feld;
import game.Figur;
import game.Position;
import game.SpielFeld;
import game.SpielFeldIO;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

public class Main extends Application {
	private boolean clicked1 = false;
	private boolean clicked2 = false;
	private boolean weiss = true;
	private Node n1;
	private int firstN;
	private int second;
	private ImageView imageView = null;
	private char XF;
	private char YF;
	private char XS;
	private char YS;
	private char[] zugC = new char[5];
	private Figur von;
	private boolean zugMoeglich = false;
	private String letzterZug = null;
	private boolean rotation = false;
	private Button rotate;
	private double width;
	private double height;
	private ArrayList<ImageView> view = new ArrayList<ImageView>();
	private ArrayList<String> felder = new ArrayList<String>();
	public ArrayList<Button> allButtons = new ArrayList<Button>();
	private SpielFeld sp;

	public SpielFeld getSpielfeld() {
		return sp;
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		BorderPane root = new BorderPane();
		GridPane feld = new GridPane();
		boolean farbe = false;

		Label spieler = new Label("Spieler Weiss am Zug");
		spieler.setPadding(new Insets(20));
		Label ausgabe = new Label("Letzter Zug: xx-xx");
		ausgabe.setPadding(new Insets(20));

		SpielFeldIO spIO = new SpielFeldIO();
		sp = spIO.einlesen("Test.txt");
		sp.setWerAmZug(false);

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Image im1 = new Image("images/" + sp.getMat()[8 - j][i - 1].toString() + ".png");

				imageView = new ImageView(im1);
				imageView.setFitHeight(40);
				imageView.setFitWidth(25);

				view.add(imageView);

				Button b = new Button();
				allButtons.add(b);
				/* ImageSize Button */
				/*
				 * Hier setzten wir "Breakpoints" also width und height, ab der das Bild
				 * skaliert werden soll
				 */
				b.widthProperty().addListener((obs, oldVal, newVal) -> {
					width = (Double) newVal;

					for (int k = 0; k < view.size(); k++) {

						if (width > 100) {
							view.get(k).setFitWidth(50);
							view.get(k).setFitHeight(81);
						} else if (width <= 100) {
							view.get(k).setFitWidth(25);
							view.get(k).setFitHeight(40);
						}
					}

				});

				b.setGraphic(imageView);
				b.setMaxWidth(Double.MAX_VALUE);
				b.setMaxHeight(Double.MAX_VALUE);
				b.getStyleClass().add("button");
				if (farbe) {
					if (j % 2 == 0) {
						b.getStyleClass().add("buttonBlack");
					}
					if (j == 8 && i % 2 == 0) {
						farbe = false;
					}
				}
				if (!farbe) {
					if (j % 2 != 0) {
						b.getStyleClass().add("buttonBlack");
					}
					if (j == 8 && i % 2 != 0) {
						farbe = true;
					}
				}

				/* Button Id setzen */
				char id = (char) ('A' + (i - 1));
				String id1 = id + String.valueOf(9 - j);
				b.setId(id1);
				feld.add(b, i, j);

				/* Button Handler */
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						XF = 0;
						YF = 0;
						XS = 0;
						YS = 0;

						/* Erster Button */
						if (!clicked1) {
							/* Letzter Zug Label */
							letzterZug = b.getId();

							/* Spielzug */
							String a = Integer.toString((b.getId().charAt(0) - 65));
							XF = a.charAt(0);
							YF = (char) (b.getId().charAt(1) - 1);
							System.out.println(XF + " " + YF);
							zugC[0] = XF;
							zugC[1] = YF;
							zugC[2] = '-';
							if (sp.getFeld(Character.getNumericValue(YF),
									Character.getNumericValue(XF)) instanceof Figur) {

								von = (Figur) sp.getFeld(Character.getNumericValue(YF), Character.getNumericValue(XF));

								/* Suggestion */
								if (von.isFarbeWeiss() == weiss) {

									felder = von.suggest(sp,
											new Position(Character.getNumericValue(YF), Character.getNumericValue(XF)),
											sp.isWerAmZug());
									if (felder.size() > 0) {

										for (int k = 0; k < felder.size(); k++) {
											int y = Character.getNumericValue(felder.get(k).charAt(0));
											int x = Character.getNumericValue(felder.get(k).charAt(1));
											System.out.println(y + "" + x);
											Button moeglich = (Button) getNodeByRowColumnIndex(8 - x, y + 1, feld);
											moeglich.setStyle("-fx-background-color: rgba(154,192,205, 1);");
										}
										clicked1 = true;
									} else {
										System.out.println("no");
										showAlertNoSuggestion();
									}
								}

							} else {
								showAlertBlankField();
							}

							/* Bild von erstem Button getten */
							n1 = b.getGraphic();
						}
						if (clicked2) {
							/* Suggestion */
							for (int k = 0; k < felder.size(); k++) {
								int y = Character.getNumericValue(felder.get(k).charAt(0));
								int x = Character.getNumericValue(felder.get(k).charAt(1));
								System.out.println(y + "" + x);
								Button moeglich = (Button) getNodeByRowColumnIndex(8 - x, y + 1, feld);
								moeglich.setStyle("");
							}

							/* Letzter Zug Label */
							letzterZug = letzterZug + "-" + b.getId();
							ausgabe.setText("Letzter Zug: ");
							/* Spielzug */
							String c = Integer.toString((b.getId().charAt(0) - 65));
							XS = c.charAt(0);
							YS = (char) (b.getId().charAt(1) - 1);
							System.out.println(XS + " " + YS);
							System.out.println(XF + " " + YF);

							zugC[3] = XS;
							zugC[4] = YS;
							String zug = String.valueOf(zugC);

							zugMoeglich = von.spielzugMoeglich(sp,
									new Position(Character.getNumericValue(zug.charAt(1)),
											Character.getNumericValue(zug.charAt(0))),
									new Position(Character.getNumericValue(zugC[4]),
											Character.getNumericValue(zugC[3])));
							sp.setWerAmZug(weiss);
							if (von.isFarbeWeiss() == sp.isWerAmZug()) {
								if (zugMoeglich) {
									/*
									 * Grafik auf den zweiten Button setzten und anschließend feld und buttons
									 * rotieren
									 */
									b.setGraphic(n1);
									clicked1 = false;

									if (rotation) {
										RotateTransition rotate = new RotateTransition(Duration.seconds(1.5), feld);
										rotate.setByAngle(180);
										rotate.play();
										for (int i = 1; i < 9; i++) {
											for (int j = 1; j < 9; j++) {
												Button ro = (Button) getNodeByRowColumnIndex(i, j, feld);
												RotateTransition rotateImage = new RotateTransition(
														Duration.seconds(0.001), ro.getGraphic());
												rotateImage.setByAngle(180);
												rotateImage.play();

											}
										}
									}
									weiss = !weiss;
									/* Spielerindikator */
									if (weiss) {
										spieler.setText("Spieler Weiss am Zug");
										spieler.setStyle("-fx-background-color: white; -fx-text-fill: black;");
									} else {
										spieler.setText("Spieler Schwarz am Zug");
										spieler.setStyle("-fx-background-color: black; -fx-text-fill: white;");
										System.out.println(spieler.getStyleClass());
									}

									/* Spielzug abschließen */
									sp.spielzug(zug);
									sp.ausgabe();
									sp.setWerAmZug(!sp.isWerAmZug());
									ausgabe.setText(ausgabe.getText() + letzterZug);

								} else {
									clicked2 = clicked1;
									clicked1 = false;
									showAlertWrongMove();
								}
							} else {
								clicked2 = clicked1;
								clicked1 = false;
								showAlertColor();

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
		RowConstraints row9 = new RowConstraints();
		row9.setPercentHeight(10);

		feld.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9);

		rotate = new Button("Rotation On");

		rotate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				rotation = !rotation;
				if (rotation) {
					rotate.setText("Rotation On");
				} else {
					rotate.setText("Rotation Off");
				}
			}

		});

		HBox hbox = new HBox();
		HBox centerButtons = new HBox(rotate);
		HBox rightButtons = new HBox(spieler);
		rightButtons.setAlignment(Pos.CENTER_RIGHT);

		HBox.setHgrow(centerButtons, Priority.ALWAYS);
		HBox.setHgrow(rightButtons, Priority.ALWAYS);

		centerButtons.setAlignment(Pos.CENTER);

		hbox.getChildren().addAll(ausgabe, centerButtons, rightButtons);
		hbox.setPadding(new Insets(2));

		root.setCenter(feld);
		root.setBottom(hbox);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("application/application.css");
		primaryStage.setScene(scene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		primaryStage.show();

		/* So wird das Fenster genau mittig in X und oben in Y plaziert */
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY(0);
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

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	/* SpielInfos */
	private void showAlertWrongMove() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Unzulässiger Zug");

		alert.setHeaderText(null);
		alert.setContentText("Dieser Zug ist leider nicht möglich !");

		alert.showAndWait();

	}

	private void showAlertBlankField() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Unzulässiges Feld");

		alert.setHeaderText(null);
		alert.setContentText("Bitte wähle ein Feld aus, auf dem eine Figur steht !");

		alert.showAndWait();

	}

	private void showAlertColor() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Unzulässige Farbe");

		alert.setHeaderText(null);
		alert.setContentText("Der andere Spieler ist an der Reihe !");

		alert.showAndWait();

	}
	
	private void showAlertNoSuggestion() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Unzulässige Auswahl");

		alert.setHeaderText(null);
		alert.setContentText("Die von dir gewählte Figur kann sich momentan leider nicht bewegen !");

		alert.showAndWait();

	}

}