import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread{
    Socket client;
    ObjectInputStream in;
    ObjectOutputStream out;
    int portNum;
    String serverIP;

    Client(BaccaratInfo transfer) {
        portNum = transfer.getPortNum();
        serverIP = transfer.getServerIP();
    }

    public void run()
    {
        try
        {
            client = new Socket(serverIP,portNum);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            client.setTcpNoDelay(true);
            System.out.println("Server IP: " + serverIP);
            System.out.println("Port: " + portNum);
        }

        catch (Exception ignored) {}

        while(true)
        {
            try
            {

                BaccaratInfo recvPkt = (BaccaratInfo)in.readObject();
                System.out.println(recvPkt.getPortNum());
                System.out.println(recvPkt.getServerIP());
                System.out.println(recvPkt.curData.getRound_result());
                System.out.println(recvPkt.curData.getTotal_winning());
                System.out.print("New Status: ");
                System.out.println(recvPkt.curData.status);

                // print the players hand
                System.out.println("Player's hand:");
                for (int i = 0; i < recvPkt.curData.getPlayerHand().size(); i++)
                {
                    System.out.println(recvPkt.curData.getPlayerHand().get(i).getValue());
                    System.out.println(recvPkt.curData.getPlayerHand().get(i).getSuite());
                }

                // print the bankers hand
                System.out.println("Banker's hand:");
                for (int i = 0; i < recvPkt.curData.getBankerHand().size(); i++)
                {
                    System.out.println(recvPkt.curData.getBankerHand().get(i).getValue());
                    System.out.println(recvPkt.curData.getBankerHand().get(i).getSuite());
                }

            }
            catch (Exception ignored) {}
        }
    }


    public void send(BaccaratInfo gameInfo)
    {
        try {
            System.out.print("Previous Status: ");
            System.out.println(gameInfo.curData.status);
//            out.reset();
            out.writeObject(gameInfo);
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
