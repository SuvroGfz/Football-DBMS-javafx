package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import server.Server;
import util.*;

public class DBMSController {

    String colorCode;
    public final String MAN_U_COLOR_CODE = "#BE2615";
    public final String MAN_C_COLOR_CODE = "#86F2FF";
    public final String LIV_COLOR_CODE = "#C40D0D";
    public final String CHE_COLOR_CODE = "#0043DF";
    public final String ARS_COLOR_CODE = "#FF5555";


    //public javafx.scene.control.Label text;
    private Main main;
    private String currentClubName;
    private Player playerToBuyLastClicked;

//    private MouseEvent prevMouseClick;

    @FXML
    public Label clubNameLabel2;

    @FXML
    public Label text;

    @FXML
    private ImageView cBG;

    @FXML
    private Button logoutButton;

    @FXML
    private Button players;

    @FXML
    private VBox playersList;

    @FXML
    private ListView<String> listOfPlayers;

    @FXML
    private Button searchPlayers;

    @FXML
    private Button onlineConnect;

    @FXML
    private Button searchClubs;

    @FXML
    private VBox vboxTable;

    //CWPC table
    @FXML
    private TableView<CWPCTableClass> countryWisePlayerTable;

    @FXML
    private TableColumn<HashMap<String, Integer>, String> cwpcCountryColumn;

    @FXML
    private TableColumn<HashMap<String, Integer>, Integer> cwpcNumberOfPlayersColumn;

    // END of CWPC

    //PWPC table
    @FXML
    private TableView<PWPCTableClass> positionWisePlayerTable;

    @FXML
    private TableColumn<HashMap<String, Integer>, String> pwpcPositionColumn;

    @FXML
    private TableColumn<HashMap<String, Integer>, Integer> pwpcNumOfPlayersColumn;

    // end of PWPC

    @FXML
    private TableView<PlayerTable1Class> tablePart1;

    @FXML
    private TableView<PlayerTable2Class> tablePart2;

    @FXML
    private TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, String> countryNameColumn;

    @FXML
    private TableColumn<Player, Integer> ageColumn;

    @FXML
    private TableColumn<Player, Double> heightColumn;

    @FXML
    private TableColumn<Player, String> positionColumn;

    @FXML
    private TableColumn<Player, Double> salaryColumn;

    @FXML
    private TableColumn<Player, Integer> playerNoColumn;

    @FXML
    private Button closePlayerViewButton;

    @FXML
    private VBox clubWiseSearchOptionsVBOX;

    @FXML
    private Button maxSalaryPlayer;

    @FXML
    private Button maxAgePlayer;

    @FXML
    private Button maxHeightPlayer;

    @FXML
    private Button totalYearlySalaryButton;

    @FXML
    private Label totalYearlySalaryLabel;

    @FXML
    private VBox maxVBOX;

    @FXML
    private ListView<String> listOfMAXes;

    @FXML
    private VBox playerSearchVBOX;

    @FXML
    private Button searchByName;

    @FXML
    private Button searchByClubAndCounty;

    @FXML
    private Button searchByPosition;

    @FXML
    private Button searchBySalaryRange;

    @FXML
    private Button countryWisePlayerCount;

    @FXML
    private Button positionWisePlayerCount;

    // search by position

    @FXML
    private Button forwardButton;

    @FXML
    private Button midfielderButton;

    @FXML
    private Button defenderButton;

    @FXML
    private Button goalkeeperButton;

    @FXML
    private VBox positionsVBOX;

    @FXML
    private ListView<String> posWiseList;

    // search by name

    @FXML
    private VBox searchByNameVBOX;

    @FXML
    private TextField playerSearchTextBox;

    @FXML
    private Button searchBUTTONplayerName;

    @FXML
    private Button doneBUTTONplayerName;

    @FXML
    private ListView<String> searchByNameList;

    // search by salary range
    @FXML
    private VBox searchBySalaryVBOX;

    @FXML
    private TextField minSalaryText;

    @FXML
    private TextField maxSalaryText;

    @FXML
    private Button salarySearchButton;

    @FXML
    private Button doneSalarySearch;

    @FXML
    private Button clearBUTTONsearchBySalary;

    @FXML
    private ListView<String> salaryWiseList;

    // CC wise search
    @FXML
    private VBox searchByCCVBOX;

    @FXML
    private TextField clubNameText;

    @FXML
    private TextField countryNameText;

    @FXML
    private Button clearBUTTONsearchByCC;

    @FXML
    private Button doneBUTTONsearchByCC;

    @FXML
    private Button searchBUTTONCC;

    @FXML
    private ListView<String> ccWiseList;

    @FXML
    private Button clearBUTTONplayerName;

    // transfer message related
    @FXML
    private VBox transferMarketVBOX;

    @FXML
    private ListView<String> listOfTransferMarket;

    @FXML
    private Button showTransferMarketBUTTON;

    @FXML
    private Button sellPlayersBUTTON;

    @FXML
    private VBox playersToSellVBOX;

    @FXML
    private ListView<String> playersToSellList;

    @FXML
    private VBox sellPriceVBOX;

    @FXML
    private Label playerToSell;

    @FXML
    private TextField sellPrice;

    @FXML
    private Button refreshButton;

    @FXML
    private Button buyButton;

    @FXML
    private VBox buyPlayerDetailsVBOX;

    @FXML
    private TableView<PlayerTable1Class> tablePart11;

    @FXML
    private TableView<PlayerTable2Class> tablePart21;

    @FXML
    private TableColumn<Player, String> playerNameColumn1;

    @FXML
    private TableColumn<Player, String> countryNameColumn1;

    @FXML
    private TableColumn<Player, Integer> ageColumn1;

    @FXML
    private TableColumn<Player, Double> heightColumn1;

    @FXML
    private TableColumn<Player, String> positionColumn1;

    @FXML
    private TableColumn<Player, Double> salaryColumn1;

    @FXML
    private TableColumn<Player, Integer> playerNoColumn1;

    @FXML
    private Label priceLabel;

    @FXML
    private VBox mainVBOX;

    @FXML
    void buyPlayerUI(ActionEvent event)
    {
        // check first if available for you :)
        Player p = playerToBuyLastClicked;
        if(p.getClub().equalsIgnoreCase(currentClubName))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Invalid Buy Request");
            a.setContentText("You cannot buy the player you just sold out! Try buying other players!");
            a.showAndWait();
        }
        else
        {
            if(!(Main.currentClubSignedIn.playerCount < 7))
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Purchase failed");
                a.setContentText("Club is Full!! No vacant place for a player! You must sell any other player to purchase a new Player");
                a.showAndWait();
            }
            else {
                // buy
                playerToBuyLastClicked.setClub(currentClubName);
                BoughtPlayerDTO boughtPlayerDTO = new BoughtPlayerDTO();
                boughtPlayerDTO.setPlayer(playerToBuyLastClicked);

                // update current club players
                Main.currentClubPlayerList.add(playerToBuyLastClicked);
                Main.marketList.remove(playerToBuyLastClicked);
                Main.priceMap.remove(playerToBuyLastClicked);

                int n = Main.currentClubSignedIn.getPlayerCount();
                Main.currentClubSignedIn.getPlayers()[n] = playerToBuyLastClicked;
                n++;
                Main.currentClubSignedIn.setPlayerCount(n);


                boughtPlayerDTO.setNewClub(Main.currentClubSignedIn);


                try {
                    main.getNetworkUtil().write(boughtPlayerDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                colorAllExcept(new Button());
                generateBuyConfirmationAlert(playerToBuyLastClicked.getName());
            }
        }
    }

    void generateBuyConfirmationAlert(String name)
    {
        buyPlayerDetailsVBOX.setVisible(false);
        transferMarketVBOX.setVisible(false);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Player Bought!");
        a.setContentText("Purchased new Player: " + name);
        a.showAndWait();
    }

    @FXML
    void refreshMarket(ActionEvent event)
    {
        UpdateMarketListReq updateMarketListReq = new UpdateMarketListReq();
        updateMarketListReq.setNameOfSender(currentClubName);

        transferMarketVBOX.setVisible(false);
        showingBuyPlayerList(Main.marketList,listOfTransferMarket);
        transferMarketVBOX.setVisible(true);

        buyPlayerDetailsVBOX.setVisible(false);
        if(playerToBuyLastClicked != null)
            initializeTableV2(playerToBuyLastClicked.getName());


        try {
            main.getNetworkUtil().write(updateMarketListReq);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void closePriceBOX(ActionEvent event)
    {
        sellPriceVBOX.setVisible(false);
    }

    @FXML
    void sellPlayerMains(ActionEvent event)
    {
        String soldPlayerName = playerToSell.getText();
        String priceST = sellPrice.getText();
        double price = 0;
        try {
            price = Double.parseDouble(priceST);
        }catch (Exception e)
        {
            System.out.println(e);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Sell Failed!");
            a.setContentText("Invalid Price Entered! The price you entered is not a valid price! Input a number");
            a.showAndWait();
            sellPriceVBOX.setVisible(false);
            return;
        }

        Player soldPlayer = new Player();
        for(Player p : Main.currentClubPlayerList)
        {
            if(p.getName().equalsIgnoreCase(soldPlayerName))
                soldPlayer = p;
        }

        Main.currentClubPlayerList.remove(soldPlayer);

        Club current = Main.currentClubSignedIn;
        System.out.println("Players before removing the sold:::");
        for(int i = 0; i < current.playerCount; i++)
        {
            System.out.println(current.players[i].getName());
        }

        int idx = 0;
        for(int i = 0; i < current.playerCount; i++)
        {
            if(current.players[i].getName().equalsIgnoreCase(soldPlayer.getName()))
            {
                idx = i;
                break;
            }
        }
        for(int i = idx; i < current.playerCount - 1; i++)
        {
            current.players[i] = current.players[i+1];
        }
        current.players[current.playerCount - 1] = new Player();
        current.playerCount--;

        System.out.println("Players after removing sold:::");
        for(int i = 0; i < current.playerCount; i++)
        {
            System.out.println(current.players[i].getName());
        }

        Main.currentClubSignedIn = current;


        TransferDetailsDTO playerTransfer = new TransferDetailsDTO();
        //soldPlayer.setClub("");
        playerTransfer.setPlayer(soldPlayer);
        playerTransfer.setPrice(price);
        playerTransfer.setClub(current);

        try {
            main.getNetworkUtil().write(playerTransfer);
            System.out.println("sent to server for sell");
        } catch (IOException e) {
            e.printStackTrace();
        }

        colorAllExcept(new Button());
        playersToSellVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        sellPrice.setText("");

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Player Sold!");
        a.setContentText("Sold out the player for: " + priceST +  " $");
        a.showAndWait();

    }

    // buy sell ends here ***

    @FXML
    void clearPlayerNameSearch(ActionEvent event)
    {
        playerSearchTextBox.setText("");
    }

    @FXML
    void donePlayerNameSearch(ActionEvent event)
    {
        searchByNameVBOX.setVisible(false);
        playerSearchTextBox.setText("");
    }

    @FXML
    void searchByPlayerNameMains(ActionEvent event)
    {
        String pn = playerSearchTextBox.getText();
        List<Player> pList = SearchingAndOthers.searchByPlayerName(pn,Main.currentClubPlayerList);

        listWiseShowing(pList,searchByNameList);
    }

    @FXML
    void positionNameButtonPressed(ActionEvent event) {
        String pos = " ";

        if(!posWiseList.isVisible())
        {
            posWiseList.setVisible(true);
        }
        else
        {
            posWiseList.setVisible(false);
        }

        if(event.getSource().equals(forwardButton))
        {
            pos = "forward";
        }
        else if(event.getSource().equals(midfielderButton))
        {
            pos = "midfielder";
        }
        else if(event.getSource().equals(defenderButton))
        {
            pos = "defender";
        }
        else if(event.getSource().equals(goalkeeperButton))
        {
            pos = "goalkeeper";
        }
        List<Player> posWisePlayers = SearchingAndOthers.searchByPosition(pos,Main.currentClubPlayerList);

        listWiseShowing(posWisePlayers,posWiseList);


    }

    @FXML
    void positionWisePlayerCountUI(ActionEvent event) {
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        searchByNameVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);

        HashMap<String,Integer> posAndCount = SearchingAndOthers.searchPositionWisePlayerCount(Main.currentClubPlayerList);
        initializePWPC_Table(posAndCount);

        if(positionWisePlayerTable.isVisible())
        {
            positionWisePlayerTable.setVisible(false);
        }
        else
        {
            positionWisePlayerTable.setVisible(true);
        }
    }

    void initializePWPC_Table(HashMap<String,Integer> posAndCountParam)
    {
        ObservableList<PWPCTableClass> data = FXCollections.observableArrayList();

        // adding resources to data . . .
        for(Map.Entry me  : posAndCountParam.entrySet())
        {
            data.add(new PWPCTableClass((String) me.getKey(),(Integer) me.getValue()));
        }

        pwpcPositionColumn.setCellValueFactory(new PropertyValueFactory<HashMap<String,Integer>, String>("position"));
        pwpcNumOfPlayersColumn.setCellValueFactory(new PropertyValueFactory<HashMap<String,Integer>, Integer>("playerCount"));

        positionWisePlayerTable.setItems(data);
    }

    // search by CC

    @FXML
    void searchByClubAndCountyUI(ActionEvent event) {
        positionWisePlayerTable.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        searchByNameVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);


        if(searchByCCVBOX.isVisible())
        {
            searchByCCVBOX.setVisible(false);
        }
        else
        {
            searchByCCVBOX.setVisible(true);
        }
    }

    @FXML
    void searchByCCmains(ActionEvent event) {
        String clubName = clubNameText.getText();
        String countryName = countryNameText.getText();

        List<Player> ccwisePlayers = SearchingAndOthers.searchByCountryAndClub(countryName,clubName,Main.currentClubPlayerList);
        listWiseShowing(ccwisePlayers,ccWiseList);
    }

    @FXML
    void doneCCSearch(ActionEvent event) {
        searchByCCVBOX.setVisible(false);
    }

    @FXML
    void clearCCSearch(ActionEvent event) {
        clubNameText.setText("");
        countryNameText.setText("");
    }

    @FXML
    void searchByNameUI(ActionEvent event) {
        totalYearlySalaryLabel.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);

        if(searchByNameVBOX.isVisible())
        {
            searchByNameVBOX.setVisible(false);
        }
        else
        {
            searchByNameVBOX.setVisible(true);
        }
    }

    @FXML
    void searchByPositionUI(ActionEvent event) {
        searchByNameVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        posWiseList.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        positionWisePlayerTable.setVisible(false);

        if(positionsVBOX.isVisible())
        {
            positionsVBOX.setVisible(false);
        }
        else
        {
            positionsVBOX.setVisible(true);
        }
    }

    // search by Salary Range
    @FXML
    void searchBySalaryRangeUI(ActionEvent event) {
        searchByCCVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        searchByNameVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        positionWisePlayerTable.setVisible(false);


        if(searchBySalaryVBOX.isVisible())
        {
            searchBySalaryVBOX.setVisible(false);
        }
        else
        {
            searchBySalaryVBOX.setVisible(true);
        }
    }

    @FXML
    void searchBySalaryMains(ActionEvent event)
    {
        String minSalText = minSalaryText.getText();
        String maxSalText = maxSalaryText.getText();
        double minSal = 0.0;
        double maxSal = 0.0;
        try {
            minSal = Double.parseDouble(minSalText);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            maxSal = Double.parseDouble(maxSalText);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        List<Player> salWisePlayers = SearchingAndOthers.searchBySalaryRange(minSal,maxSal,Main.currentClubPlayerList);

        listWiseShowing(salWisePlayers,salaryWiseList);
    }

    @FXML
    void doneSalarySearch(ActionEvent event)
    {
        searchBySalaryVBOX.setVisible(false);
    }

    @FXML
    void clearSalarySearch(ActionEvent event)
    {
        minSalaryText.setText("");
        maxSalaryText.setText("");
    }

    /// CWPC table handle

    @FXML
    void countryWisePlayerCountUI(ActionEvent event) {
        positionWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        searchByNameVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);

        HashMap<String,Integer> countryAndCount = SearchingAndOthers.searchCountryWisePlayerCount(Main.currentClubPlayerList);
        initializeCWPC_Table(countryAndCount);


        if(countryWisePlayerTable.isVisible())
        {
            countryWisePlayerTable.setVisible(false);
        }
        else
        {
            countryWisePlayerTable.setVisible(true);
        }
    }

    void initializeCWPC_Table(HashMap<String,Integer> countryAndCountParam)
    {
        ObservableList<CWPCTableClass> data = FXCollections.observableArrayList();

        // adding resources to data . . .
        for(Map.Entry me  : countryAndCountParam.entrySet())
        {
            data.add(new CWPCTableClass((String) me.getKey(),(Integer) me.getValue()));
            //System.out.println("Players for position " + me.getKey() + " : " + me.getValue());
        }

        cwpcCountryColumn.setCellValueFactory(new PropertyValueFactory<HashMap<String,Integer>, String>("country"));
        cwpcNumberOfPlayersColumn.setCellValueFactory(new PropertyValueFactory<HashMap<String,Integer>, Integer>("playerCount"));

        countryWisePlayerTable.setItems(data);
    }


    @FXML
    void maxAgePlayerUI(ActionEvent event) {
        vboxTable.setVisible(false);
        playersList.setVisible(false);

        List<Club> clist = new ArrayList<Club>();
        clist.add(Main.currentClubSignedIn);

        Club currentClub = Main.currentClubSignedIn;

        for (Club c : DatabaseSystem.clubList) {
            if (c.getName().equalsIgnoreCase(text.getText())) {
                currentClub = c;
                break;
            }
        }
        List<Player> maxAgePlayers = SearchingAndOthers.maxAgePlayerOfClub(currentClub.getName(),clist);

        //maxVBOX.setVisible(true);
        showMaxAgeList(maxAgePlayers);

        if(maxVBOX.isVisible())
        {
            maxVBOX.setVisible(false);
        }
        else
        {
            maxVBOX.setVisible(true);
        }

    }

    public void showMaxAgeList(List<Player> paramMaxAgeList)
    {
        ObservableList<String> maxAgePlayers;
        maxAgePlayers = FXCollections.observableArrayList();

        for(Player p : paramMaxAgeList)
        {
            maxAgePlayers.add(p.getName());
            System.out.println(p.getName());
        }
        for(int i = 0; i < maxAgePlayers.size(); i++)
            System.out.println(maxAgePlayers.get(i));


        listOfMAXes.setItems(maxAgePlayers);
        //System.out.println(listOfPlayers.getItems());

        listOfMAXes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + listOfMAXes.getSelectionModel().getSelectedItem());

                {
                    //open or close the windows
                    if (!vboxTable.isVisible()) {
                        initializeTable(listOfMAXes.getSelectionModel().getSelectedItem());
                        vboxTable.setVisible(true);
                    }
//                    else {
//                        vboxTable.setVisible(false);
//                    }
                }


                //prevMouseClick = mouseEvent;

            }
        });

        listOfPlayers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {


//                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//                    a.setContentText(newValue);
//                    a.showAndWait();

                }
        );
    }

    @FXML
    void maxHeightPlayerUI(ActionEvent event) {
        vboxTable.setVisible(false);
        playersList.setVisible(false);

        List<Club> clist = new ArrayList<Club>();
        clist.add(Main.currentClubSignedIn);

        Club currentClub = Main.currentClubSignedIn;

//        for (Club c : DatabaseSystem.clubList) {
//            if (c.getName().equalsIgnoreCase(text.getText())) {
//                currentClub = c;
//                break;
//            }
//        }
        List<Player> maxHeightPlayers = SearchingAndOthers.maxHeightPlayerOfClub(currentClub.getName(),clist);
        showMaxHeightList(maxHeightPlayers);
        //maxVBOX.setVisible(true);

        if(maxVBOX.isVisible())
        {
            maxVBOX.setVisible(false);
        }
        else
        {
            maxVBOX.setVisible(true);
        }
    }

    public void showMaxHeightList(List<Player> paramMaxHeightList)
    {
        ObservableList<String> maxHeightPlayers;
        maxHeightPlayers = FXCollections.observableArrayList();

        for(Player p : paramMaxHeightList)
        {
            maxHeightPlayers.add(p.getName());
            System.out.println(p.getName());
        }
//        for(int i = 0; i < maxHeightPlayers.size(); i++)
//            System.out.println(maxHeightPlayers.get(i));


        listOfMAXes.setItems(maxHeightPlayers);
        //System.out.println(listOfPlayers.getItems());

        listOfMAXes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + listOfMAXes.getSelectionModel().getSelectedItem());

                {
                    //open or close the windows
                    if (!vboxTable.isVisible()) {
                        initializeTable(listOfMAXes.getSelectionModel().getSelectedItem());
                        vboxTable.setVisible(true);
                    }
//                    else {
//                        vboxTable.setVisible(false);
//                    }
                }


                //prevMouseClick = mouseEvent;

            }
        });
    }

    @FXML
    void maxSalaryPlayerUI(ActionEvent event) {
        vboxTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);

        Club currentClub = Main.currentClubSignedIn;



//        for (Club c : DatabaseSystem.clubList) {
//            if (c.getName().equalsIgnoreCase(text.getText())) {
//                currentClub = c;
//                break;
//            }
//        }
        List<Club> clist = new ArrayList<Club>();
        clist.add(Main.currentClubSignedIn);

        List<Player> maxSalaryPlayers = SearchingAndOthers.maxSalaryPlayerOfClub(currentClub.getName(),clist);

        showMaxSalaryList(maxSalaryPlayers);
        //maxVBOX.setVisible(true);

//        if (!vboxTable.isVisible()) {
//            initializeTable(maxSalaryPlayers.get(0).getName());
//            vboxTable.setVisible(true);
//        }
//        else
//        {
//            vboxTable.setVisible(false);
//        }
        if(maxVBOX.isVisible())
        {
            maxVBOX.setVisible(false);
        }
        else
        {
            maxVBOX.setVisible(true);
        }
    }

    public void showMaxSalaryList(List<Player> paramMaxSalaryList)
    {
        ObservableList<String> maxSalaryPlayers;
        maxSalaryPlayers = FXCollections.observableArrayList();

        for(Player p : paramMaxSalaryList)
        {
            maxSalaryPlayers.add(p.getName());
            System.out.println(p.getName());
        }

        listOfMAXes.setItems(maxSalaryPlayers);

        listOfMAXes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + listOfMAXes.getSelectionModel().getSelectedItem());

                {
                    //open or close the windows
                    if (!vboxTable.isVisible()) {
                        initializeTable(listOfMAXes.getSelectionModel().getSelectedItem());
                        vboxTable.setVisible(true);
                    }
//                    else {
//                        vboxTable.setVisible(false);
//                    }
                }


                //prevMouseClick = mouseEvent;

            }
        });
    }

    @FXML
    void closeAll()
    {
        clubWiseSearchOptionsVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        transferMarketVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        searchByNameVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        playerSearchVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);

        colorAllExcept(new Button());
    }


    // *** main menu buttons actions here***

    @FXML
    void showSellPlayersUI(ActionEvent event)
    {
        clubWiseSearchOptionsVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        transferMarketVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        searchByNameVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        playerSearchVBOX.setVisible(false);

        colorAllExcept(sellPlayersBUTTON);

        showingSellPlayerList(Main.currentClubPlayerList,playersToSellList);

        if (!playersToSellVBOX.isVisible())
        {
            playersToSellVBOX.setVisible(true);
            sellPlayersBUTTON.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
            sellPlayersBUTTON.setOpacity(.85);
        }
        else
        {
            playersToSellVBOX.setVisible(false);
            sellPlayersBUTTON.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            sellPlayersBUTTON.setOpacity(.85);
        }
    }

    @FXML
    void showTransferMarketUI(ActionEvent event)
    {
        clubWiseSearchOptionsVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        searchByNameVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        playerSearchVBOX.setVisible(false);

        colorAllExcept(showTransferMarketBUTTON);

        UpdateMarketListReq updateMarketListReq = new UpdateMarketListReq();
        updateMarketListReq.setNameOfSender(currentClubName);
        try {
            main.getNetworkUtil().write(updateMarketListReq);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //listWiseShowing();
        showingBuyPlayerList(Main.marketList,listOfTransferMarket);


        if(transferMarketVBOX.isVisible())
        {
            transferMarketVBOX.setVisible(false);
            showTransferMarketBUTTON.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            showTransferMarketBUTTON.setOpacity(.85);
        }
        else
        {
            transferMarketVBOX.setVisible(true);
            showTransferMarketBUTTON.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
            showTransferMarketBUTTON.setOpacity(.85);
        }

    }

    @FXML
    void searchClubsUI(ActionEvent event)
    {
        transferMarketVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        searchByNameVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playersList.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        playerSearchVBOX.setVisible(false);

        colorAllExcept(searchClubs);

        if(clubWiseSearchOptionsVBOX.isVisible())
        {
            clubWiseSearchOptionsVBOX.setVisible(false);
            //searchClubs.setStyle("-fx-background-color: #00df78");
            searchClubs.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            searchClubs.setOpacity(.85);
        }
        else
        {
            clubWiseSearchOptionsVBOX.setVisible(true);
            //searchClubs.setStyle("-fx-background-color: #ffffff");
            searchClubs.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff ;-fx-border-color: black");
            searchClubs.setOpacity(.85);
        }
    }

    @FXML
    void searchPlayersUI(ActionEvent event)
    {
        transferMarketVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        searchByNameVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        playersList.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        vboxTable.setVisible(false);
        maxVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);

        colorAllExcept(searchPlayers);

        if(playerSearchVBOX.isVisible())
        {
            playerSearchVBOX.setVisible(false);
            searchPlayers.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            searchPlayers.setOpacity(.85);
        }
        else
        {
            playerSearchVBOX.setVisible(true);
            searchPlayers.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
            searchPlayers.setOpacity(.85);

        }
    }

    @FXML
    void showPlayers(ActionEvent event)
    {
        transferMarketVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByNameVBOX.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        vboxTable.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        maxVBOX.setVisible(false);
        totalYearlySalaryLabel.setVisible(false);
        playerSearchVBOX.setVisible(false);
        //System.out.println("showing players");

        colorAllExcept(players);

        listWiseShowing(Main.currentClubPlayerList,listOfPlayers);

        if (playersList.isVisible()) {
            playersList.setVisible(false);
            players.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            players.setOpacity(.85);
        }else {
            playersList.setVisible(true);
            players.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
            players.setOpacity(.85);
        }
    }

    @FXML
    void totalYearlySalaryOfClub(ActionEvent event)
    {
        transferMarketVBOX.setVisible(false);
        sellPriceVBOX.setVisible(false);
        buyPlayerDetailsVBOX.setVisible(false);
        playersToSellVBOX.setVisible(false);
        positionWisePlayerTable.setVisible(false);
        searchByCCVBOX.setVisible(false);
        searchBySalaryVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        searchByNameVBOX.setVisible(false);
        countryWisePlayerTable.setVisible(false);
        vboxTable.setVisible(false);
        clubWiseSearchOptionsVBOX.setVisible(false);
        maxVBOX.setVisible(false);
        playersList.setVisible(false);
        posWiseList.setVisible(false);
        positionsVBOX.setVisible(false);
        playerSearchVBOX.setVisible(false);

        colorAllExcept(totalYearlySalaryButton);

//        Club currentClub = new Club();
//        for (Club c : DatabaseSystem.clubList) {
//            if (c.getName().equalsIgnoreCase(text.getText())) {
//                currentClub = c;
//                break;
//            }
//        }

        List<Club> clist = new ArrayList<Club>();
        clist.add(Main.currentClubSignedIn);

        Double d = SearchingAndOthers.totalYearlySalaryOfClub(Main.currentClubSignedIn.getName(),clist);
        System.out.println(d);
        System.out.println(Main.currentClubSignedIn.getName());
        Double d1 = d/1000000;
        totalYearlySalaryLabel.setText(String.valueOf(d1)+" M"+" $");

        if(totalYearlySalaryLabel.isVisible())
        {
            totalYearlySalaryLabel.setVisible(false);
            totalYearlySalaryButton.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
            totalYearlySalaryButton.setOpacity(.85);
        }
        else
        {
            totalYearlySalaryLabel.setVisible(true);
            totalYearlySalaryButton.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
            totalYearlySalaryButton.setOpacity(.85);

        }
    }

    @FXML
    void showHideMenu()
    {
        if(mainVBOX.isVisible())
        {
            mainVBOX.setVisible(false);
        }
        else
        {
            mainVBOX.setVisible(true);
        }
    }

    // *** end of main menu options ***

    void colorAllExcept(Button b)
    {
        searchClubs.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        searchClubs.setOpacity(.85);

        searchPlayers.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        searchPlayers.setOpacity(.85);

        totalYearlySalaryButton.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        totalYearlySalaryButton.setOpacity(.85);

        players.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        players.setOpacity(.85);

        sellPlayersBUTTON.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        sellPlayersBUTTON.setOpacity(.85);

        showTransferMarketBUTTON.setStyle("-fx-text-fill:  #ffffff; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #00df78; -fx-border-color: black");
        showTransferMarketBUTTON.setOpacity(.85);

        b.setStyle("-fx-text-fill:  #00df78; -fx-font-size: 19px; -fx-font-family: 'Cooper Black';-fx-background-color: #ffffff; -fx-border-color: black");
        b.setOpacity(.85);
    }

    @FXML
    void closePlayerView(ActionEvent event) {
        vboxTable.setVisible(false);
    }

    @FXML
    void closePlayerView2(ActionEvent event)
    {
        buyPlayerDetailsVBOX.setVisible(false);
    }

    public void initializeTable(String pName) {
        ObservableList<PlayerTable1Class> data = FXCollections.observableArrayList();
        for (Player p : Main.currentClubPlayerList) {
            if (p.getName().equalsIgnoreCase(pName)){
                data.add(new PlayerTable1Class(p.getName(), p.getCountry(), p.getAge()));
                break;
            }
        }
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        countryNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
        tablePart1.setItems(data);

        ObservableList<PlayerTable2Class> data2 = FXCollections.observableArrayList();
        for (Player p : Main.currentClubPlayerList) {
            if (p.getName().equalsIgnoreCase(pName)){
                data2.add(new PlayerTable2Class(p.getPosition(),p.getHeight(),p.getWeeklySalary(),p.getNumber()));
                break;
            }
        }
        heightColumn.setCellValueFactory(new PropertyValueFactory<Player, Double>("height"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Player, Double>("salary"));
        playerNoColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        tablePart2.setItems(data2);
    }

    @FXML
    void logoutButtonAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogoutDTO logoutDTO = new LogoutDTO();
        logoutDTO.setClub(Main.currentClubSignedIn);
        logoutDTO.setClubName(currentClubName);
        logoutDTO.setPlayerList(Main.currentClubPlayerList);
        try {
            main.getNetworkUtil().write(logoutDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkColor(String cName) {
        String colorCodeLocal;
        if (cName.equalsIgnoreCase("Manchester city")) {
            colorCodeLocal = MAN_C_COLOR_CODE;
        } else if (cName.equalsIgnoreCase("Manchester united")) {
            colorCodeLocal = MAN_U_COLOR_CODE;
        } else if (cName.equalsIgnoreCase("liverpool")) {
            colorCodeLocal = LIV_COLOR_CODE;
        } else if (cName.equalsIgnoreCase("chelsea")) {
            colorCodeLocal = CHE_COLOR_CODE;
        } else if (cName.equalsIgnoreCase("arsenal")) {
            colorCodeLocal = ARS_COLOR_CODE;
        } else {
            colorCodeLocal = "#FFFFFF";
        }
        return colorCodeLocal;
    }

    public void initializeDBMS(String a) {
        text.setText(a.toUpperCase());
        clubNameLabel2.setText(a.toUpperCase());
        //showList();
        String imageName = a.toUpperCase() + ".jpg";
        System.out.println(imageName);
        Image img = new Image(Main.class.getResourceAsStream(imageName));
        try {
            cBG.setImage(img);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("image not found for the particular club");
            cBG.setImage(new Image(Main.class.getResourceAsStream("fbg.jpg")));
        }

        currentClubName = a;
    }

    public void setMain(Main m) {
        this.main = m;
    }



    public void showList() {
        ObservableList<String> namesOfPlayers;
        namesOfPlayers = FXCollections.observableArrayList();

        Club currentClub = Main.currentClubSignedIn;
        System.out.println(currentClub.getName());

//        for (Club c : DatabaseSystem.clubList) {
//            if (c.getName().equalsIgnoreCase(text.getText())) {
//                currentClub = c;
//                break;
//            }
//        }


        System.out.println(currentClub.getName());
        System.out.println(currentClub.players.length);
        for (int i = 0; i < currentClub.playerCount; i++) {
            Player p = currentClub.players[i];
            try {
                //p.PrintPlayerInfo();
                namesOfPlayers.add(p.getName());
                System.out.println("player added " + (i + 1));
                System.out.println(namesOfPlayers.isEmpty());
            } catch (Exception e) {
                System.out.println("vacant for a player");
            }

        }

        listOfPlayers.setItems(namesOfPlayers);
        //System.out.println(listOfPlayers.getItems());

        listOfPlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + listOfPlayers.getSelectionModel().getSelectedItem());

                {
                    //open or close the windows
                    if (!vboxTable.isVisible()) {
                        initializeTable(listOfPlayers.getSelectionModel().getSelectedItem());
                        vboxTable.setVisible(true);
                    }
//                    else {
//                        vboxTable.setVisible(false);
//                    }
                }


                //prevMouseClick = mouseEvent;

            }
        });

        listOfPlayers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {


//                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//                    a.setContentText(newValue);
//                    a.showAndWait();

                }
        );

    }

    public void listWiseShowing(List<Player> listToShow, ListView<String> whereToShow) {
        ObservableList<String> namesOfPlayers;
        namesOfPlayers = FXCollections.observableArrayList();

        if(!listToShow.isEmpty()) {
            for (Player p : listToShow) {
                namesOfPlayers.add(p.getName());
            }
        }
        else
        {
            namesOfPlayers.add("No Players Found");
        }

        whereToShow.setItems(namesOfPlayers);

        whereToShow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + whereToShow.getSelectionModel().getSelectedItem());

                {
                    //open or close the windows
                    if (!vboxTable.isVisible())
                    {
                        initializeTable(whereToShow.getSelectionModel().getSelectedItem());
                        vboxTable.setVisible(true);
                    }
                    else
                    {
                        vboxTable.setVisible(false);
                    }
                }
            }
        });

    }

    public void showingSellPlayerList(List<Player> listToShow, ListView<String> whereToShow)
    {
        ObservableList<String> namesOfPlayers;
        namesOfPlayers = FXCollections.observableArrayList();

        if(!listToShow.isEmpty()) {
            for (Player p : listToShow) {
                namesOfPlayers.add(p.getName());
            }
        }
        else
        {
            namesOfPlayers.add("No Players Found");
        }

        whereToShow.setItems(namesOfPlayers);

        whereToShow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + whereToShow.getSelectionModel().getSelectedItem());
                String playerClicked = whereToShow.getSelectionModel().getSelectedItem();
                {
                    //open or close the windows
                    String textToSHow = "Want to sell \"" + playerClicked + "\" ?";
                    textToSHow = playerClicked;
                    playerToSell.setText(textToSHow);
                    if(sellPriceVBOX.isVisible())
                    {
                        sellPriceVBOX.setVisible(false);
                    }
                    else
                    {
                        sellPriceVBOX.setVisible(true);
                    }
                }
            }
        });

    }

    public void showingBuyPlayerList(List<Player> listToShow, ListView<String> whereToShow)
    {
        ObservableList<String> namesOfPlayers;
        namesOfPlayers = FXCollections.observableArrayList();

        if(!listToShow.isEmpty()) {
            for (Player p : listToShow) {
                namesOfPlayers.add(p.getName());
            }
        }
        else
        {
            namesOfPlayers.add("No Players Found");
        }

        whereToShow.setItems(namesOfPlayers);

        whereToShow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked on " + whereToShow.getSelectionModel().getSelectedItem());
                String playerClicked = whereToShow.getSelectionModel().getSelectedItem();

                {
                    //open or close the windows
                    initializeTableV2(playerClicked);

                    String textToSHow = "Want to sell \"" + playerClicked + "\" ?";
                    textToSHow = playerClicked;
                    //playerToSell.setText(textToSHow);
                    if(buyPlayerDetailsVBOX.isVisible())
                    {
                        buyPlayerDetailsVBOX.setVisible(false);
                    }
                    else
                    {
                        buyPlayerDetailsVBOX.setVisible(true);
                    }
                }
            }
        });

    }

    public void initializeTableV2(String pName) {
        Player current = new Player();
        ObservableList<PlayerTable1Class> data = FXCollections.observableArrayList();
        for (Player p : Main.marketList) {
            current = p;
            if (p.getName().equalsIgnoreCase(pName)){
                data.add(new PlayerTable1Class(p.getName(), p.getCountry(), p.getAge()));
                break;
            }
        }
        playerToBuyLastClicked = current;
        playerNameColumn1.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        countryNameColumn1.setCellValueFactory(new PropertyValueFactory<Player, String>("country"));
        ageColumn1.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
        tablePart11.setItems(data);

        ObservableList<PlayerTable2Class> data2 = FXCollections.observableArrayList();
        for (Player p : Main.marketList) {
            if (p.getName().equalsIgnoreCase(pName)){
                data2.add(new PlayerTable2Class(p.getPosition(),p.getHeight(),p.getWeeklySalary(),p.getNumber()));
                break;
            }
        }
        heightColumn1.setCellValueFactory(new PropertyValueFactory<Player, Double>("height"));
        positionColumn1.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        salaryColumn1.setCellValueFactory(new PropertyValueFactory<Player, Double>("salary"));
        playerNoColumn1.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        tablePart21.setItems(data2);

        try {
            double price = Main.priceMap.get(current);
            String textToSet = "Price: " + Double.toString(price) + " $";
            priceLabel.setText(textToSet);
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
