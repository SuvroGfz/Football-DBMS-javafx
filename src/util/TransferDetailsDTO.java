package util;

import sample.Club;
import sample.Player;

import java.io.Serializable;

public class TransferDetailsDTO implements Serializable {
    private Player player;
    private double price;
    private Club club;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
