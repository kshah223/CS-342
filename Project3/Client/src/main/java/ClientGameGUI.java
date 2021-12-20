import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientGameGUI implements Initializable {

    Client clientConnection;

    private String serverIP;
    private int port;
    public Label player_label;
    public Label banker_label;
    public Button draw_button;
    public ChoiceBox chip_values;
    public ChoiceBox num_chips;
    public Label bet_label;
    public Button quit_game;

    public Button player_bet;
    public Button tie_bet;
    public Button banker_bet;

    public ImageView player_card1;
    public ImageView player_card2;
    public ImageView player_card3;

    // banker cards
    public ImageView banker_card1;
    public ImageView banker_card2;
    public ImageView banker_card3;

    // info display stuff
    public Label balance_label;
    public Text bal_amt_text;
    public Label current_bet_label;
    public Text cur_bet_text;
    public Label total_win_label;
    public Text total_win_text;
    public Label round_win_label;
    public Text round_win_text;

    public MenuBar game_menu_bar;
    public Menu game_menu;
    public MenuItem game_menu_rules;

    public String player_choice = "";
    public TextField bet_amount;

    int bet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInfo(String IP, int mainPort, Client pastCLient){
        serverIP = IP;
        port = mainPort;
        clientConnection = pastCLient;
    }

    public void draw_card(ActionEvent actionEvent) throws Exception {
        bet = Integer.parseInt(bet_amount.getText());

        BaccaratInfo gameInfo = new BaccaratInfo();

        gameInfo.curData.setBetAmount(bet);
        gameInfo.curData.setBetPerson(player_choice);
        gameInfo.curData.setStatus(1);
        gameInfo.setServerIP(serverIP);
        gameInfo.setPortNum(port);

        clientConnection.send(gameInfo);


    }

    public void select_player(ActionEvent actionEvent) {
        player_choice = "Player";
        player_bet.setStyle("-fx-background-color: MediumSeaGreen");
        tie_bet.setStyle(null);
        banker_bet.setStyle(null);
    }

    public void select_tie(ActionEvent actionEvent) {
        player_choice = "Tie";
        player_bet.setStyle(null);
        tie_bet.setStyle("-fx-background-color: MediumSeaGreen");
        banker_bet.setStyle(null);
    }

    public void select_banker(ActionEvent actionEvent) {
        player_choice = "Banker";
        player_bet.setStyle(null);
        tie_bet.setStyle(null);
        banker_bet.setStyle("-fx-background-color: MediumSeaGreen");
    }

    public void quit_game(ActionEvent actionEvent) {
        System.exit(-1);
    }
}
