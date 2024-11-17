package util;

import sample.Club;
import sample.Player;

import java.io.Serializable;

public class BoughtPlayerDTO implements Serializable {
    Player player;
    Club newClub;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Club getNewClub() {
        return newClub;
    }

    public void setNewClub(Club newClub) {
        this.newClub = newClub;
    }
}
