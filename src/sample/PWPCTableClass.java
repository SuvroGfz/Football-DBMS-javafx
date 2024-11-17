package sample;

public class PWPCTableClass {
    private String position;
    private int playerCount;

    public PWPCTableClass(String position, int playerCount) {
        this.position = position;
        this.playerCount = playerCount;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
