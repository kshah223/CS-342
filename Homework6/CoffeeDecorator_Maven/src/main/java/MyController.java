import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;

public class MyController implements Initializable {
	
	@FXML
	private VBox root;

    @FXML
    private Button flavouredSyrup;

	@FXML
	private BorderPane root2;

    @FXML
    private Button milk;
    
    @FXML
    private Button sugar;
    
    @FXML
    private Button cream;

    @FXML
    private Button extraShot;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button placeOrder;
    
    @FXML
    private TextArea customerCopy;

    private static String textEntered = "";
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void addMilk(ActionEvent e) throws IOException {
		textEntered = customerCopy.getText();
		customerCopy.setText(textEntered + "\nMilk: $1.00");
		milk.setDisable(true);
	}

	public void addSugar(ActionEvent e) throws IOException{
		textEntered = customerCopy.getText();
		customerCopy.setText(textEntered + "\nSugar: $0.50");
		sugar.setDisable(true);
	}

	public void addCream(ActionEvent e) throws IOException {
		textEntered = customerCopy.getText();
		customerCopy.setText(textEntered + "\nCream: $0.50");
		cream.setDisable(true);
	}

	public void addExtraShot(ActionEvent e) throws IOException {
		textEntered = customerCopy.getText();
		customerCopy.setText(textEntered + "\nExtra Shot: $1.20");
		extraShot.setDisable(true);
	}

	public void addFlavouredSyrup(ActionEvent e) throws IOException {
		textEntered = customerCopy.getText();
		customerCopy.setText(textEntered + "\nflavoured Syrup: $0.45");
		flavouredSyrup.setDisable(true);
	}

	public void addReset() {
		milk.setDisable(false);
		sugar.setDisable(false);
		cream.setDisable(false);
		extraShot.setDisable(false);
		flavouredSyrup.setDisable(false);
		placeOrder.setDisable(false);
		reset.setDisable(false);
	}

    public void addPlaceOrder(double cost) {
		milk.setDisable(true);
		sugar.setDisable(true);
		cream.setDisable(true);
		extraShot.setDisable(true);
		flavouredSyrup.setDisable(true);
		placeOrder.setDisable(true);
		reset.setDisable(false);
		customerCopy.setText(textEntered + "\n\nTotal: $"+ cost);
    }

	public void placeOrder(ActionEvent e) throws IOException {
		textEntered = customerCopy.getText();

		Coffee customerOrder = new BasicCoffee();
		if (milk.isDisabled())
			customerOrder = new Milk(customerOrder);

		if (sugar.isDisabled())
			customerOrder = new Sugar(customerOrder);

		if (cream.isDisabled())
			customerOrder = new Cream(customerOrder);

		if (extraShot.isDisabled())
			customerOrder = new ExtraShot(customerOrder);

		if (flavouredSyrup.isDisabled())
			customerOrder = new flavouredSyrup(customerOrder);

		double finalCost = customerOrder.makeCoffee();
		System.out.println("Total: $"+ finalCost);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml.fxml"));
		Parent root2 = loader.load(); //load view into parent
		MyController myctr = loader.getController();//get controller created by FXMLLoader
		myctr.addPlaceOrder(finalCost);
		root2.getStylesheets().add("/styles/style.css");//set style

		root.getScene().setRoot(root2);//update scene graph
	}

	public void reset(ActionEvent e) throws IOException {
		customerCopy.setText("Order Details: \n");
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml.fxml"));
        Parent root2 = loader.load(); //load view into parent
        MyController myctr = loader.getController();//get controller created by FXMLLoader
        myctr.addReset();//use MyLoader class method for forReset()
        root2.getStylesheets().add("/styles/style.css");//set style
        
        root.getScene().setRoot(root2);//update scene graph
	}
}