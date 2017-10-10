package DoctorData;

/**
 * Created by Obare on 3/5/2017.
 */


import DataBase.DBController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import DoctorData.DoctorDetails;
import DoctorData.DoctorController;

import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DoctorDataController implements Initializable{

    @FXML
    private TableView<DoctorDetails> tableUser;

    @FXML
    private TableColumn<DoctorDetails, String> columnTag;

    @FXML
    private TableColumn<DoctorDetails, String> columnFName;

    @FXML
    private TableColumn<DoctorDetails, String> columnLName;

    @FXML
    private TableColumn<DoctorDetails, String> columnAddress;

    @FXML
    private TableColumn<DoctorDetails, String> columnIDNumber;

    @FXML
    private TableColumn<DoctorDetails, String> columnGender;

    @FXML
    private TableColumn<DoctorDetails, String> columnPhoneNumber;

    @FXML
    private TableColumn<DoctorDetails, String> columnEmail;

    @FXML
    private TableColumn<DoctorDetails, String> columnQualifications;

    @FXML
    private JFXButton loadData;

    @FXML
    private JFXButton btnExport;

    @FXML
    private JFXButton SearchDoc;

    @FXML
    private JFXTextField SearchField;

    private ObservableList<DoctorDetails> data = FXCollections.observableArrayList();
    DBController dc;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private void LoadDocData(ActionEvent actionEvent){

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Doctors");
            while (rs.next()) {

                data.add(new DoctorDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                        , rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnTag.setCellValueFactory(new PropertyValueFactory<>("DocTag"));
        columnFName.setCellValueFactory(new PropertyValueFactory<>("DocFName"));
        columnLName.setCellValueFactory(new PropertyValueFactory<>("DocLName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("DocAddress"));
        columnIDNumber.setCellValueFactory(new PropertyValueFactory<>("DocIDNumber"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("DocGender"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("DocPhoneNumber"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("DocEmail"));
        columnQualifications.setCellValueFactory(new PropertyValueFactory<>("DocQualifications"));

        tableUser.setItems(null);
        tableUser.setItems(data);

    }

    @FXML
    private void PrintDocReport(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("IMPORTANT!");
        alert.setHeaderText("Load all Doctors!");
        alert.setContentText("Please Refresh First Before Exporting!");
        alert.showAndWait();

        File file = chooser.showSaveDialog(btnExport.getScene().getWindow());
        if (file != null) {
            saveXLSFile(file);

        }
    }

    private void saveXLSFile(File file) {
        try {

            Connection connection = DBController.Connect();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            Row row1 = workSheet.createRow(0);
            row1.createCell(0).setCellValue(" DOCTOR'S TAG");
            row1.createCell(1).setCellValue("FIRST NAME");
            row1.createCell(2).setCellValue("LAST NAME");
            row1.createCell(3).setCellValue("ADDRESS");
            row1.createCell(4).setCellValue("ID NUMBER");
            row1.createCell(5).setCellValue("GENDER");
            row1.createCell(6).setCellValue("PHONE NUMBER");
            row1.createCell(7).setCellValue("EMAIL");
            row1.createCell(8).setCellValue("QUALIFICATIONS");
            Row row2;

            String query = "SELECT * FROM doctors ";
            resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()) {
                int a = resultSet.getRow();
                row2 = workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(resultSet.getString(1));
                row2.createCell(1).setCellValue(resultSet.getString(2));
                row2.createCell(2).setCellValue(resultSet.getString(3));
                row2.createCell(3).setCellValue(resultSet.getString(4));
                row2.createCell(4).setCellValue(resultSet.getString(5));
                row2.createCell(5).setCellValue(resultSet.getString(6));
                row2.createCell(6).setCellValue(resultSet.getString(7));
                row2.createCell(7).setCellValue(resultSet.getString(8));
                row2.createCell(8).setCellValue(resultSet.getString(9));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            resultSet.close();

            connection.close();

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.setTitle(" SUCCESS");
            tray.setMessage("Data Successfully exported!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));

        } catch (SQLException | IOException e) {

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle("WARNING");
            tray.setMessage("Could not generate specified file!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dc = new DBController();

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Doctors");
            while (rs.next()) {

                data.add(new DoctorDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                        , rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnTag.setCellValueFactory(new PropertyValueFactory<>("DocTag"));
        columnFName.setCellValueFactory(new PropertyValueFactory<>("DocFName"));
        columnLName.setCellValueFactory(new PropertyValueFactory<>("DocLName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("DocAddress"));
        columnIDNumber.setCellValueFactory(new PropertyValueFactory<>("DocIDNumber"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("DocGender"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("DocPhoneNumber"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("DocEmail"));
        columnQualifications.setCellValueFactory(new PropertyValueFactory<>("DocQualifications"));

        tableUser.setItems(null);
        tableUser.setItems(data);

        /*// Initialize the columns.
        columnFName.setCellValueFactory(cellData -> cellData.getValue().DocFNameproperty());
        columnLName.setCellValueFactory(cellData -> cellData.getValue().DocLNameproperty());
        //  Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<DoctorDetails> filteredData = new FilteredList<>(data, p -> true);
        // Set the filter Predicate whenever the filter changes.
        SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person ->
        {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty())
            {
                return true;
            }
            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();
            if (person.getDocFName().toLowerCase().contains(lowerCaseFilter))
            {
                return true;
                // Filter matches first name.
            }
            else if (person.getDocLName().toLowerCase().contains(lowerCaseFilter))
            {
                return true;
                // Filter matches last name.
            }
            return false;
            // Does not match.
        });
        });
        // Wrap the FilteredList in a SortedList.
        SortedList<DoctorDetails> sortedData = new SortedList<>(filteredData);
        // Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        tableUser.setItems(sortedData);

            */

    }
}
