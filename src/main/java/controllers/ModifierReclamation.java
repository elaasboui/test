package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Reclamation;
import services.AvieServices;
import services.ReclamationServices;

import java.sql.SQLException;

public class ModifierReclamation {
    private ReclamationServices rs = new ReclamationServices();
    @FXML
    private TextField idp;

    @FXML
    private TextField dp;

    @FXML
    private TextField ip;

    @FXML
    void modifierReclamation(ActionEvent event) {
        try {
            // Récupérer l'ID à partir du champ de texte
            int reclamationId = Integer.parseInt(idp.getText());

            // Récupérer l'objet Reclamation à partir de la base de données en fonction de l'ID
            Reclamation reclamation = rs.getReclamationById(reclamationId);
            if (reclamation == null) {
                // Afficher un message d'erreur si aucune réclamation n'est trouvée avec cet ID
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Aucune réclamation trouvée avec cet ID.");
                alert.showAndWait();
                return;
            }

            // Mettre à jour les données de la réclamation avec les valeurs des champs de texte
            reclamation.setDescription(dp.getText());
            reclamation.setImage(ip.getText());

            // Appeler la méthode de mise à jour dans le service ReclamationServices
            rs.modifier(reclamation);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Réclamation modifiée avec succès !");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            // Gérer les erreurs de format de champ de texte (par exemple, si l'utilisateur entre du texte au lieu d'un nombre)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez entrer un nombre valide dans le champ ID.");
            alert.showAndWait();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Erreur lors de la mise à jour de la réclamation : " + e.getMessage());
            alert.showAndWait();
        }

    }
}
