package sample;

import java.io.Serializable;

public class Club implements Serializable {
    String name;
    Player players[];
    int playerCount;

    public Club()
    {
        name = "";
        players = new Player[7];
        playerCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCOunt) {
        this.playerCount = playerCOunt;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public void addPlayer(Player player)
    {
        try {
            players[playerCount] = player;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        playerCount++;
    }

    public void removePlayer(Player player)
    {
        //code later
    }

}
