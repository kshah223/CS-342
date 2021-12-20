import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.Application;
import javafx.stage.Stage;

class MyTest {

	static GameLogic testing;
	
	public static class mainTest extends Application {
		@Override
		public void start(Stage primaryStage) throws Exception {
			// do nothing here since we just need to start the JavaFX toolkit
		}
	}

	@BeforeEach
	public void initJFX() throws Exception {
		Thread test = new Thread("JavaFX Application Thread") {
			public void run() {
				Application.launch(mainTest.class);
			}
		};
		test.setDaemon(true);
		test.start();
	}
	
	@Test
	void test1() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.color(JavaFXTemplate.gameArr, 1, 2);
		assertEquals(true, JavaFXTemplate.gameArr[1][2].valid);
	}

	@Test
	void test2() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.move(JavaFXTemplate.gameArr, 3, 3);
		assertEquals(1, JavaFXTemplate.win);
		GameLogic.move(JavaFXTemplate.gameArr, 2, 2);
		assertEquals(1, JavaFXTemplate.win);
		GameLogic.move(JavaFXTemplate.gameArr, 4, 4);
		assertEquals(1, JavaFXTemplate.win);
	}

	@Test
	void test3() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.color(JavaFXTemplate.gameArr, 5, 1);
		assertEquals(2, JavaFXTemplate.gameArr[5][1].id);
	}

	@Test
	void test5() {
		testing = new GameLogic();
		int id = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		if (JavaFXTemplate.gameArr[0][1].id == id && JavaFXTemplate.gameArr[0][2].id == id && JavaFXTemplate.gameArr[0][3].id == id && JavaFXTemplate.gameArr[0][4].id == id) {
			assertEquals(true, GameLogic.win(JavaFXTemplate.gameArr, id));
		}
	}

	@Test
	void test6() {
		testing = new GameLogic();
		int id = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		if (JavaFXTemplate.gameArr[5][0].id == id && JavaFXTemplate.gameArr[4][0].id == id && JavaFXTemplate.gameArr[3][0].id == id && JavaFXTemplate.gameArr[2][0].id == id) {
			assertEquals(true, GameLogic.win(JavaFXTemplate.gameArr, id));
		}
	}

	@Test
	void test7() {
		testing = new GameLogic();
		int id = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		if (JavaFXTemplate.gameArr[5][1].id == id && JavaFXTemplate.gameArr[4][2].id == id && JavaFXTemplate.gameArr[3][3].id == id && JavaFXTemplate.gameArr[2][4].id == id) {
			assertEquals(true, GameLogic.win(JavaFXTemplate.gameArr, id));
		}
	}

	@Test
	void test8() {
		testing = new GameLogic();
		int id = 1;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		if (JavaFXTemplate.gameArr[4][5].id == id && JavaFXTemplate.gameArr[3][4].id == id && JavaFXTemplate.gameArr[2][3].id == id && JavaFXTemplate.gameArr[1][2].id == id) {
			assertEquals(true, GameLogic.win(JavaFXTemplate.gameArr, id));
		}

	}

	@Test
	void test9() {
		testing = new GameLogic();
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if(JavaFXTemplate.gameArr[i][j].valid) {
					assertEquals(true, GameLogic.gameDraw(JavaFXTemplate.gameArr));
				}
			}
		}
	}

	@Test
	void test10() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.move(JavaFXTemplate.gameArr, 2, 3);
		GameLogic.move(JavaFXTemplate.gameArr, 1, 2);
		GameLogic.move(JavaFXTemplate.gameArr, 3, 4);
		GameLogic.move(JavaFXTemplate.gameArr, 5, 0);
		GameLogic.move(JavaFXTemplate.gameArr, 4, 3);
		GameLogic.newGame(JavaFXTemplate.gameArr);
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				assertFalse(JavaFXTemplate.gameArr[i][j].valid);
				JavaFXTemplate.gameArr[i][j].id = 0;
				JavaFXTemplate.gameArr[i][j].setStyle("-fx-background-color: lightgrey;");
				JavaFXTemplate.gameArr[i][j].setDisable(false);
			}
		}
		assertEquals(GameLogic.stack.size(), 0);
	}

	@Test
	void test11() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.move(JavaFXTemplate.gameArr, 1, 3);
		GameLogic.move(JavaFXTemplate.gameArr, 3, 5);
		GameLogic.undo(JavaFXTemplate.gameArr);
		assertEquals(0, JavaFXTemplate.gameArr[3][5].id);
	}

	@Test
	void test12() throws FileNotFoundException {
		new JavaFXTemplate();
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				JavaFXTemplate.gameArr[i][j] = new GameButton(i, j);
			}
		}
		testing = new GameLogic();
		GameLogic.move(JavaFXTemplate.gameArr, 3, 5);
		GameLogic.undo(JavaFXTemplate.gameArr);
		assertEquals(false, JavaFXTemplate.gameArr[3][5].valid);
	}

}
