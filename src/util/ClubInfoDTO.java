package util;

import sample.Club;
import sample.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ClubInfoDTO implements Serializable {
    String name;
    Club club;
    List<Player> clubWiseList;
    List<Player> marketList;
    HashMap<Player,Double> priceMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Player> getClubWiseList() {
        return clubWiseList;
    }

    public void setClubWiseList(List<Player> clubWiseList) {
        this.clubWiseList = clubWiseList;
    }

    public List<Player> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<Player> marketList) {
        this.marketList = marketList;
    }

    public HashMap<Player, Double> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(HashMap<Player, Double> priceMap) {
        this.priceMap = priceMap;
    }
}
