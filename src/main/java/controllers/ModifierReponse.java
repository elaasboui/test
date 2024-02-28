package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Reclamation;
import models.Reponsee;
import services.ReclamationServices;
import services.ReponseeServices;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifierReponse implements Initializable {

    @FXML
    private TextField dp;

    @FXML
    private ComboBox<Reclamation> reccombobox;

    private Reponsee reponseeToUpdate;
    private ReponseeServices reponseeServices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reponseeServices = new ReponseeServices();
    }

    public void initData(Reponsee reponsee) {
        reponseeToUpdate = reponsee;
        dp.setText(reponsee.getDescription());

        try {
            ReclamationServices rs=new ReclamationServices();
            List<Reclamation> reclamationList = rs.afficher();
            ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamationList);
            reccombobox.setItems(observableReclamations);
            reccombobox.setValue(getReclamationForReponsee(reponsee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Reclamation getReclamationForReponsee(Reponsee reponsee) {
        try {
            ReclamationServices rs = new ReclamationServices();
            return rs.getReclamationById(reponsee.getRec_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void modifierReponse() {
        reponseeToUpdate.setDescription(dp.getText());

        Reclamation selectedReclamation = reccombobox.getValue();
        if (selectedReclamation != null) {
            reponseeToUpdate.setRec_id(selectedReclamation.getId());
        } else {
            reponseeToUpdate.setRec_id(-1); // In case no reclamation(complaint) is selected ###
        }

        try {
            reponseeServices.modifier(reponseeToUpdate);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("reponse modifiée avec succées");
            Optional<ButtonType> buttonType = alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reponsee getUpdatedReponsee() {
        return reponseeToUpdate;
    }
}