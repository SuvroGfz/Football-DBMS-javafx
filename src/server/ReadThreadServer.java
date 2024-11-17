package server;

import sample.Club;
import sample.DatabaseSystem;
import sample.Player;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    public List<Player> clubPlayerList = new ArrayList<Player>();
    public Club clubSignedIN = new Club();
    static public HashMap<Player,Double> playerPriceMap = new HashMap<Player,Double>();
    static public List<Player> playerMarketList = new ArrayList<Player>();
    public static List<String> listOfActiveUsers = new ArrayList<>();


    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }



    public void run() {
//        DatabaseSystem.listInit();
//        allPlayersInServer = DatabaseSystem.playerList;
//        for(Player p : allPlayersInServer)
//        {
//            System.out.println(p.getName());
//        }
//        allClubsInServer = DatabaseSystem.clubList;
//        for(Club c:allClubsInServer)
//        {
//            System.out.println(c.getName());
//        }

        System.out.println("initialized");

        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName().toLowerCase());
                        System.out.println(loginDTO.getUserName() + "   " + loginDTO.getPassword());
                        if(loginDTO.getPassword().equals(password))
                        {
                            System.out.println("correct password!");
                            loginDTO.setStatus(true);

                            if(isOnline(loginDTO.getUserName(),listOfActiveUsers))
                            {
                                loginDTO.setOnline(true);
                                System.out.println("** this club is already online **");
                            }
                            else
                            {
                                loginDTO.setOnline(false);
                                System.out.println("## this club is not online ##");
                                listOfActiveUsers.add(loginDTO.getUserName());
                                System.out.println("Active users: . . . .");
                                for(String s : listOfActiveUsers)
                                {
                                    System.out.println(s);
                                }
                            }
                        }
                        else
                        {
                            loginDTO.setStatus(false);
                            loginDTO.setOnline(false);
                        }

                        networkUtil.write(loginDTO);

                        //if(loginDTO.isStatus()) {


                            createPlayerListAndCurrentClub(loginDTO.getUserName(), Server.getAllClubsInServer());
                            ClubInfoDTO clubInfoDTO = new ClubInfoDTO();
                            clubInfoDTO.setClub(clubSignedIN);
                            clubInfoDTO.setClubWiseList(clubPlayerList);
                            clubInfoDTO.setName(clubSignedIN.getName());
                            clubInfoDTO.setMarketList(playerMarketList);
                            clubInfoDTO.setPriceMap(playerPriceMap);

                            networkUtil.write(clubInfoDTO);
//                        networkUtil.write(clubSignedIN);
//                        networkUtil.write(clubPlayerList);
                            System.out.println(clubInfoDTO.getClub().getName() + " " + clubInfoDTO.getClub().getPlayerCount());
                        //}


                    }
                    else if(o instanceof TransferDetailsDTO)
                    {
                        TransferDetailsDTO playerTransfer = (TransferDetailsDTO)o;

                        System.out.println("received player: " + playerTransfer.getPlayer().getName()+" price: " + playerTransfer.getPrice());
                        playerPriceMap.put(playerTransfer.getPlayer(),playerTransfer.getPrice());

                        Club club = playerTransfer.getClub();
                        List<Club> clubs = Server.getAllClubsInServer();
                        Club clubToRemove = new Club();
                        for(Club c : clubs)
                        {
                            if(c.getName().equalsIgnoreCase(club.getName()))
                            {
                                clubToRemove = c;
                            }
                        }
                        clubs.remove(clubToRemove);
                        clubs.add(club);
                        Server.setAllClubsInServer(clubs);

                        Player player = playerTransfer.getPlayer();
                        List<Player> allPlayersServer = Server.getAllPlayersInServer();
                        Player playerToRemove = new Player();
                        for(Player p : allPlayersServer)
                        {
                            if(p.getName().equalsIgnoreCase(player.getName()))
                            {
                                playerToRemove = p;
                                break;
                            }
                        }

                        allPlayersServer.remove(playerToRemove);
                        allPlayersServer.add(player);
                        Server.setAllPlayersInServer(allPlayersServer);

                        refreshList();
                    }
                    else if(o instanceof UpdateMarketListReq)
                    {
                        UpdateMarketListReq updateMarketListReq = (UpdateMarketListReq) o;

                        refreshList();
                        UpdateMarketList updateMarketList = new UpdateMarketList();
                        updateMarketList.setClubName(updateMarketListReq.getNameOfSender());
                        updateMarketList.setMarketList(playerMarketList);
                        updateMarketList.setPriceMap(playerPriceMap);

                        System.out.println(">> list to send: ");
                        for(Player p : updateMarketList.getMarketList())
                        {
                            System.out.println(p.getName());
                        }

                        try {
                            networkUtil.write(updateMarketList);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else if(o instanceof BoughtPlayerDTO)
                    {
                        BoughtPlayerDTO boughtPlayerDTO = (BoughtPlayerDTO) o;

                        Player player = boughtPlayerDTO.getPlayer();
                        Club club = boughtPlayerDTO.getNewClub();

                        System.out.println("## " + player.getName() + " purchased by " + club.getName());

                        clubPlayerList.add(player);

                        List<Player> allPlayersServer = Server.getAllPlayersInServer();
                        Player playerToRemove = new Player();
                        for(Player p : allPlayersServer)
                        {
                            if(p.getName().equalsIgnoreCase(player.getName()))
                            {
                                playerToRemove = p;
                                break;
                            }
                        }

                        allPlayersServer.remove(playerToRemove);
                        allPlayersServer.add(player);
                        Server.setAllPlayersInServer(allPlayersServer);

                        List<Club> clubs = Server.getAllClubsInServer();
                        Club clubToRemove = new Club();
                        for(Club c : clubs)
                        {
                            if(c.getName().equalsIgnoreCase(club.getName()))
                            {
                                clubToRemove = c;
                            }
                        }
                        clubs.remove(clubToRemove);
                        clubs.add(club);
                        Server.setAllClubsInServer(clubs);

                        Player player2remove = new Player();
                        for (Player p : playerMarketList)
                        {
                            if(player.getName().equals(p.getName()))
                            {
                                player2remove = p;
                            }
                        }


                        playerPriceMap.remove(player2remove);
                        playerMarketList.remove(player2remove);

                        System.out.println("removed player from server market maps and market lists");
                        for(Map.Entry me  : playerPriceMap.entrySet())
                        {
                            Player p = (Player) me.getKey();
                            System.out.println(p.getName());
                        }

                        clubSignedIN = club;

                        Server.priceMap = playerPriceMap;
                        refreshList();
//                        clubSignedIN.getPlayers()[clubSignedIN.getPlayerCount()] = player;
//                        int n = clubSignedIN.getPlayerCount();
//                        n++;
//                        clubSignedIN.setPlayerCount(n);
                    }
                    else if(o instanceof LogoutDTO)
                    {
                        LogoutDTO logoutDTO = (LogoutDTO) o;
                        listOfActiveUsers.remove(logoutDTO.getClubName());
                        clubPlayerList.removeAll(clubPlayerList);
                        //thr.setDaemon(true);
                        //break;

                        List<Club> clubs = Server.getAllClubsInServer();
                        Club clubToRemove = new Club();
                        for(Club c : clubs)
                        {
                            if(c.getName().equalsIgnoreCase(logoutDTO.getClubName()))
                            {
                                clubToRemove = c;
                            }
                        }
                        clubs.remove(clubToRemove);
                        clubs.add(logoutDTO.getClub());
                        Server.setAllClubsInServer(clubs);

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline(String clubName, List<String> allOnline)
    {
        boolean a = false;

        if(!allOnline.isEmpty())
        {
            for (String s : allOnline)
            {
                if (s.equalsIgnoreCase(clubName))
                {
                    a = true;
                    break;
                }
            }
        }

        return a;
    }

    public void createPlayerListAndCurrentClub(String clubName, List<Club> clubs)
    {
        if(clubs.isEmpty())
            System.out.println("club list empty>> inside readthreadserver CPLACC");

        for (Club c : clubs)
        {
            System.out.println(c.getName()+" "+"inside readthreadserver");
            if(clubName.equalsIgnoreCase(c.getName()))
            {
                System.out.println("curr club found");
                System.out.println(c.getName() + " is the club to receive");
                System.out.println(clubSignedIN == null);
                clubSignedIN = c;
                break;
            }
        }

        System.out.println("current club : " + clubSignedIN.getName());

        clubPlayerList.removeAll(clubPlayerList);

        for(int i = 0; i < clubSignedIN.getPlayerCount(); i++)
        {
            clubPlayerList.add(clubSignedIN.getPlayers()[i]);
            System.out.println(clubSignedIN.getPlayers()[i].getName()+" added to current club player list");
        }

    }

    public void refreshList()
    {

        System.out.println("Players in map of server: ");
        for(Map.Entry me  : playerPriceMap.entrySet())
        {
            Player p = (Player) me.getKey();
            System.out.println("Player to sell >>" + p.getName() + " : " + me.getValue());
            if(!playerMarketList.contains(p))
            {
                playerMarketList.add(p);
            }
            clubPlayerList.remove(p);

            List<Player> players = Server.getAllPlayersInServer();
            players.remove(p);

            Server.setAllPlayersInServer(players);
            Server.setMarketList(playerMarketList);
            Server.setPriceMap(playerPriceMap);

        }
        System.out.println("** Server market list updated: ");
        for(Player p : playerMarketList)
        {
            System.out.println(p.getName());
        }
    }

}



