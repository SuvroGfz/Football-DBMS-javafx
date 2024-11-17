package util;

import sample.Club;
import sample.Player;

import java.io.Serializable;
import java.util.List;

public class LogoutDTO implements Serializable {
    String clubName;
    Club club;
    List<Player> playerList;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
