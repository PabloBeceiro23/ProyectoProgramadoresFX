package com.example.proyectoprogramadores.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label labelMensajeError;

    public void botonLogin(ActionEvent actionEvent) {
        String usuario = textFieldUsuario.getText();
        String password = passwordField.getText();


    }
}
