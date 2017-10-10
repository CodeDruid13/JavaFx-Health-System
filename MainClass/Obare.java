/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClass;

/**
 *
 * @author Obare
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.net.URLConnection;

//This class calls the splash screen
public class Obare extends Application {


    @Override
    public void start(Stage obare) throws Exception {
        Parent rroot = FXMLLoader.load(getClass().getResource("/Resources/A.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        obare.setTitle("Login");
        obare.setScene(new Scene(rroot, 590, 280));
        obare.initStyle(StageStyle.UNDECORATED);
        obare.setResizable(false);
        obare.getIcons().add(icon);
        obare.show();


    }
    
    public static void main(String [] args){

        launch(args);
    }
}
