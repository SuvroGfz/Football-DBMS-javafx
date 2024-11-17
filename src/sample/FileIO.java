package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";

    // code from provided lectures starts here...
    public static List<Player> readFromFile() throws Exception {
        List<Player> playerList = new ArrayList();
        // var studentList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        // var br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player player = new Player();
            player.setName(tokens[0]);
            //System.out.print(player.getName());                          // name
            player.setCountry(tokens[1]);                       // country
            //System.out.print(" " + player.getCountry());
            //System.out.println();
            player.setAge(Integer.parseInt(tokens[2]));         // age
            //System.out.print(" " + player.getAge());
            player.setHeight(Double.parseDouble(tokens[3]));    // height
            //System.out.print(" " + player.getHeight());
            player.setClub(tokens[4]);                          // club
            //System.out.print(" " + player.getClub());
            player.setPosition(tokens[5]);                      // position
            //System.out.print(" " + player.getPosition());
            player.setNumber(Integer.parseInt(tokens[6]));      // number
            //System.out.printf(" " + player.getNumber());
            player.setWeeklySalary(Double.parseDouble(tokens[7]));// weekly salary
            //System.out.printf(" " + player.getWeeklySalary());
            //System.out.println();
            playerList.add(player);
        }


        /*for(Player p : playerList){
            System.out.println(p.getNumber() + "," + p.getName() + "," + p.getClub() + "," + p.getAge() + "," + p.getPosition() + "," + p.getHeight() + "," + p.getCountry() + "," + p.getWeeklySalary());
        }*/
        br.close();

        return playerList;
    }

    public static void writeToFile(List<Player> playerList) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        // var bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Player p : playerList) {
            bw.write(p.getName() + "," + p.getCountry() + "," + p.getAge() + "," + p.getHeight() + ",");
            bw.write( p.getClub() + "," + p.getPosition() + ","  + p.getNumber() + "," +  p.getWeeklySalary());
            bw.write("\n");
        }
        bw.close();
    }

}
