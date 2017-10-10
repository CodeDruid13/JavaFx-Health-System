package Inventory;

import MainClass.Obare;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Obare on 3/21/2017.
 */
public class InventoryChoices implements Initializable {

    @FXML
    private JFXButton btnSuppliers;

    @FXML
    void gotoSuppliers(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("/Resources/Suppliers.fxml"));
        Image icon = new Image(Obare.class.getResourceAsStream("/Resources/one.png"));
        Scene scene = new Scene(root, 913, 536);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Suppliers");
        nStage.setResizable(false);
        nStage.initStyle(StageStyle.UTILITY);
        nStage.getIcons().add(icon);
        nStage.show();

        Stage one = (Stage) btnSuppliers.getScene().getWindow();
        one.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
