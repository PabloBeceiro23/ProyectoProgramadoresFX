package com.example.proyectoprogramadores.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RegistroController {
    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    public void initialize() {
        idComboBox.getItems().addAll("Java", "Python", "JavaScript");
    }
}
