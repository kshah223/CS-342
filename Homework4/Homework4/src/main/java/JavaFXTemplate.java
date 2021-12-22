import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	Stage window;
	VBox root;
	cardPayAdapter cardPayAdapter = new cardPayAdapter();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		// TODO Auto-generated method stub
		window  = primaryStage;
		primaryStage.setTitle("Welcome to Adapter pattern!!");
		primaryStage.setScene(primaryWindow());
		primaryStage.show();
	}

	public Scene primaryWindow() throws FileNotFoundException {
		Button btn1 = new Button("START HOMEWORK 4");
        btn1.setOnAction(e -> {
				try {
					window.setScene(secondaryWindow());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

	public Scene secondaryWindow() throws FileNotFoundException {
		Button btn1 = new Button("Enter details");
		TextField txt1 = new TextField("Card number: ");
		TextField txt2 = new TextField("Card owner name: ");
		TextField txt3 = new TextField("Amount: ");
		
		EventHandler<MouseEvent> actionbtn1 = new EventHandler<MouseEvent>() {
        	
			@Override
			public void handle(MouseEvent event) {
				String input;
				cardPayAdapter.setCardNum(txt1.getText());
				input = "Card number: " + cardPayAdapter.getCardNum();
				txt1.setText(input);
				
				cardPayAdapter.setCardOwnerName(txt2.getText());
				input = "Card owner name: " + cardPayAdapter.getCardOwnerName();
				txt2.setText(input);
				
				cardPayAdapter.setAmount(txt3.getText());
				input = "Amount: " + cardPayAdapter.getAmount();
				txt3.setText(input);
				
				btn1.setDisable(true);
				btn1.setText("Done");
        	}
        };
        btn1.setOnMouseClicked(actionbtn1);
        
        FileInputStream input = new FileInputStream("src/main/resources/money.jpg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		root = new VBox(10,txt1,txt2,txt3, btn1);
		root.setAlignment(Pos.BOTTOM_LEFT); 
		root.setBackground(background);
        return new Scene(root, 700, 700);
	}

}
