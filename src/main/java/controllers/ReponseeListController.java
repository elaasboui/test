package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Reponsee;
import services.ReponseeServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReponseeListController implements Initializable {

    @FXML
    private TableView<Reponsee> tableView;

    private final ReponseeServices reponseeServices = new ReponseeServices();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        loadReponseeData();
    }

    private void setupTable() {
        TableColumn<Reponsee, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reponsee, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> {
            Reponsee reponsee = event.getRowValue();
            reponsee.setDescription(event.getNewValue());
            try {
                reponseeServices.modifier(reponsee);
                tableView.refresh(); // Refresh the table after editing
            } catch (SQLException e) {
                e.printStackTrace(); // Handle this exception properly
            }
        });

        TableColumn<Reponsee, Integer> reclamationColumn = new TableColumn<>("Reclamation");
        reclamationColumn.setCellValueFactory(new PropertyValueFactory<>("rec_id"));

        TableColumn<Reponsee, Void> modifyColumn = new TableColumn<>("Modify");
        modifyColumn.setCellFactory(getModifyButtonCellFactory());

        TableColumn<Reponsee, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(getDeleteButtonCellFactory());

        tableView.getColumns().addAll(idColumn, descriptionColumn, reclamationColumn, modifyColumn, deleteColumn);
    }

    private Callback<TableColumn<Reponsee, Void>, TableCell<Reponsee, Void>> getModifyButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Reponsee, Void> call(final TableColumn<Reponsee, Void> param) {
                final TableCell<Reponsee, Void> cell = new TableCell<>() {
                    private final Button modifyButton = new Button("Modify");

                    {
                        modifyButton.setOnAction(event -> {
                            Reponsee reponsee = getTableView().getItems().get(getIndex());
                            // Implement modify action here, like opening a dialog for editing
                            System.out.println("Modify Reponsee: " + reponsee.getId());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierreponse.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            ModifierReponse modifierReponseController = loader.getController();
                            modifierReponseController.initData(reponsee);

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.showAndWait();
                        loadReponseeData();
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(modifyButton);
                        }
                    }
                };
                return cell;
            }
        };
    }

    private Callback<TableColumn<Reponsee, Void>, TableCell<Reponsee, Void>> getDeleteButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Reponsee, Void> call(final TableColumn<Reponsee, Void> param) {
                final TableCell<Reponsee, Void> cell = new TableCell<>() {
                    private final Button deleteButton = new Button("Supprimer");
                    {
                        deleteButton.setOnAction(event -> {
                            Reponsee reponsee = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet reponse ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    reponseeServices.supprimer(reponsee.getId());
                                    tableView.getItems().remove(reponsee);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };
    }

    private void loadReponseeData() {
        try {
            List<Reponsee> reponsees = reponseeServices.getAllReponsees();
            ObservableList<Reponsee> observableList = FXCollections.observableArrayList(reponsees);
            tableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this exception properly
        }
    }

    public void AjouterReponse(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterReponsee.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();


    }
}
