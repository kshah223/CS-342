import java.io.FileNotFoundException;
import java.util.*;


public class GameLogic extends JavaFXTemplate {

	static Stack<GameButton> stack = new Stack<>();
	
	public static void newGame(GameButton[][] gameArr) {
		for(int i = 0; i < gameArr.length; i++) {
			for(int j = 0; j < gameArr[0].length; j++) {
				gameArr[i][j].valid = false;
				gameArr[i][j].id = 0;
				gameArr[i][j].setStyle("-fx-background-color: white;");
				gameArr[i][j].setDisable(false);
			}
		}
		win = 0;
		stack.clear();
		players.setText("Player 1 choice");
		info.setText("");
	}

	public static void move(GameButton[][] gameArr, int r, int c) throws FileNotFoundException {
		if(r == 5) {
			checkMove(gameArr,r,c);
		}
		if(r < 5) {
			if(gameArr[r+1][c].valid) {
				checkMove(gameArr,r,c);
			}
			else {
				valid(r,c);
			}
		}
	}

	public static void valid(int r, int c) {
		if(win == 0) {
			info.setText(String.format("Player 1 moved to %d,%d. NOT valid move. Player 1 choose again.", r, c));
		}
		else {
			info.setText(String.format("Player 2 moved to %d,%d. NOT valid move. Player 2 choose again.", r, c));
		}
	}

	public static void checkMove(GameButton[][] gameArr, int r, int c) throws FileNotFoundException {
		color(gameArr,r,c);
		if(win(gameArr,gameArr[r][c].id) || gameDraw(gameArr)) {
			window.setScene(finalWindow());
		}
		/*if(gameDraw(gameArr)) {
			window.setScene(finalWindow());
		}*/
	}

	public static boolean gameDraw(GameButton[][] gameArr) {
		for(int i = 0; i < gameArr.length; i++) {
			for(int j = 0; j < gameArr[0].length; j++) {
				if(!gameArr[i][j].valid) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean win(GameButton[][] gameArr, int id) {
		for(int i = 0; i < gameArr.length - 3; i++) {
			for(int j = 0; j < gameArr[0].length; j++) {
				if(gameArr[i][j].id == id && gameArr[i+1][j].id == id && gameArr[i+2][j].id == id && gameArr[i+3][j].id == id) {
					return true;
				}
			}
		}
		for(int i = 0; i < gameArr.length; i++) {
			for(int j = 0; j < gameArr[0].length - 3; j++) {
				if(gameArr[i][j].id == id && gameArr[i][j+1].id == id && gameArr[i][j+2].id == id && gameArr[i][j+3].id == id) {
					return true;
				}
			}
		}
		for(int i = 0; i < gameArr.length - 3; i++) {
			for(int j = 0; j < gameArr[0].length - 3; j++) {
				if(gameArr[i][j].id == id && gameArr[i+1][j+1].id == id && gameArr[i+2][j+2].id == id && gameArr[i+3][j+3].id == id) {
					return true;
				}
			}
		}
		for(int i = 0; i < gameArr.length; i++) {
			for(int j = 0; j < gameArr[0].length - 3; j++) {
				if(gameArr[i][j].id == id && gameArr[i-1][j+1].id == id && gameArr[i-2][j+2].id == id && gameArr[i-3][j+3].id == id) {
					return true;
				}
			}
		}
		return false;
	}

	public static void color(GameButton[][] gameArr, int r, int c) {
		if(win == 0) {
			colorButton(gameArr,r,c);
			gameArr[r][c].id = 1;
			gameArr[r][c].valid = true;
			gameArr[r][c].setDisable(true);
			stack.push(gameArr[r][c]);
			players.setText("Player 2 choice");
			info.setText(String.format("Player 1 moved to %d,%d. Valid move.", r, c));
			win = 1;
		}
		else if(win == 1) {
			colorButton(gameArr,r,c);
			gameArr[r][c].id = 2;
			gameArr[r][c].valid = true;
			gameArr[r][c].setDisable(true);
			stack.push(gameArr[r][c]);
			players.setText("Player 1 choice");
			info.setText(String.format("Player 2 moved to %d,%d. Valid move.", r, c));
			win = 0;
		}
		else {
			gameArr[r][c].setStyle("-fx-background-color: white;");
		}
	}
	
	public static void colorButton(GameButton[][] gameArr, int r, int c) {
		if (theme == 0) {
            if (win == 0) {
                gameArr[r][c].setStyle("-fx-background-color: yellow;");
            } 
            else {
                gameArr[r][c].setStyle("-fx-background-color: red;");
            }
        } 
		else if (theme == 1) {
			if (win == 0) {
                gameArr[r][c].setStyle("-fx-background-color: orange;");
            } 
            else {
                gameArr[r][c].setStyle("-fx-background-color: lightskyblue;");
            }
        } 
		else if (theme == 2) {
			if (win == 0) {
                gameArr[r][c].setStyle("-fx-background-color: brown;");
            } 
            else {
                gameArr[r][c].setStyle("-fx-background-color: blue;");
            }
        }	
	}

	public static void undo(GameButton gameArr[][]) {
		if(stack.empty()) {
			return;
		}
		GameButton btn = stack.peek();
		int r = btn.Row;
		int c = btn.Col;
		if(win == 0) {
			colorUndo(gameArr,r,c);
			gameArr[r][c].id = 0;
			gameArr[r][c].valid = false;
            gameArr[r][c].setDisable(false);
            stack.pop();
            win = 1;
            players.setText("Player 2 choice");
            info.setText(String.format("Player 1 moved to %d,%d. Valid move.", r, c));
        }
		else if(win == 1) {
			colorUndo(gameArr,r,c);
			gameArr[r][c].id = 0;
			gameArr[r][c].valid = false;
            gameArr[r][c].setDisable(false);
            stack.pop();
            win = 0;
            players.setText("Player 1 choice");
            info.setText(String.format("Player 2 moved to %d,%d. Valid move.", r, c));
		}
	}

	public static void colorUndo(GameButton[][] gameArr, int r, int c) {
		if (theme == 0) {
            gameArr[r][c].setStyle("-fx-background-color: white;");
        } 
		else if (theme == 1 || theme == 2) {
            gameArr[r][c].setStyle("-fx-background-color: white;");
        }
	}
}