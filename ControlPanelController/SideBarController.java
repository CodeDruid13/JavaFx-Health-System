package ControlPanelController;

import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * Created by Obare on 3/4/2017.
 */
public class SideBarController implements Initializable {

    @FXML
    private VBox sideBar;

    @FXML
    private JFXButton showAbout;

    @FXML
    private ImageView imgAbout;

    @FXML
    private JFXButton gotoLogin;

    @FXML
    private ImageView imgLogout;


    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/Login.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 627, 473);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Login Panel.");
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) gotoLogin.getScene().getWindow();
        one.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

            imgAbout.setOnMouseEntered(e ->{
            RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgAbout);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
            rotateTransition.play();
        });

            imgLogout.setOnMouseEntered(e -> {
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgLogout);
                rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.play();
        });

        gotoLogin.setOnMouseExited(e -> {
            gotoLogin.setStyle("-fx-background-color:   #fff; ");
        });

        gotoLogin.setOnMouseEntered(e -> {
            gotoLogin.setStyle("-fx-background-color: #ef9a9a ");
        });

        showAbout.setOnMouseExited(e -> {
            showAbout.setStyle("-fx-background-color:   #fff; ");
        });

        showAbout.setOnMouseEntered(e -> {
            showAbout.setStyle("-fx-background-color: #ef9a9a ");
        });
    }
}
