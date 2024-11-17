package sample;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import util.LoginDTO;



import java.io.IOException;
import javafx.geometry.Insets;

public class Controller {


    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;

    @FXML
    private ImageView fb1;

    private Main main;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Button reset;

    @FXML
    private Button login;

    @FXML
    void loginPressed2(KeyEvent event) {
        //loadMains();
//        if(event.getCode().equals(KeyCode.ENTER))
//        {
//            String userInput = userName.getText();
//            String passwordInput = password.getText();
//
//            if(isValidClub(userInput) && passwordInput.equals("123"))
//            {
//                try {
//                    System.out.println("login success");
//                    main.showDBMS(userInput);
//
//                } catch (Exception e) {
//                    System.out.println(e);
//                    e.printStackTrace();
//                }
//            }
//            else
//            {
//                main.showNegativeAlert();
//            }
//        }
    }

    @FXML
    void loginPressed(ActionEvent event) throws IOException {
        //loadMains();
        String userInput = userName.getText();
        String passwordInput = password.getText();

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userInput.toLowerCase());
        loginDTO.setPassword(passwordInput);
        loginDTO.setOnline(false);

        try{
            main.getNetworkUtil().write(loginDTO);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

//        if(isValidClub(userInput) && passwordInput.equals("123"))
//        {
//            try {
//                System.out.println("login success");
//                main.showDBMS(userInput);
//
//            } catch (Exception e) {
//                System.out.println(e);
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            main.showNegativeAlert();
//        }

    }

    @FXML
    void resetPressed(ActionEvent event) {
        //loadMains();
        BackgroundFill fill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(fill);
        reset.setBackground(bg);

        userName.setText("");
        password.setText("");
    }
    public boolean isValidClub(String cName)
    {
        for(Club c : DatabaseSystem.clubList)
        {
            if(c.getName().equalsIgnoreCase(cName))
            {
                return true;
            }
        }
        return false;
    }

    public void loadMains()
    {
        setRotate(c1,true, 360,8);
        setRotate(c2,true, 150,5);
        setRotate(c3,true, 120,10);
        setRotate(fb1,true, 360*3,5);

    }
    private void setRotate(Circle c, boolean reverse, int angle, int duration)
    {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);

        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(RotateTransition.INDEFINITE);
        rt.play();
    }

    private void setRotate(ImageView img, boolean reverse, int angle, int duration)
    {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),img);
        rt.setAutoReverse(true);

        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        //rt.setRate(3);
        rt.setCycleCount(18);
        rt.play();

        TranslateTransition tt = new TranslateTransition();
        tt.setNode(fb1);
        tt.setCycleCount(TranslateTransition.INDEFINITE);
        tt.setByX(1160);
        tt.setAutoReverse(true);
        tt.setDuration(Duration.seconds(duration));
        tt.setByY(0);
        tt.play();

    }


    public void setMain(Main m) {
        this.main = m;
    }
}
