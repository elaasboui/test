package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class Accueil {
    @FXML
    private Button buttonafficheravis;
    
    @FXML
    void handleAfficherAvis(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/listAvis.fxml"));
            Scene scene = new Scene(page);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private Button buttonmodifieravis;

    @FXML
    private ImageView myImageView;

    @FXML
    private Button buttonajouteravis;

    @FXML
    void handleModifierAvis(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/modifierAvie.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonmodifieravis.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleAjouterAvis(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/AjouterAvie.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonajouteravis.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        buttonajouteravis.setOnAction(this::handleAjouterAvis);
        buttonmodifieravis.setOnAction(this::handleModifierAvis);

        // Chargement de l'image dans l'ImageView
        try {
            Image image = new Image(getClass().getResourceAsStream("/images/Pizza.jpg"));
            myImageView.setImage(image);
            myImageView.setFitWidth(1000); // Largeur de l'image
            myImageView.setFitHeight(300); // Hauteur de l'image
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image.");
        }
    }
}
