package util;

import sample.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class UpdateMarketList implements Serializable {
    String clubName;
    List<Player> marketList;
    HashMap<Player,Double> priceMap;

    public HashMap<Player, Double> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(HashMap<Player, Double> priceMap) {
        this.priceMap = priceMap;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public List<Player> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<Player> marketList) {
        this.marketList = marketList;
    }
}
