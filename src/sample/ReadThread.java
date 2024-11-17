package sample;

import javafx.application.Platform;
import util.ClubInfoDTO;
import util.LoginDTO;
import util.UpdateMarketList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    private List<Player> currentClubPlayerList = new ArrayList<Player>();
    private Club currCLub = new Club();
    private List<Player> marketPlayerList = new ArrayList<Player>();

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null)
                {
                    if (o instanceof LoginDTO)
                    {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());


                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus())
                                {
                                    if(!loginDTO.isOnline())
                                    {
                                        try {
                                            //System.out.println(DatabaseSystem.clubList.isEmpty() + " *   *   * ");
                                            //createPlayerListAndCurrentClub(loginDTO.getUserName(),DatabaseSystem.clubList);

                                            main.showDBMS(loginDTO.getUserName(), currCLub, currentClubPlayerList, marketPlayerList);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else
                                    {
                                        main.showMultipleLoginAlert();
                                    }
                                }
                                else
                                {
                                    main.showNegativeAlert();
                                }

                            }
                        });
                    }
                    else if(o instanceof ClubInfoDTO)
                    {
                        ClubInfoDTO clubInfoDTO = (ClubInfoDTO) o;
                        Main.currentClubPlayerList = clubInfoDTO.getClubWiseList();
                        Main.currentClubSignedIn = clubInfoDTO.getClub();
                        Main.marketList = clubInfoDTO.getMarketList();
                    }
                    else if(o instanceof UpdateMarketList)
                    {
                        UpdateMarketList updateMarketList = (UpdateMarketList) o;
                        Main.marketList = updateMarketList.getMarketList();
                        Main.priceMap = updateMarketList.getPriceMap();
                        System.out.println("** Received new market list: ");

                        for(Player p : updateMarketList.getMarketList()) {
                            System.out.println(p.getName());
                        }

                        System.out.println("** updated main market list **");
                        for(Player p : Main.marketList) {
                            System.out.println(p.getName());
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void createPlayerListAndCurrentClub(String clubName, List<Club> clubs)
//    {
//        if(clubs.isEmpty())
//            System.out.println("club list empty");
//
//        for (Club c : clubs)
//        {
//            if(clubName.equalsIgnoreCase(c.getName()))
//            {
//                System.out.println("curr club found");
//                this.currCLub = c;
//
//                break;
//            }
//        }
//
//
//        for(int i = 0; i < this.currCLub.playerCount; i++)
//        {
//            this.currentClubPlayerList.add(this.currCLub.players[i]);
//        }
//
//    }

//    else if(o instanceof List) {
//        currentClubPlayerList = ((List<Player>) o);
//        Main.currentClubPlayerList = this.currentClubPlayerList;
//    }
//                    else if(o instanceof Club)
//    {
//        currCLub = (Club) o;
//        Main.currentClubSignedIn = currCLub;
//        System.out.println("received current club");
//        System.out.println("cc: " + currCLub.getName() + " inside read thread client <<");
//    }

}



