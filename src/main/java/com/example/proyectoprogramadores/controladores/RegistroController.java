package com.example.proyectoprogramadores.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
    @FXML
    private ComboBox<String> idComboBox;
    @FXML
    private Button btnIrLogin;
    @FXML
    private TextField txtNombreRegistro;
    @FXML
    private TextField txtPasswordRegistro;
    @FXML
    private ToggleGroup grupoOpciones;



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

    public void botonRegistrar(ActionEvent actionEvent) {

        String usuario = txtNombreRegistro.getText();
        String password = txtPasswordRegistro.getText();
        RadioButton seleccionado = (RadioButton) grupoOpciones.getSelectedToggle();

        if (seleccionado != null) {
            String genero = seleccionado.getText();   // "Hombre" o "Mujer"
        }

        String lenguajeProgramacion = idComboBox.getValue();
    }
}
