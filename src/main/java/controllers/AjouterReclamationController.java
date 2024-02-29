package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import models.Reclamation;
import services.ReclamationServices;
import javafx.scene.control.TextField;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
public class AjouterReclamationController {
    ReclamationServices rs = new ReclamationServices();



    @FXML
    private TextField idp;

    @FXML
    private TextField ip;

    @FXML
    private TextField np;

    @FXML
    void AjouterReclamation(ActionEvent event) {

    }
    @FXML
    public void AjouterReclamation(javafx.event.ActionEvent actionEvent) {
        try {
            // Vérification des saisies
            if (!validateInputs()) {
                return;
            }
            rs.ajouter(new Reclamation(idp.getText(), Integer.parseInt(np.getText()), ip.getText()));
        } catch (SQLException e) {
            showAlert("Erreur", e.getMessage());
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // Méthode pour valider les saisies
    private boolean validateInputs() {
        // Vérification du champ description (String)

        if (idp.getText() == null || idp.getText().trim().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ Description ne peut pas être vide");
            return false;
        }
        if (!isValidString(idp.getText())) {
            showAlert("Erreur de saisie", "Le champ Description doit être une chaîne de caractères");
            return false;
        }

        // Vérification du champ nombreReclamation (int)
        String nombreReclamationText = np.getText();
        if (nombreReclamationText == null || nombreReclamationText.trim().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ Nombre de Réclamation ne peut pas être vide");
            return false;
        }
        if (!isValidInteger(nombreReclamationText)) {
            showAlert("Erreur de saisie", "Le champ Nombre de Réclamation doit être un entier");
            return false;
        }

        // Vérification du champ image (String)
        if (ip.getText() == null || ip.getText().trim().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ Image ne peut pas être vide");
            return false;
        }
        if (!isValidString(ip.getText())) {
            showAlert("Erreur de saisie", "Le Image doit être une chaîne de caractères");
            return false;
        }
        return true;
    }
    private boolean isValidString(String str) {
        return str.matches("[a-zA-Z]+"); // Vérifie si la chaîne ne contient que des lettres
    }

    // Méthode pour vérifier si une chaîne peut être convertie en entier
    private boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void retourPagePrecedente(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/accueil2.fxml"));
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