package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

public class SearchingAndOthers {

    public static List<Player> playerList;
    public static List<Club> clubList = new ArrayList();


    public SearchingAndOthers() {
        try {
            playerList = FileIO.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSearchPlayersMenu()
    {
        System.out.println("(1) By Player Name");
        System.out.println("(2) By Club and Country");
        System.out.println("(3) By Position");
        System.out.println("(4) By Salary Range");
        System.out.println("(5) Country-wise player count");
        System.out.println("(6) Back to Main Menu");
    }

    public static List<Player> searchByPlayerName(String player2search, List<Player> playerList)
    {
        //System.out.print("Player Name ");
        //String player2search = DatabaseSystem.CommandLineInput();
        boolean notFound = true;
        List<Player> pp = new ArrayList<Player>();



        for (Player p : playerList)
        {
            if(p.getName().toLowerCase().contains(player2search.toLowerCase()))
            {
                notFound = false;
                pp.add(p);
                //p.PrintPlayerInfo();
            }

        }
        return pp;
//        if(notFound)
//        {
////            System.out.println("***");
////            System.out.println("No such player with this name");
////            System.out.println("***");
//        }
    }

    public static List<Player> searchByCountryAndClub(String country2search, String club2search, List<Player> playerList)
    {
        //System.out.print("Player Name ");
        //String player2search = DatabaseSystem.CommandLineInput();
        boolean notFound = true;
        List<Player> pp = new ArrayList<Player>();

        if(club2search.equalsIgnoreCase("ANY") && country2search.equalsIgnoreCase("ANY"))
        {
            for(Player p: playerList)
            {
                pp.add(p);
                //p.PrintPlayerInfo();
            }
        }
        else
        {
            for (Player p : playerList)
            {
                if (country2search.equalsIgnoreCase("ANY") && p.getClub().equalsIgnoreCase(club2search))
                {
                    pp.add(p);
                    notFound = false;
                    //p.PrintPlayerInfo();
                }
                else if (club2search.equalsIgnoreCase("ANY") && p.getCountry().equalsIgnoreCase(country2search)) {
                    notFound = false;
                    pp.add(p);
                    //p.PrintPlayerInfo();
                }
                else if (p.getCountry().equalsIgnoreCase(country2search) && p.getClub().equalsIgnoreCase(club2search)) {
                    notFound = false;
                    pp.add(p);
                    //p.PrintPlayerInfo();
                }

            }


//            if (notFound)
//            {
//                System.out.println("***");
//                System.out.println("No such player with this country and club");
//                System.out.println("***");
//            }
        }
        return pp;
    }

    public static List<Player> searchByPosition(String position2search, List<Player> playerList)
    {
        boolean notFound = true;
        List<Player> pp = new ArrayList<Player>();

        for (Player p : playerList)
        {
            if(p.getPosition().equalsIgnoreCase(position2search))
            {
                notFound = false;
                //p.PrintPlayerInfo();
                pp.add(p);
            }

        }
        return pp;
//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such player with this name");
//            System.out.println("***");
//        }
    }

    public static List<Player> searchBySalaryRange(double min, double max, List<Player> playerList)
    {
        boolean notFound = true;
        List<Player> pp = new ArrayList<Player>();

        for (Player p : playerList)
        {
            if(min <= p.getWeeklySalary() && p.getWeeklySalary() <= max)
            {
                notFound = false;
                pp.add(p);
                //p.PrintPlayerInfo();
            }

        }
        return pp;
//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such player with this weekly salary range");
//            System.out.println("***");
//        }
    }

    public static HashMap<String, Integer> searchCountryWisePlayerCount(List<Player> playerList)
    {
        List<String> countries = new ArrayList();
        HashMap<String,Integer> countryWiseMap = new HashMap<String,Integer>();

        for(Player p : playerList)
        {
            if(!countries.contains(p.getCountry()))
            {
                countries.add(p.getCountry());
            }
        }

        int[] numOfPlayers = new int[countries.size()];
        int i = 0;
        for (String c : countries)
        {
            for (Player p:playerList)
            {
                if(c.equals(p.getCountry()))
                {
                    numOfPlayers[i]++;
                }
            }
            i++;
        }

        int j = 0;
        for (String s  : countries)
        {
            countryWiseMap.put(s,numOfPlayers[j]);
            //System.out.println("Number of Players for position " + s + ": " + numberOfPlayers[j]);
            j++;
        }
        return countryWiseMap;
    }

    public static HashMap<String, Integer> searchPositionWisePlayerCount(List<Player> playerList)
    {
        List<String> positionList= new ArrayList();
        //List<Player> players = new ArrayList<Player>();
        HashMap<String,Integer> positionWiseMap = new HashMap<String,Integer>();

        positionList.add("Goalkeeper");
        positionList.add("Defender");
        positionList.add("Midfielder");
        positionList.add("Forward");

        int[] numberOfPlayers = new int[positionList.size()];

        int i  = 0;
        for (String position : positionList)
        {
            for (Player p : playerList)
            {
                if(position.equals(p.getPosition()))
                {
                    numberOfPlayers[i]++;
                }

            }
            i++;
        }


        int j = 0;
        for (String s  : positionList)
        {
            positionWiseMap.put(s,numberOfPlayers[j]);
            //System.out.println("Number of Players for position " + s + ": " + numberOfPlayers[j]);
            j++;
        }
        return positionWiseMap;

    }



    /*public static int countFrequencies(ArrayList<Club> list)
    {

        // hash set is created and elements of
        // arraylist are inserted into it
        Set<Club> clubSet = new HashSet<Club>(list);
        for (Club c : clubSet)
        {
            c.playerCount = Collections.frequency(list, c);
        }
        return 0;

    }*/

    // option 2

    public static List<Player> maxAgePlayerOfClub(String clubName, List<Club> clubs)
    {
        boolean notFound = true;
        List<Player> players = new ArrayList<Player>();

        for(Club c: clubs)
        {
            if(c.getName().equalsIgnoreCase(clubName))
            {
                notFound = false;
                int max = c.players[0].getAge();
                int idx = 0;

                for (int i = 1; i < c.playerCount; i++)
                {
                    if(max < c.players[i].getAge())
                    {
                        max = c.players[i].getAge();
                        idx = i;

                    }
                }
                for (int i = 0; i < c.playerCount; i++)
                {
                    if (max == c.players[i].getAge())
                    {
                        //System.out.println("Max age = " + max);
                        players.add(c.players[i]);
                        //c.players[i].PrintPlayerInfo();
                    }
                }
            }
        }
        return players;

//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such club with this name");
//            System.out.println("***");
//        }
    }


    public static List<Player> maxSalaryPlayerOfClub(String clubName, List<Club> clubs)
    {
        boolean notFound = true;
        List<Player> players = new ArrayList<Player>();
        for(Club c: clubs)
        {
            if(c.getName().equalsIgnoreCase(clubName))
            {
                notFound = false;
                double max = c.players[0].getWeeklySalary();
                int idx = 0;

                for (int i = 1; i < c.playerCount; i++)
                {
                    if(max < c.players[i].getWeeklySalary())
                    {
                        max = c.players[i].getWeeklySalary();
                        idx = i;

                    }
                }
                for (int i = 0; i < c.playerCount; i++)
                {
                    if (max == c.players[i].getWeeklySalary())
                    {
                        //System.out.println("Max salary = " + max);
                        players.add(c.players[i]);
                        //c.players[i].PrintPlayerInfo();
                    }
                }
            }

        }
        return players;

//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such club with this name");
//            System.out.println("***");
//        }
    }

    public static List<Player> maxHeightPlayerOfClub(String clubName, List<Club> clubs)
    {
        boolean notFound = true;
        List<Player> players = new ArrayList<Player>();
        for(Club c: clubs)
        {
            if(c.getName().equalsIgnoreCase(clubName))
            {
                notFound = false;
                double max = c.players[0].getHeight();
                int idx = 0;

                for (int i = 1; i < c.playerCount; i++)
                {
                    if(max < c.players[i].getHeight())
                    {
                        max = c.players[i].getHeight();
                        idx = i;
                    }
                }
                for (int i = 0; i < c.playerCount; i++)
                {
                    if (max == c.players[i].getHeight())
                    {
//                        System.out.println("Max Height = " + max);
//                        c.players[i].PrintPlayerInfo();
                        players.add(c.players[i]);
                    }
                }
            }
        }
        return players;

//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such club with this name");
//            System.out.println("***");
//        }
    }

    public static double totalYearlySalaryOfClub(String clubName, List<Club> clubs)
    {
        boolean notFound = true;
        double yearlySalary = -1;

        for(Club c: clubs)
        {
            if(c.getName().equalsIgnoreCase(clubName))
            {
                notFound = false;

                yearlySalary = 0;

                for (int i = 0; i < c.playerCount; i++)
                {
                    yearlySalary += (52 * c.players[i].getWeeklySalary());
                }

//                System.out.println("Total yearly salary for Club " + c.getName() + ": " + yearlySalary);

            }
        }
        return yearlySalary;

//        if(notFound)
//        {
//            System.out.println("***");
//            System.out.println("No such club with this name");
//            System.out.println("***");
//        }
    }


}
