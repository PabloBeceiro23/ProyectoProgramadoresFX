package com.example.proyectoprogramadores.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
    @FXML
    private ComboBox<String> idComboBox;
    @FXML
    private Button btnIrLogin;

    @FXML
    public void initialize() {
        idComboBox.getItems().addAll("Java", "Python", "JavaScript");
    }

    public void volverAlInicio(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/proyectoprogramadores/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnIrLogin.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
