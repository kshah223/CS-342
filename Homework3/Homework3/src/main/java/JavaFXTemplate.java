import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class JavaFXTemplate<ActionEvent> extends Application {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Kalpkumar Shah Homework 3");
				
		Button btn1 = new Button("button 1");
		Button btn2 = new Button("button 2");
		TextField txt1 = new TextField("enter text here then press button 1");
        TextField txt2 = new TextField("final string goes here");
        
        VBox root = new VBox();
        BorderPane alpha = new BorderPane();
        
        alpha.setCenter(txt1);
        alpha.setRight(txt2);
        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        alpha.setBottom(root);
		
        EventHandler<MouseEvent> actionbtn1 = new EventHandler<MouseEvent>() {
        	
			@Override
			public void handle(MouseEvent event) {
				String input = txt1.getText();
				input = input + " : from the center text field!";
				txt2.setText(input);
				btn2.setDisable(true);
				btn1.setText("pressed");
        	}
        };
        btn1.setOnMouseClicked(actionbtn1);
        
        EventHandler<MouseEvent> actionbtn2 = new EventHandler<MouseEvent>() {
        	
			@Override
			public void handle(MouseEvent event) {
				txt1.clear();
				txt2.clear();
				txt2.setText("final string goes here");
				btn1.setDisable(false);
				btn1.setText("button one");
        	}
        };
        btn2.setOnMouseClicked(actionbtn2);
        
		Scene scene = new Scene(alpha, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
