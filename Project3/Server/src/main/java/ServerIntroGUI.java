import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ServerIntroGUI extends Application implements Initializable {

    public Button connect_server;
    public TextField port_input;
    int portNum;
    Server serverConnection;
    ListView<String> listItemsServer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // this is the connect button
    public void connect_thread(ActionEvent actionEvent) throws Exception {
        listItemsServer = new ListView<>();
        portNum = Integer.parseInt(port_input.getText());
        System.out.println("I was here");
        changeScene(actionEvent);
        serverConnection = new Server(portNum);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    //feel free to remove the starter code from this method
    @Override
    public void start(Stage primaryStage) throws Exception {
        listItemsServer = new ListView<String>();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ServerIntroGUI.fxml")));
        primaryStage.setTitle("Baccarat Server");
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeScene(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ServerMainGUI.fxml")));
        Scene newScene = new Scene(newRoot);
        Stage newStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(newScene);
        newStage.show();
    }


}