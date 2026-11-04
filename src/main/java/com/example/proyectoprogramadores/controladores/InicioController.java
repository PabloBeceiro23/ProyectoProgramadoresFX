package com.example.proyectoprogramadores.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class InicioController {

    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    public void initialize() {
        idComboBox.getItems().addAll("Todos","Java", "Python", "JavaScript");
    }

}
