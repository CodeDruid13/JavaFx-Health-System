package Registration;

/**
 * Created by Obare on 3/5/2017.
 */

import DataBase.DBController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PatientDataController {

    @FXML
    private TableView<PatientDetails> tableUser;

    @FXML
    private TableColumn<PatientDetails, String> columnPID;

    @FXML
    private TableColumn<PatientDetails, String>  columnFName;

    @FXML
    private TableColumn<PatientDetails, String>  columnLName;

    @FXML
    private TableColumn<PatientDetails, String>  columnKinNumber;

    @FXML
    private TableColumn<PatientDetails, String> columnIDNumber;

    @FXML
    private TableColumn<PatientDetails, String>  columnGender;

    @FXML
    private TableColumn<PatientDetails, String>  columnPhoneNumber;

    @FXML
    private TableColumn<PatientDetails, String> columnEmail;

    @FXML
    private TableColumn<PatientDetails, String>  columnBloodGroup;

    @FXML
    private TableColumn<PatientDetails, String>  columnReasons;


    @FXML
    private JFXButton loadPatData;

    @FXML
    private JFXButton btnExport;

    private ObservableList<PatientDetails> data = FXCollections.observableArrayList();
    DBController dc;
    ResultSet resultSet = null;

    @FXML
    void LoadPatData(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM patientregistration");
            while (rs.next()) {

                data.add(new PatientDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                        , rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnPID.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        columnFName.setCellValueFactory(new PropertyValueFactory<>("PatientFName"));
        columnLName.setCellValueFactory(new PropertyValueFactory<>("PatientLName"));
        columnKinNumber.setCellValueFactory(new PropertyValueFactory<>("NextOfKin"));
        columnIDNumber.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnBloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
        columnReasons.setCellValueFactory(new PropertyValueFactory<>("Remarks"));


        tableUser.setItems(null);
        tableUser.setItems(data);

    }

    @FXML
    void PrintPatReport(ActionEvent event) {

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("IMPORTANT!");
        alert.setHeaderText("Load all Patients!");
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
            row1.createCell(0).setCellValue(" PATIENT'S ID");
            row1.createCell(1).setCellValue("FIRST NAME");
            row1.createCell(2).setCellValue("LAST NAME");
            row1.createCell(3).setCellValue("NEXT OF KIN");
            row1.createCell(4).setCellValue("ID NUMBER");
            row1.createCell(5).setCellValue("GENDER");
            row1.createCell(6).setCellValue("PHONE NUMBER");
            row1.createCell(7).setCellValue("EMAIL");
            row1.createCell(8).setCellValue("BLOOD GROUP");
            row1.createCell(9).setCellValue("REASONS");
            Row row2;

            String query = "SELECT * FROM patientregistration ";
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
                row2.createCell(9).setCellValue(resultSet.getString(10));
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


}
