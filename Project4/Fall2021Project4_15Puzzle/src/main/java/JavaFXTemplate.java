import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JavaFXTemplate extends Application {
	static Stage primStage;
	public static GameButton[] arr = new GameButton[16];
	public static Integer[] numbers;
	public static int emptyRow;
	public static int emptyCol;
	public static boolean remake = false;
	public static Text run = new Text();
	public static ExecutorService ex;
	public static ArrayList<Node> solutionPath;
	public static A_IDS_A_15solver ids;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ex = Executors.newFixedThreadPool(10);
		primStage = primaryStage;
		primaryStage.setTitle("Welcome to 15 puzzle");
		primaryStage.setScene(welcomeScene());
		primaryStage.show();
	}

	public Scene welcomeScene() {
		Text t = new Text("Welcome to 15 Puzzle game!");
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> {
			primStage.setScene(gamePlayScene());
			primStage.show();
		});
		pause.play();

		VBox root = new VBox(40, t);
		root.setAlignment(Pos.CENTER);
		root.getStylesheets().add("/styles1.css");
		t.getStyleClass().add("textLabel");
		root.getStyleClass().add("background");

		return new Scene(root, 900,600);
	}

	public static Scene gamePlayScene() {
		MenuBar menu = new MenuBar();
		menu(menu);

		GridPane grid = new GridPane();
		addGrid(grid);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);

		Text name = new Text("15 Puzzle");

		Button newPuzzle = new Button("New Puzzle");
		newPuzzle.setOnAction(e -> addGrid(grid));


		Button solve1 = new Button("Solve with AI H1");
		Button solve2 = new Button("Solve with AI H2");
		solve1.setOnAction(e -> {
			Future<ArrayList<Node>> future = ex.submit(() -> {
				ids = new A_IDS_A_15solver(1, false);
				ArrayList<Node> solver = ids.A_Star(ids.startState, "heuristicOne");
				run.setText("");
				return solver;
			});
			submit(future);
		});

		solve2.setOnAction(e -> {
			Future<ArrayList<Node>> future = ex.submit(() -> {
				ids = new A_IDS_A_15solver(2, false);
				ArrayList<Node> solver = ids.A_Star(ids.startState, "heuristicTwo");
				run.setText("");
				return solver;
			});
			submit(future);
		});

		VBox buttons = new VBox(15, name, newPuzzle, solve1, solve2, run);
		buttons.setAlignment(Pos.CENTER);

		HBox main = new HBox(50, grid, buttons);
		main.setAlignment(Pos.CENTER);

		VBox main2 = new VBox(130, menu, main);

		main2.getStylesheets().add("/styles2.css");
		name.getStyleClass().add("textLabel");
		newPuzzle.getStyleClass().add("buttonRight");
		solve1.getStyleClass().add("buttonRight");
		solve2.getStyleClass().add("buttonRight");
		menu.getStyleClass().add("background");
		main2.getStyleClass().add("background");
		grid.getStyleClass().add("grid-pane");

		return new Scene(main2, 900,600);
	}

	public static Scene winScene () {
		primStage.setTitle("You won 15 puzzle!");
		Text win = new Text("YOU WON!");

		Button playAgain = new Button("Play again");
		playAgain.setOnAction(e -> primStage.setScene(gamePlayScene()));

		Button exit = new Button("Exit the game");
		exit.setOnAction(e -> Platform.exit());

		HBox hbox = new HBox(20, playAgain, exit);
		VBox vbox = new VBox(40, win, hbox);
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		vbox.getStylesheets().add("/styles2.css");
		playAgain.getStyleClass().add("buttonRight");
		exit.getStyleClass().add("buttonRight");
		win.getStyleClass().add("textLabel");
		vbox.getStyleClass().add("background");
		return new Scene(vbox, 900, 600);
	}

	private static void submit(Future<ArrayList<Node>> future) {
		ex.submit(() -> {
			run.setText("Running an algorithm ...");
			if (future.isDone()) {
				try {
					solutionPath = future.get();
					Thread.sleep(2000);
				} catch (Exception ignored) {}
			}
		});
		GameLogic.changeBoard(numbers);
		remake = false;
	}

	public static void menu(MenuBar menu) {
		Menu how = new Menu("Info");
		MenuItem how2 = new MenuItem("How to play the game");

		Alert a = new Alert(Alert.AlertType.INFORMATION);
		how2.setOnAction(e -> {
			a.setContentText("Move tiles in grid to order them from 1 to 15. To move a tile you can click on it or use your " +
					"arrow keys. You can use 'Solve' button in order for an AI to help you solve 10 moves. Only press one AI solver at a time.");
			a.show();
		});
		how.getItems().add(how2);

		Menu exit = new Menu("Options");
		MenuItem exit1 = new MenuItem("Exit the game");
		exit1.setOnAction(e -> Platform.exit());
		exit.getItems().add(exit1);

		menu.getMenus().addAll(how, exit);
	}

	public static void addGrid (GridPane grid) {
		int counter = 0;
		numbers = GameButton.genNum();
		while (GameButton.isSolvable(numbers)){
			numbers = GameButton.genNum();
		}
		int counter2 = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				GameButton b1 = new GameButton(counter);
				b1.setMinSize(60, 60);
				b1.getStylesheets().add("/styles2.css");
				b1.getStyleClass().add("game-button");
				b1.assignNum(numbers[counter]);
				if (counter <= 16) {
					if (numbers[counter] == 0) {
						b1.setText("");
					}
					counter++;
				}
				b1.setOnAction(e -> {
					int index = b1.index;
					GameLogic.makeMove(numbers, index);
				});
				arr[counter2] = b1;
				grid.add(b1, j, i);
				counter2++;
			}
		}
	}
}
