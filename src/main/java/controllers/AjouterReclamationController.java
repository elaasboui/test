package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Reclamation;
import services.ReclamationServices;
import javafx.scene.control.TextField;


import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Optional;
public class AjouterReclamationController {
    ReclamationServices rs = new ReclamationServices();


    @FXML
    private TextField ep;

    @FXML
    private TextField idp;

    @FXML
    private TextField ip;

    @FXML
    private TextField np;

    @FXML
    void AjouterReclamation(ActionEvent event) {

    }
    public void AjouterReclamation(javafx.event.ActionEvent actionEvent) {
        try {
            rs.ajouter(new Reclamation( idp.getText(),Float.parseFloat(ep.getText()),Integer.parseInt(np.getText()) ,ip.getText() ));
        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Erroe");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> buttonType = alert.showAndWait();

        }
    }

}