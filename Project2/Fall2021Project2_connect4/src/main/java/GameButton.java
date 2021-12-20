import javafx.scene.control.Button;

public class GameButton extends Button{

	int Row;
	int Col;
	boolean valid;
	int id;

	public GameButton(int r, int c) {
		this.Col = c;
		this.Row = r;
		valid = false;
		id = 0;
	}
}