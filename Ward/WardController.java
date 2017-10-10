/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ward;

/**
 *
 * @author Obare
 */


import Animations.FadeInLeftTransition;
import Animations.FadeInRightTransition;
import DataBase.DBController;
import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class WardController implements Initializable {

    @FXML
    private JFXButton btnSaveWard;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableView<WardDetails> tableWard;

    @FXML
    private TableColumn<WardDetails, String> columnWardname;

    @FXML
    private TableColumn<WardDetails, String> columnWardType;

    @FXML
    private TableColumn<WardDetails, String> columnNumberofBeds;

    @FXML
    private TableColumn<WardDetails, String> columnCharges;

    @FXML
    private JFXTextField filterField;

    @FXML
    private JFXTextField txtWardName;

    @FXML
    private JFXComboBox ChooseType;

    @FXML
    private JFXTextField txtWardNumberofBeds;

    @FXML
    private JFXTextField txtChargesperBed;

    @FXML
    private JFXButton createNew;

    @FXML
    private JFXButton LoadWardData;

    @FXML
    private JFXButton btnChoices;


    private ObservableList<WardDetails> data;
    DBController dc;
    PreparedStatement preparedStatement = null;
    Connection connection = null;


    @FXML
    void ClearAllFields(ActionEvent event) {

        txtWardName.clear();
        txtChargesperBed.clear();
        txtWardNumberofBeds.clear();
        ChooseType.getItems().clear();

    }

    @FXML
    void gotoInventoryChoices(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Resources/InventoryChoices.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 538, 141);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Inventory");
        nStage.setResizable(false);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnChoices.getScene().getWindow();
        one.close();
    }

    @FXML
    void FillTableWard(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM ward");
            while (rs.next()) {

                data.add(new WardDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnWardname.setCellValueFactory(new PropertyValueFactory<>("wardname"));
        columnWardType.setCellValueFactory(new PropertyValueFactory<>("wardtype"));
        columnNumberofBeds.setCellValueFactory(new PropertyValueFactory<>("beds"));
        columnCharges.setCellValueFactory(new PropertyValueFactory<>("charges"));

        tableWard.setItems(null);
        tableWard.setItems(data);

    }

    @FXML
    private void addWard(ActionEvent event) throws Exception{

        String Wname = txtWardName.getText();
        Object Wtype = ChooseType.getValue();
        String Bnumber = txtWardNumberofBeds.getText();
        String ChargesBed = txtChargesperBed.getText();

        if (!validateFields()) {
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Registration Error");
            tray.setMessage("Please ensure all fields are not blank!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        if(!validateWardName()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Ward Name");
            tray.setMessage("Please use a valid name!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }


        if(!validateWardNumber()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Number of Beds");
            tray.setMessage("Please use only Numbers!");
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        if(!validateWardCharges()){
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("Phone Number");
            tray.setMessage("Please use only Numbers!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
            return;

        }

        else {

            String query = "insert into ward (wardname, wardtype, beds, charges) values (?,?,?,?)";

            preparedStatement = null;

            try {

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, Wname);
                preparedStatement.setObject(2, Wtype);
                preparedStatement.setString(3, Bnumber);
                preparedStatement.setString(4, ChargesBed);

            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
            finally
            {

                preparedStatement.execute();
                preparedStatement.close();
            }

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.setTitle("Registration successful");
            tray.setMessage(Wname +  "  Has been succesfully registered!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));

        }

    }
    private boolean validateFields(){

        boolean pass=true;

        if (txtWardName.getText().isEmpty() | txtWardNumberofBeds.getText().isEmpty()
                | txtChargesperBed.getText().isEmpty() | ChooseType.getSelectionModel().isEmpty()
                )
        {

            pass=false;
        }

        return pass;
    }


    private boolean validateWardName(){

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(txtWardName.getText());
        if (matcher.find() && matcher.group().equals(txtWardName.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateWardNumber(){

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(txtWardNumberofBeds.getText());
        if (matcher.find() && matcher.group().equals(txtWardNumberofBeds.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    private boolean validateWardCharges(){

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(txtChargesperBed.getText());
        if (matcher.find() && matcher.group().equals(txtChargesperBed.getText())){
            return true;
        }
        else {

            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> WardTypeList = FXCollections.observableArrayList("General", "Special");
        ChooseType.setItems(WardTypeList);
        ChooseType.getSelectionModel();

        Platform.runLater(() -> {
            new FadeInRightTransition(txtChargesperBed).play();
            new FadeInRightTransition(txtWardName).play();
            new FadeInLeftTransition(txtWardNumberofBeds).play();

        });

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM ward");
            while (rs.next()) {

                data.add(new WardDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnWardname.setCellValueFactory(new PropertyValueFactory<>("wardname"));
        columnWardType.setCellValueFactory(new PropertyValueFactory<>("wardtype"));
        columnNumberofBeds.setCellValueFactory(new PropertyValueFactory<>("beds"));
        columnCharges.setCellValueFactory(new PropertyValueFactory<>("charges"));

        tableWard.setItems(null);
        tableWard.setItems(data);

    }

}
