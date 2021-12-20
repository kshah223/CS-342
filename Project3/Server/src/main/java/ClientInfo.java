import javafx.beans.Observable;
import javafx.collections.ObservableList;

public class ClientInfo {
    public ObservableList<String> game_results; // W,L,L,L
    public ObservableList<Integer> client_bets; // amount of bet
    public ObservableList<String> client_bets_PBT; // P, B, T
    public ObservableList<Integer> win; // amount Won/Lost in each round
    public boolean status;              // online/offline
    public String name;                 // name of the player -- Optional
    public boolean another_hand;        // is playing new round
    public int current_bet;             // bet in current round
    public int total_win;               // total win
    public String current_PBT;          // current PBT
}
