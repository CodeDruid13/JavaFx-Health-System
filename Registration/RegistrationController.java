package Registration;

/**
 *
 * @author Obare
 */

import Animations.FadeInLeftTransition;
import DataBase.DBController;
import MainClass.Obare;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Animations.FadeInRightTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import Mail.Mail;
import javafx.concurrent.Task;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.Properties;


public class RegistrationController implements Initializable {

    @FXML
    private Pane paneAnimation;

    @FXML
    private JFXTextField txtPID;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtKinName;

    @FXML
    private JFXTextField txtdNo;

    @FXML
    private JFXTextField txtpNumber;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXComboBox SelectPatientGender;

    @FXML
    private JFXComboBox SelectPatBloodGroup;

    @FXML
    private JFXTextArea txtRemarks;

    @FXML
    private JFXButton btnRegNewDoc;

    @FXML
    private JFXButton btnSavePatientData;

    @FXML
    private JFXButton btnPatData;

    @FXML
    private JFXButton GenPID;




    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
   

    Connection connection = DBController.Connect();

    /*ObservableList<String> PatGender = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> BGroup = FXCollections.observableArrayList("O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-");

    @FXML
    private void initialize() {

        SelectPatientGender.setItems(PatGender);
        SelectPatBloodGroup.setItems(BGroup);
    }*/


    @FXML
    private void fetchPatData(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/AvailablePatients.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 1293, 500);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Patient's Data");
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnPatData.getScene().getWindow();
        one.close();

    }


    @FXML
    private void SaveData() throws Exception {

        String PID = txtPID.getText();
        String FName = txtFirstName.getText();
        String LName = txtLastName.getText();
        String KinName = txtKinName.getText();
        String IDNumber = txtdNo.getText();
        String PhoneNumber = txtpNumber.getText();
        String Email = txtemail.getText();
        Object Gender = SelectPatientGender.getValue();
        Object BloodGroup = SelectPatBloodGroup.getValue();
        String Remarks = txtRemarks.getText();


        if (!validateFields()) {
            
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Registration Error");
            tray.setMessage("Please ensure all fields are not blank!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;
            
        }

        if(validateGender()) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Gender Selection");
            tray.setMessage("Please select Gender!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
        }

        if(validateBloodGroup()) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Gender Selection");
            tray.setMessage("Please select Blood Group!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
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

        if(!validateKinNumber()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Phone Number");
            tray.setMessage("Please use a valid Phone Number!");
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

           String query = "insert into patientregistration (PatientID, PatientFName, PatientLName, NextOfKin, IDNumber, Gender, ContactNo, Email, BloodGroup, Remarks) values (?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = null;

            try {

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, PID);
                preparedStatement.setString(2, FName);
                preparedStatement.setString(3, LName);
                preparedStatement.setString(4, KinName);
                preparedStatement.setString(5, IDNumber);
                preparedStatement.setObject(6, Gender);
                preparedStatement.setString(7, PhoneNumber);
                preparedStatement.setString(8, Email);
                preparedStatement.setObject(9, BloodGroup);
                preparedStatement.setString(10, Remarks);
                
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

            txtPID.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtdNo.clear();
            txtpNumber.clear();
            txtemail.clear();
            txtRemarks.clear();

        }
        
       
        String Username2 = " From Registration. New Registration of " + txtFirstName + txtLastName +  " Has occured!";
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
        

    }


    
    private boolean validateFields(){
        boolean pass=true;
        if (txtPID.getText().isEmpty() | txtFirstName.getText().isEmpty() | txtLastName.getText().isEmpty() |
                txtKinName.getText().isEmpty() | txtdNo.getText().isEmpty() | txtemail.getText().isEmpty() |
                txtpNumber.getText().isEmpty() | txtRemarks.getText().isEmpty())
        {

            pass=false;
        }

        return pass;
    }

    private boolean validateBloodGroup(){

        if ( SelectPatBloodGroup.getSelectionModel().isEmpty() ){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateGender(){

        if (SelectPatientGender.getSelectionModel().isEmpty()){
            return true;
        }
        else {
            return false;
        }
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

    private boolean validateKinNumber(){

        Pattern pattern = Pattern.compile("[0-9]+.{6,7}");
        Matcher matcher = pattern.matcher(txtKinName.getText());
        if (matcher.find() && matcher.group().equals(txtKinName.getText())){
            return true;
        }
        else {

            return false;
        }
    }

      private boolean validateIDNumber(){

       Pattern pattern = Pattern.compile("[0-9]+.{6,7}");
        Matcher matcher = pattern.matcher(txtdNo.getText());
        if (matcher.find() && matcher.group().equals(txtdNo.getText())){
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
    void AddPID(ActionEvent event) throws SQLException{

        txtPID.setText(String.valueOf(GeneratePatientID()));

    }

    private Integer GeneratePatientID() throws SQLException {

        Connection connection = DBController.Connect();

        String query = "\n" +
                "select PatientID from patientregistration";
        rs = connection.createStatement().executeQuery(query);
        rs.last();
        int Patient_ID = rs.getInt(1) ;
        return Patient_ID + 1;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> PatGender = FXCollections.observableArrayList("Male", "Female");
        ObservableList<String> BGroup = FXCollections.observableArrayList("A+", "A-", "AB-","B+", "B-", "AB+","O+", "O-" );

        SelectPatientGender.setItems(PatGender);
        SelectPatBloodGroup.setItems(BGroup);

          Platform.runLater(() -> {

                new FadeInRightTransition(txtFirstName).play();
                new FadeInLeftTransition(txtLastName).play();
                new FadeInRightTransition(txtdNo).play();
                new FadeInLeftTransition(txtpNumber).play();
                new FadeInRightTransition(txtemail).play();
                new FadeInLeftTransition(txtRemarks).play();
                new FadeInRightTransition(btnSavePatientData).play();


        });
    }
}

  
   