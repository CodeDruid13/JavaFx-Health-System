/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlPanelController;

import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

/**
 *
 * @author Obare
 */
public class ControlPanelController implements Initializable {
    
     @FXML
    private AnchorPane anchorpane;
     
    @FXML
    JFXButton btnLogout;
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private Pane backgroundPane;
    
    @FXML
    private JFXButton gotoDocPanel;
    
    @FXML
    private JFXButton gotoNursePanel;
    
    @FXML
    private JFXButton gotoWards;

    @FXML
    private JFXButton gotoPatients;

    @FXML
    private JFXButton btnUsers;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton gotoServicesPanel;

    @FXML
    private JFXButton gotoStatsPanel;

    @FXML
    private JFXButton gotoAdmissionsPanel;

    @FXML
    private ImageView imgMasterEntry;

    @FXML
    private ImageView imgUsers;

    @FXML
    private ImageView imgSettings;

    @FXML
    private ImageView imgDoctors;

    @FXML
    private ImageView imgNurses;

    @FXML
    private ImageView imgPatients;

    @FXML
    private ImageView imgServices;

    @FXML
    private ImageView imgStats;

    @FXML
    private ImageView imgAdmissions;




    @FXML
    private void regWards(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/WardEntry.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Master Entry.");
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.initStyle(StageStyle.UTILITY);
        //nStage.initModality(Modality.WINDOW_MODAL);
        //nStage.initOwner(nStage);
        nStage.show();
        
    }
    
    
    @FXML
    private void gotoDocPanelReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/RegisterDoctor.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Doctor Panel.");
        nStage.initStyle(StageStyle.UTILITY);
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();
        
    }
    
    @FXML
    private void gotoNursePanelReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/RegisterNurse.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("NURSE REGISTRATION.");
        nStage.initStyle(StageStyle.UTILITY);
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();
    }

    @FXML
    private void taketoServices(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("/Resources/ServiceChoices.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 624, 181);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("PATIENT SERVICES");
        nStage.setResizable(false);
        nStage.initStyle(StageStyle.UTILITY);
        nStage.getIcons().add(icon);
        nStage.show();

    }


    @FXML
    private void gotoPatientRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/RegisterPatient.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Patient Registration.");
        nStage.initStyle(StageStyle.UTILITY);
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();

    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try
        {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Internet Connected");

        }catch (Exception e){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("IMPORTANT!");
            alert.setHeaderText("No Internet Connection!");
            alert.setContentText("Please check for Internet Connection during Registration!");
            alert.showAndWait();

        }

        try {


            gotoWards.setOnMouseExited(e -> {
                gotoWards.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoWards.setOnMouseEntered(e -> {
                gotoWards.setStyle("-fx-background-color: #ef9a9a ");
            });

            btnUsers.setOnMouseExited(e -> {
                btnUsers.setStyle("-fx-background-color:  #01e5a7; ");
            });

            btnUsers.setOnMouseEntered(e -> {
                btnUsers.setStyle("-fx-background-color: #ef9a9a ");
            });

            btnSettings.setOnMouseExited(e -> {
                btnSettings.setStyle("-fx-background-color:  #01e5a7; ");
            });

            btnSettings.setOnMouseEntered(e -> {
                btnSettings.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoDocPanel.setOnMouseExited(e -> {
                gotoDocPanel.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoDocPanel.setOnMouseEntered(e -> {
                gotoDocPanel.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoNursePanel.setOnMouseExited(e -> {
                gotoNursePanel.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoNursePanel.setOnMouseEntered(e -> {
                gotoNursePanel.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoPatients.setOnMouseExited(e -> {
                gotoPatients.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoPatients.setOnMouseEntered(e -> {
                gotoPatients.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoServicesPanel.setOnMouseExited(e -> {
                gotoServicesPanel.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoServicesPanel.setOnMouseEntered(e -> {
                gotoServicesPanel.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoStatsPanel.setOnMouseExited(e -> {
                gotoStatsPanel.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoStatsPanel.setOnMouseEntered(e -> {
                gotoStatsPanel.setStyle("-fx-background-color: #ef9a9a ");
            });

            gotoAdmissionsPanel.setOnMouseExited(e -> {
                gotoAdmissionsPanel.setStyle("-fx-background-color:  #01e5a7; ");
            });

            gotoAdmissionsPanel.setOnMouseEntered(e -> {
                gotoAdmissionsPanel.setStyle("-fx-background-color: #ef9a9a ");
            });


               imgMasterEntry.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgMasterEntry);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

                imgUsers.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgUsers);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

                imgSettings.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgSettings);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgDoctors.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgDoctors);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgNurses.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgNurses);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgPatients.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgPatients);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgServices.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgServices);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgStats.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgStats);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            imgAdmissions.setOnMouseEntered(e ->{
                RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), imgAdmissions);
                rotateTransition.setFromAngle(0);
                rotateTransition.setToAngle(360);
                rotateTransition.play();
            });

            FadeTransition fadeout = new FadeTransition(Duration.seconds(2.0), backgroundPane);
            fadeout.setFromValue(1);
            fadeout.setToValue(0);
            fadeout.play();

            fadeout.setOnFinished(event -> {

                backgroundPane.setStyle("-fx-background-image: url(\"/resources/Images/image-1.jpg\");");

                FadeTransition fadein = new FadeTransition(Duration.seconds(2.0), backgroundPane);
                fadein.setFromValue(0);
                fadein.setToValue(0.6);
                fadein.play();


            });
            
            VBox box = FXMLLoader.load(getClass().getResource("/Resources/SideBar.fxml"));
            drawer.setSidePane(box);
            
             HamburgerNextArrowBasicTransition burger2 = new HamburgerNextArrowBasicTransition(hamburger);
            burger2.setRate (-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burger2.setRate(burger2.getRate() * -1);
            burger2.play();
            
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        } catch (IOException ex) {
            Logger.getLogger(ControlPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }        

               
    }
}
