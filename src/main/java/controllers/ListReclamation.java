package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Reclamation;
import services.ReclamationServices;

import java.sql.SQLException;

public class ListReclamation {
    @FXML
    private TableView<Reclamation> tableViewAvis;

    private ReclamationServices reclamationServices;

    public ListReclamation() {
        this.reclamationServices = new ReclamationServices();
    }

    @FXML
    void initialize() {
        try {
            // Créer les colonnes pour le TableView
            TableColumn<Reclamation, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));


            TableColumn<Reclamation, Integer> nombreReclamationColumn = new TableColumn<>("Nombre de Réclamation");
            nombreReclamationColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_reclation")); // Correction ici

            TableColumn<Reclamation, String> imageColumn = new TableColumn<>("Image");
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));

            // Ajouter les colonnes au TableView
            tableViewAvis.getColumns().addAll(descriptionColumn, nombreReclamationColumn, imageColumn);

            // Ajouter la colonne "Supprimer"
            TableColumn<Reclamation, Reclamation> deleteColumn = new TableColumn<>("Supprimer");
            deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue())); // Utiliser ReadOnlyObjectWrapper
            deleteColumn.setCellFactory(param -> new TableCell<>() {
                private final Button deleteButton = new Button("Supprimer");

                {
                    deleteButton.setOnAction(event -> {
                        Reclamation reclamation = getTableView().getItems().get(getIndex());
                        try {
                            reclamationServices.supprimer(reclamation); // Supprimer la réclamation de la base de données
                            getTableView().getItems().remove(reclamation); // Supprimer la réclamation de la TableView
                        } catch (SQLException e) {
                            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression de la réclamation", e.getMessage());
                        }
                    });
                }

                @Override
                protected void updateItem(Reclamation item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            });
            
            tableViewAvis.getColumns().add(deleteColumn);

            // Récupérer la liste des réclamations depuis le service
            ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(reclamationServices.afficher());

            // Filtrer les réclamations pour exclure celles avec des champs vides
            ObservableList<Reclamation> filteredReclamations = FXCollections.observableArrayList();
            for (Reclamation reclamation : reclamations) {
                if (reclamation.getDescription() != null && !reclamation.getDescription().isEmpty() &&
                        reclamation.getNombre_reclation() != -1 &&
                        reclamation.getImage() != null && !reclamation.getImage().isEmpty()) {
                    filteredReclamations.add(reclamation);
                }
            }

            // Ajouter les données filtrées à la TableView
            tableViewAvis.setItems(filteredReclamations);

            // Définir la largeur des colonnes en fonction du contenu
            descriptionColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.4));
            nombreReclamationColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.3));
            imageColumn.prefWidthProperty().bind(tableViewAvis.widthProperty().multiply(0.3));

            // Ajuster automatiquement la taille du TableView
            tableViewAvis.autosize();

        } catch (SQLException e) {
            // Gestion de l'exception...
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des réclamations", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
