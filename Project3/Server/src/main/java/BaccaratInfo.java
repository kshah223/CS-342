import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {

    public class PlayerData implements Serializable{
        private double betAmount;
        private String betPerson; // name, hands, amount, player choice, online/offline
        int status;
        private ArrayList<Card> playerHand;
        private ArrayList<Card> bankerHand;
        private double round_result;
        private double total_winning;

        public double getRound_result() {
            return round_result;
        }

        public void setRound_result(double round_result) {
            this.round_result = round_result;
        }

        public double getTotal_winning() {
            return total_winning;
        }

        public void setTotal_winning(double total_winning) {
            this.total_winning = total_winning;
        }

        public ArrayList<Card> getPlayerHand() {
            return playerHand;
        }

        public void setPlayerHand(ArrayList<Card> playerHand) {
            this.playerHand = playerHand;
        }

        public ArrayList<Card> getBankerHand() {
            return bankerHand;
        }

        public void setBankerHand(ArrayList<Card> bankerHand) {
            this.bankerHand = bankerHand;
        }

        public int getStatus() { return status; }

        public void setStatus(int status) { this.status = status; }

        public void setBetAmount (double amount) { this.betAmount = amount; }
        public void setBetPerson (String person) { this.betPerson = person; }

        public double getBetAmount() { return betAmount; }
        public String getBetPerson() { return betPerson; }
    }
    // ip, port, instance, winning message
    private String serverIP;
    private int portNum;
    PlayerData curData = new PlayerData();


    // setters
    public void setPortNum(int portNum) { this.portNum = portNum; }
    public void setServerIP(String serverIP) { this.serverIP = serverIP; }

    // getters
    public int getPortNum() { return  portNum; }
    public String getServerIP() { return serverIP; }


}
