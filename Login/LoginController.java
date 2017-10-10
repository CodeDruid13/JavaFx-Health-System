/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author Obare
 */
import Animations.FadeInLeftTransition;
import Animations.FadeInRightTransition;
import DataBase.DBController;
import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.sql.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



public class LoginController implements Initializable {

    @FXML
    private JFXTextField authUsername;
    @FXML
    private JFXPasswordField authPassword;
    @FXML
    private JFXComboBox cbUser;
    @FXML
    private Hyperlink ForgotPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private GridPane root;
    @FXML
    private ImageView google;
    @FXML
    private ImageView googleplus;
    @FXML
    private ImageView linkedin;
    @FXML
    private ImageView youtube;
    @FXML
    private ImageView twitter;
    @FXML
    private ImageView facebook;
    
    /*private static final BooleanProperty Granted_Access = new SimpleBooleanProperty(false);
    private static final int Max_Attempts = 3;
    private final IntegerProperty Attempts = new SimpleIntegerProperty(0);*/


    @FXML
    void gotoResetPanel(ActionEvent event) throws IOException{

        Parent parent = FXMLLoader.load(getClass().getResource("/Resources/ForgotPassWord.fxml"));
        Image image = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(parent, 646, 449);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reset Password");
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();

        Stage two = (Stage)ForgotPassword.getScene().getWindow();
        two.close();

    }

    @FXML
    private void checkforUser(ActionEvent event) throws IOException {
    
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Resources/ControlPanel.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, 1050, 550);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        if (isValidCredentials()){
             TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.setTitle(" LOGIN SUCCESSFUL!");
            tray.setMessage("WELCOME TO 3CARE SYSTEM!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            
            app_stage.hide(); 
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }

        else
        {
            
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Login Error");
            tray.setMessage("Please try again!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            
            authUsername.clear();
            authPassword.clear();
            
        }

    }

    private boolean isValidCredentials() {
        boolean let_in = false;
      
        Connection c = null;
        Statement stmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/obare";
            String user = "root";
            String pwd = "kronos";
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url, user, pwd);
            c.setAutoCommit(false);

           stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM administrator WHERE username= " + "'" + authUsername.getText() + "'"
                + " AND Password = " + "'" + authPassword.getText() + "'" );


            while (rs.next()) {
                if (rs.getString("USERNAME") != null && rs.getString("PASSWORD") != null ) {
                    String username = rs.getString("USERNAME");

                    String password = rs.getString("PASSWORD");

                    let_in = true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
             return let_in;

    }


    
 @Override
    public void initialize(URL location, ResourceBundle resources) {
          Platform.runLater(() -> {
            
            new FadeInLeftTransition(authUsername).play();
            new FadeInLeftTransition(authPassword).play();
            /*new FadeInLeftTransition(cbUser).play();
            new FadeInLeftTransition(ForgotPassword).play();*/
            new FadeInLeftTransition(btnLogin).play();

        });
      
       google.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.google.com"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       googleplus.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.googleplus.com/obare geoffrey"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       linkedin.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.linkedin.com/obare geoffrey"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });

       youtube.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.youtube.com/"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       twitter.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.twitter.com/obare13"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       facebook.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/obare gginji"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
      
    }
}