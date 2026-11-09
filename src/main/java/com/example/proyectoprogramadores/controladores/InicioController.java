package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.DAO.inicioDAO;
import com.example.proyectoprogramadores.entity.Programador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colLenguaje.setCellValueFactory(new PropertyValueFactory<>("lenguaje"));
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

        inicioDAO dao = new inicioDAO();
        // se crea un arraylist de programadores segun lenguaje de programacion seleccionado
        ArrayList<Programador>  listaProgramadores = dao.filtrarPorLenguaje(idComboBox.getValue());

        ObservableList<Programador> datos = FXCollections.observableArrayList(listaProgramadores);
        tablaProgramadores.setItems(datos);





    }
}
