package server;

import sample.Club;
import sample.DatabaseSystem;
import sample.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private static List<Player> allPlayersInServer;
    private ServerSocket serverSocket;
    public static HashMap<String, String> userMap;
    public static HashMap<Player,Double> priceMap;
    private static List<Club> allClubsInServer;
    private static List<Player> marketList;

    Server() {
        userMap = new HashMap<>();
        userMap.put("arsenal", "123");
        userMap.put("liverpool", "123");
        userMap.put("chelsea", "123");
        userMap.put("manchester united", "123");
        userMap.put("manchester city", "123");
        DatabaseSystem.listInit();
        allPlayersInServer = DatabaseSystem.playerList;
        for(Player p : allPlayersInServer)
        {
            System.out.println(p.getName());
        }
        allClubsInServer = DatabaseSystem.clubList;
        for(Club c : allClubsInServer)
        {
            System.out.println(c.getName());
        }
        marketList = new ArrayList<Player>();
        priceMap = new HashMap<Player,Double>();

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil);
    }

    public static List<Player> getAllPlayersInServer() {
        return allPlayersInServer;
    }

    public static List<Club> getAllClubsInServer() {
        return allClubsInServer;
    }

    public static void setAllPlayersInServer(List<Player> allPlayersInServer) {
        Server.allPlayersInServer = allPlayersInServer;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setUserMap(HashMap<String, String> userMap) {
        this.userMap = userMap;
    }

    public static void setAllClubsInServer(List<Club> allClubsInServer) {
        Server.allClubsInServer = allClubsInServer;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static HashMap<String, String> getUserMap() {
        return userMap;
    }

    public static List<Player> getMarketList() {
        return marketList;
    }

    public static void setMarketList(List<Player> marketList) {
        Server.marketList = marketList;
    }

    public static HashMap<Player, Double> getPriceMap() {
        return priceMap;
    }

    public static void setPriceMap(HashMap<Player, Double> priceMap) {
        Server.priceMap = priceMap;
    }

    public static void main(String[] args) {
        new Server();
    }
}
