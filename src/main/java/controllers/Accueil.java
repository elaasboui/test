package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Accueil {
    @FXML
    private Button buttonmodifieravis;

    @FXML
    private Button buttonajouteravis;
    @FXML
    void handleModifierAvis(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/modifierAvie.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) buttonajouteravis.getScene().getWindow();
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
        }}

    void initialize(){
        buttonajouteravis.setOnAction(this::handleAjouterAvis);
        buttonmodifieravis.setOnAction(this::handleModifierAvis);

    }

}
