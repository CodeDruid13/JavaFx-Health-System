package Services;

import DataBase.DBController;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Obare on 3/28/2017.
 */
public class PatientServicesController implements Initializable {

    @FXML
    private JFXTextField txtServiceName;

    @FXML
    private JFXTextField txtPatientID;

    @FXML
    private JFXTextField txtPatientName;

    @FXML
    private JFXDatePicker txtServiceDate;

    @FXML
    private TableView<PatientServicesDetails> tableWard;

    @FXML
    private TableColumn<PatientServicesDetails, String> columnPatientID;

    @FXML
    private TableColumn<PatientServicesDetails, String> columnPatientFirstName;

    @FXML
    private TableColumn<PatientServicesDetails, String>columnPatienLastName;

    @FXML
    private JFXTextField filterField;

    @FXML
    private TableView<PatientServicesDetails> tablePatients;

    DBController dc;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<PatientServicesDetails> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dc = new DBController();

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT PatientID,PatientFName,PatientLName FROM patientregistration");
            while (rs.next()) {

                data.add(new PatientServicesDetails(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnPatientID.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        columnPatientFirstName.setCellValueFactory(new PropertyValueFactory<>("PatientFName"));
        columnPatienLastName.setCellValueFactory(new PropertyValueFactory<>("PatientLName"));

        tablePatients.setItems(null);
        tablePatients.setItems(data);
    }
}
