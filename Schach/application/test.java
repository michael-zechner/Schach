package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class test extends Application {
	private static final int NUM_UNITS = 10;
	private static final int UNIT_SIZE = 30;
	private static final int GRID_SIZE = 5;
	private static final int GAP = 5;
	private static final Duration PAUSE_DURATION = Duration.seconds(3);

	private Random random = new Random(42);

	private ObservableList<Unit> units = FXCollections.observableArrayList();

	private GridPane gridPane = new GridPane();

	@Override
	public void start(Stage stage) throws Exception {
		configureGrid();

		LayoutAnimator animator = new LayoutAnimator();
		animator.observe(gridPane.getChildren());

		generateUnits();
		relocateUnits();

		continuouslyAnimateGrid();

		stage.setScene(new Scene(gridPane));
		stage.setResizable(false);
		stage.show();
	}

	private void configureGrid() {
		gridPane.setVgap(GAP);
		gridPane.setHgap(GAP);
		int size = GRID_SIZE * UNIT_SIZE + GAP * (GRID_SIZE - 1);
		gridPane.setMinSize(size, size);
		gridPane.setMaxSize(size, size);
	}

	private void generateUnits() {
		for (int i = 0; i < NUM_UNITS; i++) {
			Unit unit = new Unit(new Text((i + 1) + ""), new Rectangle(UNIT_SIZE, UNIT_SIZE));

			units.add(unit);
		}
	}

	private void relocateUnits() {
		Set<Point2D> usedLocations = new HashSet<>();

		for (Unit unit : units) {
			Node node = unit.getStackPane();

			int col;
			int row;
			do {
				col = random.nextInt(GRID_SIZE);
				row = random.nextInt(GRID_SIZE);
			} while (usedLocations.contains(new Point2D(col, row)));
			usedLocations.add(new Point2D(col, row));

			GridPane.setConstraints(unit.getStackPane(), col, row);

			if (!gridPane.getChildren().contains(node)) {
				gridPane.add(node, col, row);
			}
		}
	}

	private void continuouslyAnimateGrid() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> relocateUnits()),
				new KeyFrame(PAUSE_DURATION));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}