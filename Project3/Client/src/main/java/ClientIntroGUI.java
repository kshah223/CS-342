import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientIntroGUI extends Application implements Initializable {
    public Button connect_game;
    public TextField input_ip;
    public TextField input_port;
    int portNum;
    String serverIP;
    Client clientConnection;
    ListView<String> listItemsCleint;
    BaccaratInfo gameInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void connect_main_server(ActionEvent actionEvent) throws Exception {
        listItemsCleint = new ListView<>();
        gameInfo = new BaccaratInfo();
        portNum = Integer.parseInt(input_port.getText());
        serverIP = input_ip.getText();

        gameInfo.setPortNum(portNum);
        gameInfo.setServerIP(serverIP);
        clientConnection = new Client(gameInfo);
        clientConnection.start();
        changeScene(actionEvent);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ClientIntroGUI.fxml")));
        primaryStage.setTitle("Baccarat Client");
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientGameGUI.fxml"));
        Parent root = loader.load();

        ClientGameGUI controller = loader.getController();
        controller.setInfo(serverIP,portNum, clientConnection);
        Scene newScene = new Scene(root);
        Stage newStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        newStage.setScene(newScene);
        newStage.show();
    }
}
