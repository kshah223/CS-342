import java.util.ArrayList;

public class BaccaratGame {

    public ArrayList<Card> playerHand;
    public ArrayList<Card> bankerHand;
    public BaccaratDealer theDealer;
    public double currentBet;
    public double totalWinnings;
    public String betChoice;

    public BaccaratGame(){
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        theDealer = new BaccaratDealer();
        currentBet = 0;
        totalWinnings = 0;
        betChoice = "";
    }

    public double evaluateWinnings(){
        BaccaratGameLogic evaluate = new BaccaratGameLogic();
        Card playerCard = null;

        if(evaluate.evaluatePlayerDraw(playerHand)){
            playerCard = theDealer.drawOne();
            playerHand.add(playerCard);
        }
        if(evaluate.evaluateBankerDraw(bankerHand, playerCard)){
            bankerHand.add(theDealer.drawOne());
        }

        if (evaluate.whoWon(playerHand, bankerHand).equals(betChoice)){
            switch (betChoice) {
                case "Player":
                    totalWinnings += currentBet;
                    System.out.println("Player wins!");
                    return currentBet;
                case "Banker":
                    totalWinnings += (0.95) * currentBet;
                    System.out.println("Player wins!");
                    return (0.95) * currentBet;
                case "Tie":
                    totalWinnings += (7) * currentBet;
                    System.out.println("Player wins!");
                    return (7) * currentBet;
            }
        }
        System.out.println("Player loses!");
        return currentBet*(-1);
    }
}
