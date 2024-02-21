package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Avie;
import services.AvieServices;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Optional;
public class AjouterAvieController {
     AvieServices as = new AvieServices();
    @FXML
    private TextField dp;

    @FXML
    private TextField ep;

    @FXML
    void AjouterAvie(ActionEvent event) {

    }

    public void AjouterAvie(javafx.event.ActionEvent actionEvent) {
        try {
            as.ajouter(new Avie(dp.getText() , Float.parseFloat(ep.getText())));
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erroe");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> buttonType = alert.showAndWait();

        }
    }

}
