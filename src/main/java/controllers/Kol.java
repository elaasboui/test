package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Button;
public class Kol {
    @FXML
    private Button buttonavis;

    @FXML
    private Button buttonreclamation;

    @FXML
    private ImageView myImageView;
    @FXML
    private ImageView myImageView2;


    @FXML
    void handleavis(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/accueil.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonavis.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handlereclamation(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/accueil2.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonreclamation.getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void initialize() {
        buttonavis.setOnAction(this::handleavis);
        buttonreclamation.setOnAction(this::handlereclamation);

        // Chargement de l'image dans l'ImageView
        try {
            // Charger la première image
            Image image1 = new Image(getClass().getResourceAsStream("/images/429797995_824635885924380_2922421081889051087_n.png"));
            myImageView.setImage(image1);
            myImageView.setFitWidth(500); // Largeur de l'image
            myImageView.setFitHeight(300); // Hauteur de l'image

            // Charger la deuxième image
            Image image2 = new Image(getClass().getResourceAsStream("/images/food.jpg"));
            myImageView2.setImage(image2);

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }

    }

}
