import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ServerMainGUI {
    public ListView client_one;
    public ListView client_two;
    public Label num_clients;
    public void disconnect_server(ActionEvent actionEvent) {
        System.exit(1);
    }
}
