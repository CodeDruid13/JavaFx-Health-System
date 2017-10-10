package DoctorData;

/**
 * Created by Obare on 3/4/2017.
 */

import Animations.FadeInLeftTransition;
import DataBase.DBController;
import Mail.Mail;
import MainClass.Obare;
import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Animations.FadeInRightTransition;

import javafx.scene.control.Label;
import java.sql.*;


public class DoctorController implements Initializable {

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtTag;

    @FXML
    private JFXButton btnRegNewDoc;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField itxtdNo;

    @FXML
    private JFXComboBox SelectDocGender;

    @FXML
    private JFXTextField txtpNumber;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXButton btnSaveDocData;

    @FXML
    private JFXButton btnDocData;

    @FXML
    private Pane paneAnimation;

    @FXML
    private JFXButton btnGenerateTag;

    @FXML
    private JFXTextArea txtQualifications;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;

    Connection connection = DBController.Connect();

    @FXML
    private void ClearFields(ActionEvent actionEvent) {

        txtTag.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        itxtdNo.clear();
        txtpNumber.clear();
        txtemail.clear();
        txtQualifications.clear();
        SelectDocGender.getItems().clear();

    }


    @FXML
    private void getDocData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/AvailableDoctors.fxml"));
        javafx.scene.image.Image icon = new javafx.scene.image.Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 1268, 500);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Available Doctors");
        nStage.setResizable(false);
        nStage.initStyle(StageStyle.UTILITY);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnDocData.getScene().getWindow();
        one.close();

    }

    @FXML
    private void saveToDoctorTable() throws Exception {

        String PTag = txtTag.getText();
        String FName = txtFirstName.getText();
        String LName = txtLastName.getText();
        String Address = txtAddress.getText();
        String IDNumber = itxtdNo.getText();
        String PhoneNumber = txtpNumber.getText();
        String Email = txtemail.getText();
        Object Gender = SelectDocGender.getValue();
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

            String query = "insert into doctors (DocTag, DocFName, DocLName, DocAddress, DocIDNumber, " +
                    "DocGender, DocPhoneNumber, DocEmail, DocQualifications) values (?,?,?,?,?,?,?,?,?)";

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


            txtTag.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtAddress.clear();
            itxtdNo.clear();
            txtpNumber.clear();
            txtemail.clear();
            txtQualifications.clear();
            SelectDocGender.getItems().clear();

        }


        /*String Username2 = " Your 3CARE Registration Has occured!";
        String useremail = " Please use the Confirmation code $P$Bdm/ToR/ab4.RevJEg5v8syByiPY8c0 to confirm your account" +
                " validity in the 3CARE Application.For any Queries please contact the Administrator : obaregeoffrey78@gmail.com";

        Task sendingMessage = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Mail mail = new Mail();

                mail.sendMail("obygingi13@gmail.com","mimi8827","dagdreamer@gmail.com",Username2,useremail);

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
                txtpNumber.getText().isEmpty() | txtQualifications.getText().isEmpty() | SelectDocGender.getSelectionModel().isEmpty()
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
    private void GenerateDocTag(ActionEvent event) throws SQLException {

        txtTag.setText( String.valueOf(doctorTag()));

    }


    private Integer doctorTag() throws SQLException {

        Connection connection = DBController.Connect();

        String query = "\n" +
                "select DocTag from doctors";
        resultSet = connection.createStatement().executeQuery(query);
        resultSet.last();
        int DOC_TAG = resultSet.getInt(1) ;
        return DOC_TAG + 1 ;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> DocGender = FXCollections.observableArrayList("Male", "Female");
        SelectDocGender.setItems(DocGender);
        SelectDocGender.getSelectionModel();

        Platform.runLater(() -> {
            new FadeInRightTransition(txtTag).play();
            new FadeInRightTransition(txtFirstName).play();
            new FadeInLeftTransition(txtLastName).play();
            new FadeInLeftTransition(txtAddress).play();
            new FadeInRightTransition(itxtdNo).play();
            new FadeInLeftTransition(SelectDocGender).play();
            new FadeInLeftTransition(txtpNumber).play();
            new FadeInRightTransition(txtemail).play();
            new FadeInRightTransition(txtQualifications).play();

        });
    }
}
