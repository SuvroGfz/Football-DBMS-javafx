package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application{

    public Stage stage;
    private NetworkUtil networkUtil;
    public static List<Player> allPlayersList;
    public static Club currentClubSignedIn;
    public static List<Player> currentClubPlayerList;
    public static List<Player> marketList = new ArrayList<Player>();
    public static HashMap<Player,Double> priceMap;

    public void setStage(Stage s)
    {
        stage = s;
    }
    public Stage getStage(){return stage;}

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //DatabaseSystem.listInit();

        stage = primaryStage;
        try{
            connectToServer();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.loadMains();
        controller.setMain(this);


        stage.setTitle("Login");
        stage.setScene(new Scene(root,1280,720));
        stage.show();

        stage.setOnCloseRequest(e -> System. exit(1));
    }

    public void showDBMS(String text, Club currentClub, List<Player> currentPlayerList, List<Player> marketPlayerList) throws Exception {
        currentClubSignedIn = currentClub;
        //System.out.println(currentClub.getName()+"*this is club name place**");
        marketList = marketPlayerList;
        currentClubPlayerList = currentPlayerList;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DBMS.fxml"));
        Parent root = loader.load();

        DBMSController controller = loader.getController();
        controller.initializeDBMS(text);
        controller.setMain(this);

        stage.setTitle(text.toUpperCase());
        stage.setScene(new Scene(root,1280,720));
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }



    public void showNegativeAlert(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Incorrect Credentials");
        a.setContentText("Login failed. Incorrect username/password");
        a.showAndWait();
    }

    public void showMultipleLoginAlert(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Already Logged in");
        a.setContentText("Login failed. This club is already online from some other window");
        a.showAndWait();
    }
}
