import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server {
    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    int portNum;

    BaccaratInfo transferInstance;

    Server(int port_server){
        server = new TheServer();
        server.start();
        this.portNum = port_server;
    }

    public class TheServer extends Thread
    {
        public void run() {
            try (ServerSocket socket = new ServerSocket(portNum);)
            {
                System.out.println("Server is waiting for a client!");
                System.out.println("Server's port number is " + portNum);

                while(true)
                {
                    ClientThread c = new ClientThread(socket.accept(), count);
//                    callback.accept("Client has connected to server: " + "Client #" + count);
                    clients.add(c);
                    c.start();
                    count++;
                } // end of while
            } // end of try

            catch (Exception e)
            {
//                callback.accept("Server socket did not launch");
            }
        } // end of run
    } // end of TheServer

    class ClientThread extends Thread
    {
        Socket connection;
        int count;
        ObjectOutputStream out;
        ObjectInputStream in;

        ClientThread(Socket s, int count)
        {
            this.connection = s;
            this.count = count;
        }

        public void updateClients(String message)
        {
            for (ClientThread t : clients) {
                try {
                    t.out.writeObject(message);
                } catch (Exception ignored) {
                }
            }
        }

        public void run()
        {
            try
            {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }

            catch (Exception e)
            {
                System.out.println("Stream is not open");
            }

            updateClients("New client on server: Client #" + count);

            while (true)
            {
                try
                {
                    BaccaratInfo recvpkt = (BaccaratInfo)in.readObject();

                    //
                    System.out.println(recvpkt.getServerIP());
                    System.out.println(recvpkt.getPortNum());
                    System.out.println(recvpkt.curData.getBetAmount());
                    System.out.println(recvpkt.curData.getBetPerson());

                    BaccaratGame gameInstance = new BaccaratGame();
                    gameInstance.playerHand = gameInstance.theDealer.dealHand();
                    gameInstance.bankerHand = gameInstance.theDealer.dealHand();

                    System.out.println("Before evaluate winnings: ");
                    // print the players hand
                    System.out.println("Player's hand:");
                    for (int i = 0; i < gameInstance.playerHand.size(); i++)
                    {
                        System.out.println(gameInstance.playerHand.get(i).getValue());
                        System.out.println(gameInstance.playerHand.get(i).getSuite());
                    }

                    // print the bankers hand
                    System.out.println("Banker's hand:");
                    for (int i = 0; i < gameInstance.playerHand.size(); i++)
                    {
                        System.out.println(gameInstance.bankerHand.get(i).getValue());
                        System.out.println(gameInstance.bankerHand.get(i).getSuite());
                    }

                    gameInstance.betChoice = recvpkt.curData.getBetPerson();
                    gameInstance.currentBet = recvpkt.curData.getBetAmount();
                    gameInstance.totalWinnings = recvpkt.curData.getTotal_winning();

                    System.out.println("After evaluate winnings: ");
                    recvpkt.curData.setRound_result(gameInstance.evaluateWinnings());

                    recvpkt.curData.setTotal_winning(gameInstance.totalWinnings);
                    recvpkt.curData.setStatus(5);
                    recvpkt.curData.setBankerHand(gameInstance.bankerHand);
                    recvpkt.curData.setPlayerHand(gameInstance.playerHand);

                    // print the players hand
                    System.out.println("Player's hand:");
                    for (int i = 0; i < gameInstance.playerHand.size(); i++)
                    {
                        System.out.println(gameInstance.playerHand.get(i).getValue());
                        System.out.println(gameInstance.playerHand.get(i).getSuite());
                    }


                    // print the bankers hand
                    System.out.println("Banker's hand:");
                    for (int i = 0; i < gameInstance.playerHand.size(); i++)
                    {
                        System.out.println(gameInstance.bankerHand.get(i).getValue());
                        System.out.println(gameInstance.bankerHand.get(i).getSuite());
                    }

                    send(recvpkt);
                }

                catch (Exception e)
                {
                    clients.remove(this);
                    break;
                }
            } // end of while
        }

        public void send(BaccaratInfo gameInfo)
        {
            try {
//                out.reset();
                out.writeObject(gameInfo);
            }
            catch (IOException e) { e.printStackTrace(); }
        }
    } // end of ClientThread
}

