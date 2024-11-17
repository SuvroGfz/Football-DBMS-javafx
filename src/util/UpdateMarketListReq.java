package util;

import java.io.Serializable;

public class UpdateMarketListReq implements Serializable {
    public String getNameOfSender() {
        return nameOfSender;
    }

    public void setNameOfSender(String nameOfSender) {
        this.nameOfSender = nameOfSender;
    }

    String nameOfSender;

}
