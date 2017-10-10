package Inventory;

import Animations.FadeInLeftTransition;
import Animations.FadeInRightTransition;
import DataBase.DBController;
import MainClass.Obare;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Obare on 3/21/2017.
 */
public class SupplierController implements Initializable {

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSupplierID;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXButton btnSaveSupplier;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSupplierID;

    @FXML
    private JFXButton btnGetAllSuppliers;

    @FXML
    private JFXButton btnHelp;

    @FXML
    private TableView <SupplierDataDetails>tableSuppliers;

    @FXML
    private TableColumn <SupplierDataDetails, Integer> columnSUPPLIER_ID;

    @FXML
    private TableColumn <SupplierDataDetails, String> columnSUPPLIER_FNAME;

    @FXML
    private TableColumn <SupplierDataDetails, String> columnSUPPLIER_LNAME;

    @FXML
    private TableColumn <SupplierDataDetails, String> columnSUPPLIER_PHONE_NUMBER;

    @FXML
    private TableColumn <SupplierDataDetails, String> columnSUPPLIER_EMAIL;

    @FXML
    private TableColumn <SupplierDataDetails, String> columnSUPPLIER_ADDRESS;


    private ObservableList<SupplierDataDetails> data;
    private DBController dc;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    Connection connection = DBController.Connect();

    @FXML
    void ClearSuppliers(ActionEvent event) {

        txtSupplierID.clear();
        txtPhoneNumber.clear();
        txtFName.clear();
        txtLName.clear();
        txtEmail.clear();
        txtAddress.clear();

    }

    @FXML
    void gotoHelp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Resources/SupplierInfoHelp.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Information Help.");
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();

    }

    @FXML
    void FetchSuppliers(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM suppliers");
            while (rs.next()) {

                data.add(new SupplierDataDetails(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnSUPPLIER_ID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        columnSUPPLIER_FNAME.setCellValueFactory(new PropertyValueFactory<>("SupplierFName"));
        columnSUPPLIER_LNAME.setCellValueFactory(new PropertyValueFactory<>("SupplierLName"));
        columnSUPPLIER_PHONE_NUMBER.setCellValueFactory(new PropertyValueFactory<>("SupplierPhoneNumber"));
        columnSUPPLIER_EMAIL.setCellValueFactory(new PropertyValueFactory<>("SupplierEmail"));
        columnSUPPLIER_ADDRESS.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));

        tableSuppliers.setItems(null);
        tableSuppliers.setItems(data);

    }

    @FXML
    void SaveSupplier(ActionEvent event) throws Exception {
        String SuppID = txtSupplierID.getText();
        String SuppPhoneNumber = txtPhoneNumber.getText();
        String SuppFName = txtFName.getText();
        String SuppLName = txtLName.getText();
        String SuppEmail = txtEmail.getText();
        String SuppAddress = txtAddress.getText();

        if (!validateFields()) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Registration Error");
            tray.setMessage("Please ensure all fields are not blank!");
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

        if(!validateFirstName()){
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

                String query = "insert into suppliers (SupplierID, SupplierPhoneNumber, SupplierFName, SupplierLName, " +
                        " SupplierEmail, SupplierAddress) values (?,?,?,?,?,?)";

                preparedStatement = null;

                try {

                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1, SuppID);
                    preparedStatement.setString(2, SuppPhoneNumber);
                    preparedStatement.setString(3, SuppFName);
                    preparedStatement.setString(4, SuppLName);
                    preparedStatement.setString(5, SuppEmail);
                    preparedStatement.setString(6, SuppAddress);

                } catch (SQLException e) {
                    System.out.println(e);
                } finally {
                    preparedStatement.execute();
                    preparedStatement.close();
                }

                TrayNotification tray = new TrayNotification();
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.setTitle("Registration successful");
                tray.setMessage(SuppFName + SuppLName + "  Has been succesfully registered!");
                tray.setAnimationType(AnimationType.SLIDE);
                tray.showAndDismiss(Duration.millis(1500));


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
        if (txtSupplierID.getText().isEmpty() | txtPhoneNumber.getText().isEmpty() | txtFName.getText().isEmpty() |
                txtLName.getText().isEmpty() | txtEmail.getText().isEmpty() | txtAddress.getText().isEmpty()

                )
        {

            pass=false;
        }

        return pass;
    }

    private boolean validatePhoneNumber(){

        Pattern pattern = Pattern.compile("[0-9]+.{9,9}");
        Matcher matcher = pattern.matcher(txtPhoneNumber.getText());
        if (matcher.find() && matcher.group().equals(txtPhoneNumber.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateFirstName(){

        Pattern pattern = Pattern.compile("[a-zA-Z]+.{1}");
        Matcher matcher = pattern.matcher(txtFName.getText());
        if (matcher.find() && matcher.group().equals(txtFName.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateLastName(){

        Pattern pattern = Pattern.compile("[a-zA-Z]+.{1}");
        Matcher matcher = pattern.matcher(txtLName.getText());
        if (matcher.find() && matcher.group().equals(txtLName.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateEmail(){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(txtEmail.getText());
        if (matcher.find() && matcher.group().equals(txtEmail.getText())){
            return true;
        }
        else {

            return false;
        }
    }
    @FXML
    void createSupplierID(ActionEvent event) throws SQLException{

        txtSupplierID.setText( String.valueOf(SupplierID()));
    }

    private Integer SupplierID() throws SQLException {

        Connection connection = DBController.Connect();

        String query = "\n" +
                "select SupplierID from suppliers";
        resultSet = connection.createStatement().executeQuery(query);
        resultSet.last();
        int SUPP_ID = resultSet.getInt(1) ;
        return SUPP_ID + 1 ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dc = new DBController();

        Platform.runLater(() -> {
            new FadeInRightTransition(txtSupplierID).play();
            new FadeInLeftTransition(txtPhoneNumber).play();
            new FadeInRightTransition(txtFName).play();
            new FadeInLeftTransition(txtAddress).play();
            new FadeInRightTransition(txtLName).play();
            new FadeInLeftTransition(txtEmail).play();

        });


        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM suppliers");
            while (rs.next()) {

                data.add(new SupplierDataDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                       ));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnSUPPLIER_ID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        columnSUPPLIER_FNAME.setCellValueFactory(new PropertyValueFactory<>("SupplierFName"));
        columnSUPPLIER_LNAME.setCellValueFactory(new PropertyValueFactory<>("SupplierLName"));
        columnSUPPLIER_PHONE_NUMBER.setCellValueFactory(new PropertyValueFactory<>("SupplierPhoneNumber"));
        columnSUPPLIER_EMAIL.setCellValueFactory(new PropertyValueFactory<>("SupplierEmail"));
        columnSUPPLIER_ADDRESS.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));

        tableSuppliers.setItems(null);
        tableSuppliers.setItems(data);


            }
        }



