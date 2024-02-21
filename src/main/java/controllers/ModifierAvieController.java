package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Avie;
import services.AvieServices;

import utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ModifierAvieController {

    private AvieServices as = new AvieServices();

    @FXML
    private TextArea dp;

    @FXML
    private TextField ep;

    @FXML
    private TextField id;

    @FXML
    void ModifierAvis(ActionEvent event) {
        try {
            // Récupérer l'ID à partir du champ de texte
            int avieId = Integer.parseInt(id.getText());

            // Récupérer l'objet Avie à partir de la base de données en fonction de l'ID
            Avie avie = as.getAvieById(avieId);
            if (avie == null) {
                // Afficher un message d'erreur si aucun Avie n'est trouvé avec cet ID
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Aucun Avie trouvé avec cet ID.");
                alert.showAndWait();
                return;
            }

            // Mettre à jour les données de l'Avie avec les valeurs des champs de texte
            avie.setDescription(dp.getText());
            avie.setEtoile(Float.parseFloat(ep.getText()));

            // Appeler la méthode de mise à jour dans le service AvieServices
            as.modifier(avie);

            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Avie modifié avec succès !");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            // Gérer les erreurs de format de champ de texte (par exemple, si l'utilisateur entre du texte au lieu d'un nombre)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez entrer un nombre valide dans le champ ID ou Etoile.");
            alert.showAndWait();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Erreur lors de la mise à jour de l'Avie : " + e.getMessage());
            alert.showAndWait();
        }
    }
}
