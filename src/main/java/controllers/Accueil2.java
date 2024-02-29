package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Accueil2 {
    @FXML
    private ImageView myImageView;
    @FXML
    private Button buttonajouterreclamation;

    @FXML
    private Button buttonmodifierreclamation;

    @FXML
    private Button buttonafficherreclamation;

    @FXML
    void handleafficherreclamation(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/listReclamation.fxml"));
            Scene scene = new Scene(page);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handleajouterreclamation(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/AjouterReclamation.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonajouterreclamation.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handlemodifierreclamation(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/modifierReclamation.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonmodifierreclamation.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {
        buttonajouterreclamation.setOnAction(this::handleajouterreclamation);
        buttonafficherreclamation.setOnAction(this::handleafficherreclamation);
        buttonmodifierreclamation.setOnAction(this::handlemodifierreclamation);
        try {
            Image image = new Image(getClass().getResourceAsStream("/images/e (2).jpg"));
            myImageView.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image.");
        }}

    public void retourPagePrecedente(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kol.fxml"));
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
