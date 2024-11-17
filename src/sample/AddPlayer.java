package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

public class AddPlayer
{

    public static boolean isValidName(String playerName,List<Player> playerList)
    {
        boolean validity = true;

        for(Player p : playerList)
        {
            if(p.getName().equals(playerName))
            {
                validity = false;
            }
        }
        return validity;
    }

    public static boolean isValidAge(int age)
    {
        boolean validity = true;
        if(age <= 0)
        {
            validity = false;
        }
        return validity;
    }

    public static boolean isValidHeight(double height)
    {
        boolean validity = true;
        if(height <= 0)
        {
            validity = false;
        }
        return validity;
    }

    public static boolean isValidSalary(double salary)
    {
        boolean validity = true;
        if(salary <= 0)
        {
            validity = false;
        }
        return validity;
    }


    // return 1 if valid and ok
    // return 0 if not vacant
    // return 2 if new club name entered
    public static int isValidClub(String clubName, List<Club> clubList)
    {
        int validity = 2;

        // vacant check
        for(Club c  : clubList)
        {
            if(c.getName().equals(clubName))
            {
                if(c.getPlayerCount() < 7)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
        return validity;

    }

    public static boolean isValidPosition(String position)
    {
        boolean validity = true;

        if(position.contains(" "))
        {
            validity = false;
        }
        else
        {
            if(!(position.equals("Goalkeeper") || position.equals("Midfielder") || position.equals("Defender") || position.equals("Forward") ))
            {
                validity = false;
            }
        }
        return  validity;
    }

    // 1 for ok
    // 0 for invalid
    // 2 for occupied
    public static int isValidNumber(int num, List<Club> clubs, String playerClubName) {
        int validity = 1;
        if(num < 0)
        {
            validity = 0;
        }
        else
        {
            Club playerClub = new Club();
            for (Club c : clubs)
            {
                if(c.getName().equals(playerClubName))
                {
                    playerClub = c;
                }
            }

            for (int i = 0; i < playerClub.getPlayerCount(); i++)
            {
                if (num == playerClub.players[i].getNumber())
                {
                    return 2;
                }
            }
        }
        return  validity;
    }
}
