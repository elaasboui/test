package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponsee;
import services.AvieServices;
import services.ReclamationServices;
import services.ReponseeServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjouterReponsee implements Initializable {
    @FXML
    public ComboBox<Reclamation> reccombobox;
    ReponseeServices rs = new ReponseeServices();
    @FXML
    protected  TableView<Reponsee> tableview;
    @FXML
    private TextField dp;

    public void AjouterReponsee(ActionEvent actionEvent) {
        try {
            Reponsee r=new Reponsee( dp.getText());
            r.rec_id=reccombobox.getValue().getId();
            rs.ajouter(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Une réponse a été ajouté avec succées");
            Optional<ButtonType> buttonType = alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReponse.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erroe");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> buttonType = alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reclamation> reclamations = null;
        try {
            ReclamationServices rs=new ReclamationServices();
            reclamations = rs.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList(reclamations);

        reccombobox.setItems(reclamationList);
    }

    public class TableCellDeleteButton extends TableCell<Reponsee, Reponsee> {
        private final Button deleteButton;

        TableCellDeleteButton() {
            this.deleteButton = new Button("Supprimer");
            this.deleteButton.setOnAction(event -> {
                Reponsee reponsee = getTableView().getItems().get(getIndex());
                try {
                    rs.supprimer(reponsee.getId());
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            });
        }

        @Override
        protected void updateItem(Reponsee item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteButton);
            }
        }
    }

    private class TableCellEditButton extends TableCell<Reponsee, Reponsee> {
        private final Button editButton;

        TableCellEditButton() {
            this.editButton = new Button("Modifier");
            this.editButton.setOnAction(event -> {
                Reponsee reponsee = getTableView().getItems().get(getIndex());
                // Code pour gérer l'événement de modification
            });
        }

        @Override
        protected void updateItem(Reponsee item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(editButton);
            }
        }
    }
}
