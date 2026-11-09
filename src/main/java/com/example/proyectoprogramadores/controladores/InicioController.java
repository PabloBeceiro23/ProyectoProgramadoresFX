package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private ComboBox<String> idComboBox;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private TableView<Programador> tablaProgramadores;
    @FXML
    private TableColumn<Programador, String> colUsuario;
    @FXML
    private TableColumn<Programador, String> colGenero;
    @FXML
    private TableColumn<Programador, String> colLenguaje;

    @FXML
    public void initialize() {
        idComboBox.getItems().addAll("Todos","Java", "Python", "JavaScript");
        idComboBox.getSelectionModel().selectFirst();
    }


    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        cambiarVentana("/com/example/proyectoprogramadores/login.fxml");
    }

    public void cambiarVentana(String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(ruta));
        Parent root = loader.load();

        Stage stage = (Stage) botonCerrarSesion.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.centerOnScreen();

    }

    public void filtrarPorLenguaje(ActionEvent actionEvent) {

    }
}
