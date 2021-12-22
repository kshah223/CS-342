import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoffeeShop extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("/FXML/Myfxml.fxml"));
		primaryStage.setTitle("Kalpkumar Shah Homework 6");
		
		Scene s1 = new Scene(root,500,500);
        s1.getStylesheets().add("/styles/style.css");
		primaryStage.setScene(s1);
		primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) { launch(args); }
}
