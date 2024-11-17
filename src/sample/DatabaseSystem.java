package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import sample.*;

public class DatabaseSystem {

    public static List<Player> playerList;
    public static List<Club> clubList = new ArrayList();
    public static List<Player> marketList = new ArrayList<Player>();

    static boolean mainMenu = true;
    static boolean option1Menu = false;
    static boolean option2Menu = false;
    static boolean option3Menu = false;
    static boolean option4 = false;
    static boolean exitProgram = false;

    //option3 of main menu related
    public static void option3MenuRunning()
        {
            System.out.println("Add Player:");
            Player p = new Player();

            boolean validName = false;
            while (!validName)
            {
                System.out.println("Player Name Required");
                String playerName = CommandLineInput();
                validName = AddPlayer.isValidName(playerName,playerList);

                if(validName)
                {
                    p.setName(playerName);
                    break;
                }
                else
                {
                    System.out.println("***");
                    System.out.println("Invalid name or this name already exists");
                    System.out.println("***");
                }
            }

            boolean validAge = false;
            while (!validAge)
            {
                System.out.println("Player Age Required(Must be a positive integer in years)");
                String s = CommandLineInput();
                int age = Integer.parseInt(s);
                validAge = AddPlayer.isValidAge(age);

                if(validAge)
                {
                    p.setAge(age);
                    break;
                }
                else
                {
                    System.out.println("***");
                    System.out.println("Invalid age input");
                    System.out.println("***");
                }
            }

            boolean validHeight = false;
            while (!validHeight)
            {
                System.out.println("Player Height Required(Must be a positive double)");
                String s = CommandLineInput();
                double height = Double.parseDouble(s);
                validHeight = AddPlayer.isValidHeight(height);

                if(validHeight)
                {
                    p.setHeight(height);
                    break;
                }
                else
                {
                    System.out.println("***");
                    System.out.println("Invalid height input");
                    System.out.println("***");
                }
            }

            boolean validSalary = false;
            while (!validSalary)
            {
                System.out.println("Player Weekly Salary Required(Must be a positive double)");
                String s = CommandLineInput();
                double salary = Double.parseDouble(s);
                validSalary = AddPlayer.isValidSalary(salary);

                if(validSalary)
                {
                    p.setWeeklySalary(salary);
                    break;
                }
                else
                {
                    System.out.println("***");
                    System.out.println("Invalid weekly salary input");
                    System.out.println("***");
                }
            }

            int validClub = 0;
            while (validClub == 0)
            {
                System.out.println("Club Name Required");
                String clubName = CommandLineInput();
                validClub = AddPlayer.isValidClub(clubName,clubList);

                if(validClub == 1)           // return 1 if valid and ok
                {
                    p.setClub(clubName);
                    break;
                }
                else if (validClub == 2)     // return 2 if new club name entered
                {
                    Club c = new Club();
                    c.setName(clubName);
                    c.setPlayerCount(0);

                    clubList.add(c);
                    System.out.println("New Club added");
                    p.setClub(clubName);
                    break;
                }
                else if(validClub == 0)      // return 0 if not vacant
                {
                    System.out.println("***");
                    System.out.println("This CLub is full. No vacant place for a new player");
                    System.out.println("***");
                }

            }

        System.out.println("Country Name Required");
        String country = CommandLineInput();
        p.setCountry(country);

        boolean validPosition = false;
        while (!validPosition)
        {
            System.out.println("Player Position Required(can be Goalkeeper, Defender, Midfielder, Forward)");
            String position = CommandLineInput();
            validPosition = AddPlayer.isValidPosition(position);

            if(validPosition)
            {
                p.setPosition(position);
                break;
            }
            else
            {
                System.out.println("***");
                System.out.println("Not a valid Position");
                System.out.println("***");
            }
        }

        int validNumber = 0;
        while (validNumber != 1)
        {
            System.out.println("Player Number Required(Must be a positive integer in years)");
            String s = CommandLineInput();
            int num = Integer.parseInt(s);
            validNumber = AddPlayer.isValidNumber(num,clubList,p.getClub());

            if(validNumber == 1)
            {
                p.setNumber(num);
                break;
            }
            else if (validNumber == 2)
            {
                System.out.println("***");
                System.out.println("Number already occupied.");
                System.out.println("***");
            }
            else if (validNumber == 0)
            {
                System.out.println("***");
                System.out.println("Invalid player number input");
                System.out.println("***");
            }
        }
        playerList.add(p);



        for(Club c : clubList)
        {
            if(p.getClub().equals(c.getName()))
            {
                c.addPlayer(p);
            }
        }


        System.out.println("Adding new player...");
        p.PrintPlayerInfo();

        option3Menu = false;
        mainMenu = true;

    }

    // option 2 of main menu related
    public static void showOption2Menu()
    {
        System.out.println();
        System.out.println("Club Searching Options: ");
        System.out.println("(1) Player(s) with the maximum salary of a club");
        System.out.println("(2) Player(s) with the maximum age of a club");
        System.out.println("(3) Player(s) with the maximum height of a club");
        System.out.println("(4) Total yearly salary of a club");
        System.out.println("(5) Back to Main Menu");
    }

    public static void option2MenuRunning()
    {
        String s = CommandLineInput();
        int command = 0;
        try {
            command = Integer.parseInt(s);
        }catch (Exception e)
        {
            System.out.println("Choose a correct option from the menu");
            return;
        }
        if(command == 1)
        {
            System.out.println("Club Name Required");
            String club = CommandLineInput();
            List<Player> pp = SearchingAndOthers.maxSalaryPlayerOfClub(club,clubList);
            if(pp.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such club with this name");
                System.out.println("***");
            }
            else
            {
                for (Player p : pp)
                {
                    p.PrintPlayerInfo();
                }
            }
        }
        else if(command == 2)
        {
            System.out.println("Club Name Required");
            String club = CommandLineInput();
            List<Player> pp = SearchingAndOthers.maxAgePlayerOfClub(club, clubList);
            if(pp.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such club with this name");
                System.out.println("***");
            }
            else
            {
                for (Player p : pp)
                {
                    p.PrintPlayerInfo();
                }
            }
        }
        else if(command == 3)
        {
            System.out.println("Club Name Required");
            String club = CommandLineInput();
            List<Player> pp = SearchingAndOthers.maxHeightPlayerOfClub(club,clubList);
            if(pp.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such club with this name");
                System.out.println("***");
            }
            else
            {
                for (Player p : pp)
                {
                    p.PrintPlayerInfo();
                }
            }
        }
        else if(command == 4)
        {
            System.out.println("Club Name Required");
            String club = CommandLineInput();
            double ys = SearchingAndOthers.totalYearlySalaryOfClub(club,clubList);
            if(ys == -1)
            {
                System.out.println("***");
                System.out.println("No such club with this name");
                System.out.println("***");
            }
            else
            {
                System.out.println("Total yearly salary for Club " + club + ": " + ys);
            }
        }
        else if(command == 5)
        {
            option2Menu = false;
            mainMenu = true;
        }
        else
        {
            System.out.println("***");
            System.out.println("Wrong option selected. Please choose an option from 1-5 and enter the corresponding number");
            System.out.println("***");
        }
    }

    // option 1 of main menu related

    public static void showOption1Menu()
    {
        System.out.println();
        System.out.println("Player Searching Options: ");
        System.out.println("(1) By Player Name");
        System.out.println("(2) By Club and Country");
        System.out.println("(3) By Position");
        System.out.println("(4) By Salary Range");
        System.out.println("(5) Country-wise player count");
        System.out.println("(6) Position-wise player count");
        System.out.println("(7) Back to Main Menu");
    }

    public static void option1MenuRunning()
    {
        String s = CommandLineInput();
        int command = 0;
        try {
            command = Integer.parseInt(s);
        }catch (Exception e)
        {
            System.out.println("Choose a correct option from the menu");
            return;
        }

        if(command == 1)
        {
            System.out.println("Player Name Required");
            String name = CommandLineInput();
            List<Player> p = SearchingAndOthers.searchByPlayerName(name,playerList);
            if(p.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such player with this name");
                System.out.println("***");
            }
            else
            {
                for(Player pl : p)
                pl.PrintPlayerInfo();
            }
        }
        else if(command == 2)
        {
            System.out.println("Club Name Required -> You can also type \"ANY\"");
            String clubName = CommandLineInput();

            System.out.println("Country Name Required -> You can also type \"ANY\"");
            String countryName = CommandLineInput();

            List<Player> playerListFInal = SearchingAndOthers.searchByCountryAndClub(countryName,clubName,playerList);
            if(playerListFInal.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such player with this Country / Club");
                System.out.println("***");
            }
            else
            {
                for(Player pl : playerListFInal)
                    pl.PrintPlayerInfo();
            }
        }
        else if(command == 3)
        {
            System.out.println("Position Name Required");
            String name = CommandLineInput();
            List<Player> playerListFInal = SearchingAndOthers.searchByPosition(name,playerList);
            if(playerListFInal.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such player with this Position");
                System.out.println("***");
            }
            else
            {
                for(Player pl : playerListFInal)
                    pl.PrintPlayerInfo();
            }
        }
        else if(command == 4)
        {
            System.out.println("Minimum Weekly Salary Required");
            String s1 = CommandLineInput();
            double min = Double.parseDouble(s1);

            System.out.println("Maximum Weekly Salary Required");
            String s2 = CommandLineInput();
            double max = Double.parseDouble(s2);

            List<Player> playerListFInal = SearchingAndOthers.searchBySalaryRange(min,max,playerList);
            if(playerListFInal.isEmpty())
            {
                System.out.println("***");
                System.out.println("No such player within this salary range");
                System.out.println("***");
            }
            else
            {
                for(Player pl : playerListFInal)
                    pl.PrintPlayerInfo();
            }
        }
        else if(command == 5)
        {

            HashMap<String,Integer> countryWiseMap = SearchingAndOthers.searchCountryWisePlayerCount(playerList);
            for(Map.Entry me  : countryWiseMap.entrySet())
            {
                System.out.println("Players for position " + me.getKey() + " : " + me.getValue());
            }
        }
        else if(command == 6)
        {
            HashMap<String,Integer> positionWiseMap = SearchingAndOthers.searchPositionWisePlayerCount(playerList);
            for(Map.Entry me  : positionWiseMap.entrySet())
            {
                System.out.println("Players for position " + me.getKey() + " : " + me.getValue());
            }
        }
        else if(command == 7)
        {
            option1Menu = false;
            mainMenu = true;
        }
        else
        {
            System.out.println("***");
            System.out.println("Wrong option selected. Please choose an option from 1-6 and enter the corresponding number");
            System.out.println("***");
        }
    }


    public static String CommandLineInput(){
        Scanner scn = new Scanner(System.in);
        System.out.println(">> Input plz: ");
        //scn.close();

        return scn.nextLine();
    }

    public static void showMainMenu(){
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("(1) Search Players");
        System.out.println("(2) Search Clubs");
        System.out.println("(3) Add Player");
        System.out.println("(4) Exit System");
    }

    public static void mainMenuRunning()
    {
        String s = CommandLineInput();
        int command = 0;
        try {
            command = Integer.parseInt(s);
        }catch (Exception e)
        {
            System.out.println("Choose a correct option from the menu");
            return;
        }

        if(command == 1)
        {
            mainMenu = false;
            option1Menu = true;
        }
        else if(command == 2)
        {
            mainMenu = false;
            option2Menu = true;
        }
        else if(command == 3)
        {
            mainMenu = false;
            option3Menu = true;
        }
        else if(command == 4)
        {
            mainMenu = false;
            option4 = true;
        }
        else
        {
            System.out.println("***");
            System.out.println("Wrong option selected. Please choose an option from 1-4 and enter the corresponding number");
            System.out.println("***");
        }
    }

    public static void clubsInitializer()
    {

        List<String> clubNameList = new ArrayList();

        for (Player p: playerList)
        {
            if(!clubNameList.contains(p.getClub()))
            {
                clubNameList.add(p.getClub());
            }
        }

        for (String s : clubNameList)
        {
            Club club = new Club();
            club.setName(s);
            clubList.add(club);
        }

        for (Player p : playerList)
        {
            for (Club c : clubList) {
                if (c.getName().equals(p.getClub()))
                {
                    clubList.get(clubList.indexOf(c)).addPlayer(p);
                }
            }
        }

    }

    public static void marketInit()
    {

    }

    public static void listInit()
    {
        try
        {
            playerList = FileIO.readFromFile();
        }
        catch (Exception e)
        {
            System.out.println(e);
            //System.out.println("File Not Found");
            return;
        }
        clubsInitializer();
    }


    public static void main(String[] args)
    {
        listInit();
//        try
//        {
//            playerList = FileIO.readFromFile();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            //System.out.println("File Not Found");
//            return;
//        }
//        clubsInitializer();

        System.out.println();
        System.out.println("\t\t<<Welcome to Football Player Database System>>");

        while(!exitProgram)
        {
            if (mainMenu)
            {
                showMainMenu();
                mainMenuRunning();
            }
            else if (option1Menu)
            {
                showOption1Menu();
                option1MenuRunning();
            }
            else if (option2Menu)
            {
                showOption2Menu();
                option2MenuRunning();
            }
            else if (option3Menu)
            {
                option3MenuRunning();
            }
            else if(option4)
            {
                try {
                    FileIO.writeToFile(playerList);
                } catch (Exception e) {
                    System.out.println(e);
                }
                //return;
                break;
            }
        }
        System.out.println("Closing program . . .");

    }
}
