package sample;

public class CWPCTableClass {
    private String country;
    private int playerCount;

    public CWPCTableClass(String country, int playerCount) {
        this.country = country;
        this.playerCount = playerCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
