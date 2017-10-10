/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplashScreen;

/**
 *
 * @author Obare
 */
import MainClass.Obare;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

        //Class occurs after Splash Screen

public class SplashFXMLController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);//Time taken for splash screen to show

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/Resources/Login.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
                        Stage obare = new Stage();
                        obare.setTitle("Login");
                        obare.setScene(new Scene(root, 627, 473));
                        obare.setResizable(false);
                        obare.getIcons().add(icon);
                        obare.show();

                        rootPane.getScene().getWindow().hide();

                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
