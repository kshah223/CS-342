import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	static Stage window;
	static GameButton gameArr[][] = new GameButton[6][7];
	static VBox root = new VBox();
	static Text players = new Text();
	static int theme = 0, win = 0;
	static Text info = new Text();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		primaryStage.setTitle("Welcome to Connect 4 Game!!");
		primaryStage.setScene(primaryWindow());
		primaryStage.show();		
	}
			
	public Scene primaryWindow() throws FileNotFoundException {
		Button btn1 = new Button("START GAME");
        btn1.setOnAction(e -> {
				window.setScene(gameWindow());
				window.show();
        });
        
        FileInputStream input = new FileInputStream("src/main/resources/main.jpg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
        
        root = new VBox(40, btn1);
        root.setAlignment(Pos.CENTER);
        root.setBackground(background);
		return new Scene(root, 700, 700);
	}

	public static Scene gameWindow() {
		MenuBar menu = menu();
		players.setText("Player 1 choice");
		players.setStyle("-fx-fill: white;" + "-fx-font-weight: bold");
		info.setStyle("-fx-fill: white;" + "-fx-font-weight: bold");
		FileInputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/main1.jpg");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		
		GridPane grid = new GridPane();
		gridArr(grid);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);
		
		root = new VBox(30, menu, players, info, grid);
		root.setAlignment(Pos.TOP_CENTER);
		root.setBackground(background);
		return new Scene(root, 700, 700);
	}

	public static Scene finalWindow() throws FileNotFoundException {

		if (GameLogic.gameDraw(gameArr)) {
			players.setText("It is a draw!");
		} 
		else if (win == 0) {
			players.setText("Player 2 won!");
			info.setText("You can play again or exit the game");
		} 
		else if (win == 1) {
			players.setText("Player 1 won!");
			info.setText("You can play again or exit the game");
		}

		players.setStyle("-fx-fill: white;" + "-fx-font-weight: bold");
		info.setStyle("-fx-fill: white;" + "-fx-font-weight: bold");
		
		Button btn1 = new Button("Play Again!!");
		Button btn2 = new Button("Exit Game!!");
		btn1.setOnAction( e -> {
			GameLogic.newGame(gameArr);
			window.setScene(gameWindow());
			window.show();
		});
		
		btn2.setOnAction(e -> Platform.exit());
		FileInputStream input = new FileInputStream("src/main/resources/theme2.jpg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		
		HBox buttons = new HBox(20, btn1, btn2);
		VBox text = new VBox(players, info);
		text.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		root = new VBox(100, text, buttons);
		root.setAlignment(Pos.CENTER);
		root.setBackground(background);
		
		return new Scene(root, 700, 700);
	}
	
	public static void gridArr(GridPane grid) {
		for(int i = 0 ; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				GameButton btn1 = new GameButton(j, i);
				btn1.setMinSize(75,75);
				btn1.setStyle("-fx-background-color: white;");
				EventHandler<ActionEvent> e1 = e -> {
					GameButton btn2 = (GameButton) e.getSource();
					int r = btn2.Row;
					int c = btn2.Col;
					try {
						GameLogic.move(gameArr, r, c);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				};
				btn1.setOnAction(e1);
				gameArr[j][i] = btn1;
				grid.add(btn1, i, j);
			}
		}
	}

	public static MenuBar menu() {
		MenuBar menuList = new MenuBar();
		Menu gameplay = new Menu("Game Play");
		Menu themes = new Menu("Themes");
		Menu options = new Menu("Options");
		menuList.getMenus().addAll(gameplay,themes,options);
		
		MenuItem undo = new MenuItem("Reverse Move");
		undo.setOnAction(e -> {
			GameLogic.undo(gameArr);
		});
		gameplay.getItems().add(undo);
		
		MenuItem orgTheme = new MenuItem("Original Theme");
		orgTheme.setOnAction(e -> {
			try {
				theme = 0;
				FileInputStream input1 = new FileInputStream("src/main/resources/main1.jpg");
				Image image1 = new Image(input1);
				BackgroundImage backgroundimage = new BackgroundImage(image1,
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				Background background1 = new Background(backgroundimage);
				root.setBackground(background1);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < gameArr.length; i++){
	            for(int j = 0; j < gameArr[0].length; j++) {
	                if(!gameArr[i][j].valid) {
	                	gameArr[i][j].setStyle("-fx-background-color: white;");
	                }
	                if (gameArr[i][j].id == 1) {
	                	gameArr[i][j].setStyle("-fx-background-color: lightyellow;");
	                } else if (gameArr[i][j].id == 2) {
	                	gameArr[i][j].setStyle("-fx-background-color: red;");
	                }
	                gameArr[i][j].setGraphic(null);
	            }
	        }
		});
		MenuItem theme1 = new MenuItem("Theme 1");
		theme1.setOnAction(e -> {
			
			try {
				theme = 1;
				FileInputStream input1 = null;
				input1 = new FileInputStream("src/main/resources/theme1.jpg");
				Image image1 = new Image(input1);
				BackgroundImage backgroundimage = new BackgroundImage(image1,
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				Background background1 = new Background(backgroundimage);
				root.setBackground(background1);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(int i = 0; i < gameArr.length; i++){
	            for(int j = 0; j < gameArr[0].length; j++) {
	                if(!gameArr[i][j].valid) {
	                	gameArr[i][j].setStyle("-fx-background-color: white;");
	                }
	                if (gameArr[i][j].id == 1) {
	                	gameArr[i][j].setStyle("-fx-background-color: orange;");
	                } else if (gameArr[i][j].id == 2) {
	                	gameArr[i][j].setStyle("-fx-background-color: lightskyblue;");
	                }
	                gameArr[i][j].setGraphic(null);
	            }
	        }
		});
		MenuItem theme2 = new MenuItem("Theme 2");
		theme2.setOnAction(e -> {
			try {
				theme = 2;
				FileInputStream input2 = null;
				input2 = new FileInputStream("src/main/resources/theme2.jpg");
				Image image2 = new Image(input2);
				BackgroundImage backgroundimage = new BackgroundImage(image2,
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT);
				Background background2 = new Background(backgroundimage);
				root.setBackground(background2);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < gameArr.length; i++){
	            for(int j = 0; j < gameArr[0].length; j++) {
	                if(!gameArr[i][j].valid) {
	                	gameArr[i][j].setStyle("-fx-background-color: white;");
	                }
	                if (gameArr[i][j].id == 1) {
	                	gameArr[i][j].setStyle("-fx-background-color: brown;");
	                } else if (gameArr[i][j].id == 2) {
	                	gameArr[i][j].setStyle("-fx-background-color: blue;");
	                }
	                gameArr[i][j].setGraphic(null);
	            }
	        }
		});
		themes.getItems().addAll(orgTheme,theme1,theme2);
		
		MenuItem howToPlay = new MenuItem("How to play"); // will display some text on how to play the game.
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		howToPlay.setOnAction(e -> {
			a.setContentText("Two players plays alternatively "
					+ "dropping squares in the boxes at a time into an "
					+ "unfilled column, until the second player, "
					+ "achieves a diagonal four in a "
					+ "row, and wins the game. If the board fills up "
					+ "before either player achieves four in a row, "
					+ "then the game is a draw.");
			a.show();
		});
		
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(e -> GameLogic.newGame(gameArr));
		MenuItem exit = new MenuItem("Exit Game");
		exit.setOnAction(e -> Platform.exit());
		options.getItems().addAll(howToPlay, newGame, exit);
		return menuList;
	}
}
