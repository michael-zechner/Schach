package application;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import game.Figur;
import game.Koenig;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;

public class Main extends Application {

	private boolean first = true;

	private boolean ersteraufruf = false;

	private String zug;
	private String playerWhite;
	private String playerBlack;
	private boolean clicked1 = false;
	private boolean clicked2 = false;
	private boolean weiss = true;
	private Node n1;
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
	private Button rotate = new Button();
	private double width;
	private ArrayList<ImageView> view = new ArrayList<ImageView>();
	private ArrayList<String> felder = new ArrayList<String>();
	private ArrayList<Button> allButtons = new ArrayList<Button>();
	private SpielFeld sp;
	private GridPane feld;
	private Position vPos;
	private Position nPos;

	private MenuItem simple1;
	private MenuItem simple2;
	private MenuItem simple3;

	private boolean farbe = true;
	private boolean reload = true;

	private BorderPane root;

	public SpielFeld getSpielfeld() {
		return sp;
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {

		primaryStage.setScene(startScene(primaryStage));
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

	/* Spielscene */
	private void reloadScene(Stage primaryStage) throws FileNotFoundException {

		feld = new GridPane();
		feld.setId("bodyMain");

		sp = SpielFeldIO.einlesen("Start.txt");
		sp.setWerAmZug(true);

		allButtons.clear();

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {

				Button butt = new Button();
				allButtons.add(butt);
//				b.setGraphic(imageView);

				/* Farbe und CSS */
				butt.getStyleClass().add("button");
				if (farbe) {
					if (j % 2 == 0) {
						butt.getStyleClass().add("buttonWhite");
					}
					if (j == 8 && i % 2 == 0) {
						farbe = false;
					}
				}
				if (!farbe) {
					if (j % 2 != 0) {
						butt.getStyleClass().add("buttonWhite");
					}
					if (j == 8 && i % 2 != 0) {
						farbe = true;
					}
				}
				/* Button Id setzen */
				char id = (char) ('A' + (i - 1));
				String id1 = id + String.valueOf(9 - j);
				butt.setId(id1);
				feld.add(butt, i, j);
			}
		}
		handleImages();
		responsive();
		primaryStage.setScene(mainScene(primaryStage));
	}

	private void menue(Stage primaryStage) {
		Menu simple = new Menu("_Spiel");
		simple1 = new MenuItem("_Spielernamen ‰ndern");
		simple2 = new MenuItem("_Spiel Neustarten");
		simple3 = new MenuItem("_Spiel beenden");
		simple.getItems().addAll(simple1, simple2, simple3);

		simple1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				primaryStage.setScene(startScene(primaryStage));
				reload = false;

			}
		});

		simple2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					reloadScene(primaryStage);
					reload = true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		simple3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(simple);

		root.setTop(mb);
	}

	private Scene mainScene(Stage primaryStage) throws FileNotFoundException {
		root = new BorderPane();
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		/* Menubar Handle */
		menue(primaryStage);

		/* Rotation label handle */
		rotate.getStyleClass().add("rotation");
		if (rotation) {
			rotate.setText("Rotation On");
		} else {
			rotate.setText("Rotation Off");
		}

		farbe = false;

		Label spieler = new Label("" + " am Zug");
		spieler.setPadding(new Insets(20));
		Label ausgabe = new Label("Letzter Zug: xx-xx");
		ausgabe.setPadding(new Insets(20));

		for (Button b : allButtons) {

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
						String zug = Integer.toString((b.getId().charAt(0) - 65));
						XF = zug.charAt(0);
						YF = (char) (b.getId().charAt(1) - 1);
						zugC[0] = XF;
						zugC[1] = YF;
						zugC[2] = '-';
						vPos = new Position(Character.getNumericValue(YF), Character.getNumericValue(XF));
						if (sp.getFeld(vPos) instanceof Figur) {

							von = (Figur) sp.getFeld(vPos);

							if (sp.schachMatt(vPos)) {
								primaryStage.setScene(endScene(primaryStage));
								primaryStage.show();
							} else if (!sp.schach(vPos)) {

								/* Suggestion */
								if (von.isFarbeWeiss() == weiss) {

									felder = von.suggest(sp, vPos, sp.isWerAmZug());
									if (felder.size() > 0) {

										for (int k = 0; k < felder.size(); k++) {
											int y = Character.getNumericValue(felder.get(k).charAt(0));
											int x = Character.getNumericValue(felder.get(k).charAt(1));
											Button moeglich = (Button) getNodeByRowColumnIndex(8 - x, y + 1, feld);
											moeglich.setStyle("-fx-background-color: rgba(154,192,205, 1);");
											b.setStyle("-fx-border-color: blue; -fx-border-width: 3.0;\r\n");
										}
										clicked1 = true;
									} else {
										showAnyAlert("Unzul‰ssige Auswahl !",
												"Die von dir gew‰hlte Figur kann sich leider momentan nicht bewegen");
									}
								} else {
									showAnyAlert("Falsche Farbe", "Der gegnerische Spieler ist am Zug !");
									Button previous = (Button) getNodeByRowColumnIndex(8 - vPos.getY(), vPos.getX() + 1,
											feld);
									previous.setStyle("");
								}
							} else {
								showAnyAlert("Schach !", "Du bist im Schach, musst also deinen Kˆnig bewegen");
							}

						} else {
							showAnyAlert("Leeres Feld !",
									"Du hast ein leeres Feld angeklickt ! W‰hle bitte eines aus auf dem eine Figur steht");
						}

						/* Bild von erstem Button getten */
						n1 = b.getGraphic();

					}
					if (clicked2) {

						/* Suggestion */
						for (int k = 0; k < felder.size(); k++) {
							int y = Character.getNumericValue(felder.get(k).charAt(0));
							int x = Character.getNumericValue(felder.get(k).charAt(1));
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

						zugC[3] = XS;
						zugC[4] = YS;
						zug = String.valueOf(zugC);
						nPos = new Position(Character.getNumericValue(zugC[4]), Character.getNumericValue(zugC[3]));

						/* Rochade handle */
						if (von instanceof Koenig && !von.isBewegt() && (nPos.getX() == 7 || nPos.getX() == 0)) {
							System.out.println("Kˆnig will rochade");
							Koenig k = (Koenig) von;
							zugMoeglich = k.rochade(sp, vPos, nPos);

						} else {

							zugMoeglich = von.spielzugMoeglich(sp, vPos, nPos);
						}
						sp.setWerAmZug(weiss);
						if (von.isFarbeWeiss() == sp.isWerAmZug()) {
							if (zugMoeglich) {
								/*
								 * Grafik auf den zweiten Button setzten und anschlieﬂend feld und buttons
								 * rotieren
								 */
								b.setGraphic(n1);
								clicked1 = false;

								if (rotation) {
									RotateTransition rotate = new RotateTransition(Duration.seconds(1.5), feld);
									rotate.setByAngle(180);
									rotate.play();
									for (int i = 0; i < 64; i++) {
										System.out.println("Dreh mi");
										RotateTransition rotateImage = new RotateTransition(Duration.seconds(0.001),
												allButtons.get(i));
										rotateImage.setByAngle(180);
										rotateImage.play();

									}
								}
								weiss = !weiss;
								/* Spielerindikator */
								if (weiss) {
									spieler.setText("" + playerWhite + " am Zug");
									spieler.setStyle("-fx-background-color: white; -fx-text-fill: black;");
								} else {
									spieler.setText("" + playerBlack + " am Zug");
									spieler.setStyle("-fx-background-color: black; -fx-text-fill: white;");
								}

								/* Spielzug abschlieﬂen */
								sp.spielzug(zug);
								handleImages();
								sp.ausgabe();
								sp.setWerAmZug(!sp.isWerAmZug());
								ausgabe.setText(ausgabe.getText() + letzterZug);
								Button previous = (Button) getNodeByRowColumnIndex(8 - vPos.getY(), vPos.getX() + 1,
										feld);
								previous.setStyle("");
								handleNewFigure(nPos);

							} else {
								clicked2 = clicked1;
								clicked1 = false;
								showAnyAlert("Unzul‰ssiger Zug !", "Dieser Zug ist leider nicht mˆglich!");
								Button previous = (Button) getNodeByRowColumnIndex(8 - vPos.getY(), vPos.getX() + 1,
										feld);
								previous.setStyle("");
							}
						} else {
							clicked2 = clicked1;
							clicked1 = false;
							showAnyAlert("Falsche Farbe", "Der gegnerische SPielr ist an der Reihe ! ");

							Button previous = (Button) getNodeByRowColumnIndex(8 - vPos.getY(), vPos.getX() + 1, feld);
							previous.setStyle("");

						}

					}

					clicked2 = clicked1;

				}
			});

		}
		{
		}
		first = false;

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
		return scene;
	}

	/* SchachMatt Scene */
	private Scene endScene(Stage primaryStage) {
		StackPane root = new StackPane();
		Button button = new Button();
		VBox v = new VBox();

		Label label = new Label("Schach Matt");
		label.setLayoutX(500);
		root.getChildren().add(v);
		button.setText("Neustart");

		v.getChildren().addAll(label, button);

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					primaryStage.setScene(mainScene(primaryStage));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		Scene scene = new Scene(root, 450, 250);
		scene.getStylesheets().add("application/application.css");
		return scene;
	}

	/* Start Scene */
	private Scene startScene(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Button button = new Button();
		VBox v = new VBox();

		Label label = new Label("Schach");
		label.setLayoutX(500);
		v.setAlignment(Pos.CENTER);
		root.getChildren().add(v);

		// Spieler 1 weiss
		Label weiss = new Label("Spieler Weiss:");
		TextField textField1 = new TextField();
		textField1.setText(playerWhite);
		HBox hb1 = new HBox();
		hb1.setAlignment(Pos.CENTER);
		hb1.getChildren().addAll(weiss, textField1);
		hb1.setSpacing(10);

		// Spieler 2 schwarz
		Label label1 = new Label("Spieler Schwarz:");
		TextField textField2 = new TextField();
		textField2.setText(playerBlack);
		HBox hb2 = new HBox();
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(label1, textField2);
		hb2.setSpacing(10);
		button.setText("Spielen");

		v.getChildren().addAll(label, hb1, hb2, button);

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				playerWhite = textField1.getText();
				playerBlack = textField2.getText();

				try {
					if (reload)
						reloadScene(primaryStage);
						reloadScene(primaryStage);
					if (!reload)
						primaryStage.setScene(mainScene(primaryStage));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		Scene scene = new Scene(root, 450, 250);
		scene.getStylesheets().add("application/application.css");
		return scene;
	}

	/* SpielInfos */
	private void showAnyAlert(String fenstername, String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(fenstername);

		alert.setHeaderText(null);
		alert.setContentText(info);

		alert.showAndWait();
	}

	public void handleNewFigure(Position nach) {
		if (sp.isNewFigure()) {
			char figur = ' ';
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Dein Bauer ist Oben");

			alert.setHeaderText(null);
			alert.setContentText("Du darfst dir jetzt eine neue Figur aussuchen !");

			ButtonType Dame = new ButtonType("Dame");
			ButtonType Turm = new ButtonType("Turm");
			ButtonType Springer = new ButtonType("Springer");
			ButtonType Laeufer = new ButtonType("Laeufer");

			// Remove default ButtonTypes
			alert.getButtonTypes().clear();

			alert.getButtonTypes().addAll(Dame, Turm, Springer, Laeufer);

			// option != null.
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get() == null) {
				System.err.println();
			} else if (option.get() == Dame) {
				figur = 'D';
			} else if (option.get() == Turm) {
				figur = 'T';
			} else if (option.get() == Springer) {
				figur = 'S';
			} else if (option.get() == Laeufer) {
				figur = 'L';
			}

			sp.newFigureChoice(figur, !weiss, nach);
			sp.setNewFigure(false);
			handleImages();
		}
	}

	public void handleImages() {
		view.clear();
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {

				Image im1 = new Image("images/" + sp.getMat()[8 - j][i - 1].toString() + ".png");

				imageView = new ImageView(im1);
				imageView.setFitHeight(40);
				imageView.setFitWidth(25);

				view.add(imageView);
			}
		}
		System.out.println("VIEWSIZE" + view.size());
		for (int i = 0; i < 64; i++) {
			allButtons.get(i).setGraphic(view.get(i));
		}
	}

	public void responsive() {
		/*
		 * Hier setzten wir "Breakpoints" also width und height, ab der das Bild
		 * skaliert werden soll
		 */
		for (int j = 0; j < 64; j++) {

			allButtons.get(j).setMaxWidth(Double.MAX_VALUE);
			allButtons.get(j).setMaxHeight(Double.MAX_VALUE);
			allButtons.get(j).widthProperty().addListener((obs, oldVal, newVal) -> {
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
		}

		/* F¸r responsive Buttons */
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
	}

	public boolean isWeiss() {
		return weiss;
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

//	@Override
//	public void handle(ActionEvent event) {
//		MenuItem source = (MenuItem) event.getSource();
//		String text = source.getText();
//		
//		//Das nur als Beispiel wie man schaun kann welcher Menupunkt gew√§hlt wurde
//		if (source == simple1)
//		{
//			try {
//				
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			System.out.println("asdf");
//		}
//		
//		
//	}

}