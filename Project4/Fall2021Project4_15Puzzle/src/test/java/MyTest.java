import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTest {

	Integer[] intArray;

	@BeforeEach
	void initialize() {
		intArray = new Integer[]{8, 6, 4, 7, 15, 5, 9, 12, 10, 1, 13, 3, 2, 0, 14, 11};
	}

	@Test
	void findRow() {
		assertEquals(0, GameLogic.findRow(1), "Incorrect");
	}

	@Test
	void findCol() {
		assertEquals(1, GameLogic.findCol(1), "Incorrect");
	}

	@Test
	void findEmptyCoord() {
		assertEquals(13,GameLogic.findEmptyCoordinates(intArray), "Incorrect");
	}

	@Test
	void checkRight() {
		assertTrue(GameLogic.checkRight(intArray, 1), "Incorrect");
	}

	@Test
	void checkLeft() {
		assertTrue(GameLogic.checkLeft(intArray, 12), "Incorrect");
	}

	@Test
	void checkUp() {
		assertTrue(GameLogic.checkUp(intArray, 10), "Incorrect");
	}

	@Test
	void checkDown() {
		Integer[] arr = {8, 6, 4, 7, 15, 5, 9, 12, 10, 1, 13, 3, 2, 0, 14, 11};
		assertTrue(GameLogic.checkRight(arr, 13), "Incorrect");
	}

	@Test
	void moveToRight() {
		assertFalse(GameLogic.moveToRight(intArray, 13), "Incorrect");
	}

	@Test
	void moveToLeft() {
		assertFalse(GameLogic.moveToLeft(intArray, 13), "Incorrect");
	}

	@Test
	void moveToUp() {
		assertFalse(GameLogic.moveToUp(intArray, 13), "Incorrect");
	}

	@Test
	void moveToDown() {
		assertFalse(GameLogic.moveToDown(intArray, 13), "Incorrect");
	}

	@Test
	void intToString() {
		assertEquals("5",GameLogic.intToString(intArray,5), "Incorrect");
	}

	@Test
	void makeMove() {
		assertFalse(GameLogic.makeMove(intArray, 13), "Incorrect");
	}

	@Test
	void isWon() {
		Integer[] win = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		assertTrue(GameLogic.isWon(win), "Incorrect");
	}

}
