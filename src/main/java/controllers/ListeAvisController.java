package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Avie;
import services.AvieServices;

import java.io.IOException;
import java.sql.SQLException;

public class ListeAvisController {

    @FXML
    private TableView<Avie> tableViewAvis;

    private AvieServices avieServices;

    public ListeAvisController() {
        this.avieServices = new AvieServices();
    }

    @FXML
    void initialize() {
        try {
            // Créer les colonnes pour le TableView
            TableColumn<Avie, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<Avie, Float> etoilesColumn = new TableColumn<>("Étoiles");
            etoilesColumn.setCellValueFactory(new PropertyValueFactory<>("etoile"));

            TableColumn<Avie, Avie> supprimerColumn = new TableColumn<>("Supprimer");
            supprimerColumn.setCellFactory(param -> new TableCellDeleteButton());

            // Ajouter les colonnes au TableView
            tableViewAvis.getColumns().addAll(descriptionColumn, etoilesColumn, supprimerColumn);

            // Récupérer la liste des avis depuis le service
            ObservableList<Avie> avis = FXCollections.observableArrayList(avieServices.afficher());

            // Filtrer les avis pour exclure ceux avec des champs vides ou des étoiles nulles
            ObservableList<Avie> filteredAvis = FXCollections.observableArrayList();
            for (Avie avie : avis) {
                if (avie.getDescription() != null && !avie.getDescription().isEmpty() &&
                        avie.getEtoile() != -1 && avie.getEtoile() != -1f) {
                    filteredAvis.add(avie);
                }
            }

            // Ajouter les données filtrées à la TableView
            tableViewAvis.setItems(filteredAvis);

            // Définir la largeur des colonnes en fonction du contenu
            descriptionColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.6));
            etoilesColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.3));
            supprimerColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.1));

            // Ajuster automatiquement la taille du TableView
            tableViewAvis.autosize();

        } catch (SQLException e) {
            // Gestion de l'exception...
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des avis", e.getMessage());
        }
    }

    private class TableCellDeleteButton extends TableCell<Avie, Avie> {
        private final Button deleteButton;

        TableCellDeleteButton() {
            this.deleteButton = new Button("Supprimer");
            this.deleteButton.getStyleClass().add("delete-button"); // Ajout de la classe CSS
            this.deleteButton.setOnAction(event -> {
                Avie avie = getTableView().getItems().get(getIndex());
                try {
                    avieServices.supprimer(avie); // Supprimer l'avis de la base de données
                    tableViewAvis.getItems().remove(avie); // Supprimer l'avis de la TableView
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression de l'avis", e.getMessage());
                }
            });
        }

        @Override
        protected void updateItem(Avie item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteButton);
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

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