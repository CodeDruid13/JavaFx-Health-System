package NurseData;

/**
 * Created by Obare on 3/4/2017.
 */

import Animations.FadeInLeftTransition;
import Animations.FadeInRightTransition;
import DataBase.DBController;
import Mail.Mail;
import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sun.java2d.d3d.D3DVolatileSurfaceManager;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NurseController implements Initializable{

    @FXML
    private Pane paneAnimation;

    @FXML
    private JFXTextField txtTag;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField itxtdNo;

    @FXML
    private JFXTextField txtpNumber;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXComboBox SelectNurseGender;

    @FXML
    private JFXTextArea txtQualifications;

    @FXML
    private JFXButton btnRegNewNurse;

    @FXML
    private JFXButton btnSaveNurseData;

    @FXML
    private JFXButton btnNurseData;

    @FXML
    private JFXButton btnGenerateTag;


    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    Connection connection = DBController.Connect();


    @FXML
    void clearNurseFields(ActionEvent event) {

        txtTag.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        itxtdNo.clear();
        SelectNurseGender.getItems().clear();
        txtpNumber.clear();
        txtemail.clear();
        txtQualifications.clear();
    }

    @FXML
    private void getNurseData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/AvailableNurses.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 1268, 500);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Nurse's Data");
        nStage.setResizable(false);
        nStage.initStyle(StageStyle.UTILITY);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnNurseData.getScene().getWindow();
        one.close();

    }

    @FXML
    private void saveToNurseTable() throws Exception {

        String PTag = txtTag.getText();
        String FName = txtFirstName.getText();
        String LName = txtLastName.getText();
        String Address = txtAddress.getText();
        String IDNumber = itxtdNo.getText();
        String PhoneNumber = txtpNumber.getText();
        String Email = txtemail.getText();
        Object Gender = SelectNurseGender.getValue();
        String Qualifications = txtQualifications.getText();


        if (!validateFields()) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Registration Error");
            tray.setMessage("Please ensure all fields are not blank!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }


        if(!validateFullName()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("First Name");
            tray.setMessage("Please use a valid name!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        if(!validateLastName()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Last Name");
            tray.setMessage("Please use a valid name!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }


        if(!validateIDNumber()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("ID Number");
            tray.setMessage("Please use a valid ID Number!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        if(!validatePhoneNumber()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Phone Number");
            tray.setMessage("Please use a valid Phone Number!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        if(!validateEmail()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Wrong Email Format");
            tray.setMessage("Please use a valid Email!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;
        }


        else{

            String query = "insert into nurses (NurseTag, NurseFName, NurseLName, NurseAddress, NurseIDNumber, " +
                    "NurseGender, NursePhoneNumber, NurseEmail, NurseQualifications) values (?,?,?,?,?,?,?,?,?)";

            preparedStatement = null;

            try {

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, PTag);
                preparedStatement.setString(2, FName);
                preparedStatement.setString(3, LName);
                preparedStatement.setString(4, Address);
                preparedStatement.setString(5, IDNumber);
                preparedStatement.setObject(6, Gender);
                preparedStatement.setString(7, PhoneNumber);
                preparedStatement.setString(8, Email);
                preparedStatement.setString(9, Qualifications);

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                preparedStatement.execute();
                preparedStatement.close();
            }

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.setTitle("Registration successful");
            tray.setMessage(FName + LName + "  Has been succesfully registered!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));


        }


       /* String Username2 = " From Registration. New Registration of " + txtFirstName + txtLastName +  " Has occured!";
        String useremail = "The email is " + Email;

        Task sendingMessage = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Mail mail = new Mail();

                mail.sendMail("obygingi13@gmail.com","mimi8827","obaregeoffrey78@gmail.com",Username2,useremail);

                return null;
            }
        } ;


        new Thread(sendingMessage).start();

         */
    }



    private boolean validateFields(){
        boolean pass=true;
        if (txtTag.getText().isEmpty() | txtFirstName.getText().isEmpty() | txtLastName.getText().isEmpty() |
                txtAddress.getText().isEmpty() | itxtdNo.getText().isEmpty() | txtemail.getText().isEmpty() |
                txtpNumber.getText().isEmpty() | txtQualifications.getText().isEmpty() | SelectNurseGender.getSelectionModel().isEmpty()
                )
        {

            pass=false;
        }

        return pass;
    }


    private boolean validateFullName(){

        Pattern pattern = Pattern.compile("[a-zA-Z]+.{1}");
        Matcher matcher = pattern.matcher(txtFirstName.getText());
        if (matcher.find() && matcher.group().equals(txtFirstName.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateLastName(){

        Pattern pattern = Pattern.compile("[a-zA-Z]+.{1}");
        Matcher matcher = pattern.matcher(txtLastName.getText());
        if (matcher.find() && matcher.group().equals(txtLastName.getText())){
            return true;
        }
        else {

            return false;
        }
    }


    private boolean validateIDNumber(){

        Pattern pattern = Pattern.compile("[0-9]+.{6,7}");
        Matcher matcher = pattern.matcher(itxtdNo.getText());
        if (matcher.find() && matcher.group().equals(itxtdNo.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validatePhoneNumber(){

        Pattern pattern = Pattern.compile("[0-9]+.{9,9}");
        Matcher matcher = pattern.matcher(txtpNumber.getText());
        if (matcher.find() && matcher.group().equals(txtpNumber.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateEmail(){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(txtemail.getText());
        if (matcher.find() && matcher.group().equals(txtemail.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    @FXML
    void createTag(ActionEvent event) throws SQLException{

        txtTag.setText(String.valueOf(nursetag()));
    }

    private Integer nursetag() throws SQLException{

        Connection connection = DBController.Connect();

        String query = "select nursetag from nurses;";
        resultSet = connection.createStatement().executeQuery(query);
        resultSet.last();
        int NURSE_TAG = resultSet.getInt(1) ;
        return NURSE_TAG + 1 ;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> NurseGender = FXCollections.observableArrayList("Male", "Female");
        SelectNurseGender.setItems(NurseGender);

        Platform.runLater(() -> {
            new FadeInRightTransition(txtTag).play();
            new FadeInRightTransition(txtFirstName).play();
            new FadeInLeftTransition(txtLastName).play();
            new FadeInLeftTransition(txtAddress).play();
            new FadeInRightTransition(itxtdNo).play();
            new FadeInLeftTransition(SelectNurseGender).play();
            new FadeInLeftTransition(txtpNumber).play();
            new FadeInRightTransition(txtemail).play();
            new FadeInRightTransition(txtQualifications).play();

        });
    }
}
