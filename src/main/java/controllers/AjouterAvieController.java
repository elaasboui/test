package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.Avie;
import services.AvieServices;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjouterAvieController {

    AvieServices as = new AvieServices();

    @FXML
    private TextField dp;

    @FXML
    private TextField ep;

    @FXML
    void AjouterAvie(ActionEvent event) {

    }

    @FXML
    public void AjouterAvie(javafx.event.ActionEvent actionEvent) {
        try {
            // Vérification des saisies
            if (!validateInputs()) {
                return;
            }
            as.ajouter(new Avie(dp.getText(), Float.parseFloat(ep.getText())));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> buttonType = alert.showAndWait();
        }
    }

    // Méthode pour valider les saisies
    private boolean validateInputs() {
        // Vérification du champ dp (String)
        if (dp.getText() == null || dp.getText().trim().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ DP ne peut pas être vide");
            return false;
        }
        // Vérification du champ ep (float)
        String epText = ep.getText();
        if (epText == null || epText.trim().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ EP ne peut pas être vide");
            return false;
        }
        if (!isValidFloat(epText)) {
            showAlert("Erreur de saisie", "Le champ EP doit être un nombre décimal");
            return false;
        }
        return true;
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Méthode pour vérifier si une chaîne peut être convertie en float
    private boolean isValidFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void retourPagePrecedente(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/accueil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
