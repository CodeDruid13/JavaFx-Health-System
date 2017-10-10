package Login;

/**
 * Created by Obare on 3/31/2017.
 */

import DataBase.DBController;
import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.sql.*;

public class ResetPassword {

    @FXML
    private JFXButton btnCancelReset;

    @FXML
    private JFXPasswordField txtUsername;

    @FXML
    private JFXTextField txtPhonenumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnResetPass;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    Connection connection = DBController.Connect();

    @FXML
    void CancelResetAction(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("/Resources/Login.fxml"));
        Image image = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(parent, 627, 473);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("3CARE SYSTEM");
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();

        Stage two = (Stage)btnCancelReset.getScene().getWindow();
        two.close();

    }

    @FXML
    void checkUserData(ActionEvent event) throws Exception {

        String username = txtUsername.getText();
        String Phone_number = txtPhonenumber.getText();
        String Email = txtEmail.getText();

        if (!validateFields()) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Reset Error!");
            tray.setMessage("Please ensure all fields are not blank!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));

            return;



        } else {

            String query = "select username,phonenumber,email from users;";

            preparedStatement = null;

            try {

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(3, username);
                preparedStatement.setString(5, Phone_number);
                preparedStatement.setString(6, Email);

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                preparedStatement.execute();
                preparedStatement.close();
            }

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.setTitle("Action Successful");
            tray.setMessage("Please Check Your Email for further Instructions!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));

            txtEmail.clear();
            txtPhonenumber.clear();
            txtPhonenumber.clear();
        }
    }

    private boolean validateFields(){

        if(txtUsername.getText().isEmpty() | txtPhonenumber.getText().isEmpty() | txtEmail.getText().isEmpty())
        {


            return false;
        }
        return true;

    }


}
