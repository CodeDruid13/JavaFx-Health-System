package Services;

/**
 * Created by Obare on 3/28/2017.
 */

import MainClass.Obare;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServiceChoices {

    @FXML
    private JFXButton btnServices;

    @FXML
    private JFXButton btnPatientServices;

    @FXML
    private JFXButton btnExit;


    @FXML
    void gotoCreateServices(ActionEvent event) {


    }

    @FXML
    void gotoPatientServices(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Resources/PatientServices.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 1000, 482);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("PATIENT SERVICES");
        nStage.setResizable(false);
        nStage.initStyle(StageStyle.UTILITY);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnPatientServices.getScene().getWindow();
        one.close();

    }

}
