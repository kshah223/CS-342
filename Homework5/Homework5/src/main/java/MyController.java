import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MyController implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
	private VBox root;
	
	@FXML
	private BorderPane root2;
    
    @FXML
    private TextField txt1;
    
    @FXML
    private TextField txt2;
    
    //static so each instance of controller can access to update 
    private static String textEntered = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

    //method so that the controller created for second view can update the text
    //field with the text from the first view
    public void setText() {
        txt1.setPromptText("enter text here then press button 1");
        System.out.println("hello from setText");
        txt2.setText("final string goes here");
    }
    
    public void setText2() {
        txt2.setText(textEntered);
        System.out.println("hello from setText");
        txt2.setText("final string goes here");
    }

    public void helloMethod(ActionEvent e) throws IOException {

        textEntered = txt1.getText(); //get text entered by user
        System.out.println(textEntered);

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml2.fxml"));
        Parent root2 = loader.load(); //load view into parent
        MyController myctr = loader.getController();//get controller created by FXMLLoader
        myctr.setText();
        root2.getStylesheets().add("/styles/style2.css");//set style

        root.getScene().setRoot(root2);//update scene graph
    }

    public void b1Method(ActionEvent e) throws IOException{

        textEntered = txt1.getText();
        System.out.println(textEntered);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml.fxml"));
        Parent root = loader.load();
        MyController myctr = loader.getController();
        myctr.setText();
        myctr.txt2.setText(textEntered + " : from the center text field!");
        myctr.btn1.setDisable(true);
        myctr.btn1.setText("pressed");
        root2.getScene().setRoot(root);
    }

    public void b2Method(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml.fxml"));
        Parent root = loader.load();
        MyController myctr = loader.getController();
        myctr.txt1.clear();
        myctr.txt2.clear();
        myctr.setText2();
        myctr.btn1.setDisable(false);
        myctr.btn1.setText("button 1");
        root2.getScene().setRoot(root);
    }
}
