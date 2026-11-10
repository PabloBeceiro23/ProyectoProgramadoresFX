package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.DAO.inicioDAO;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.GenerarExcel;
import com.example.proyectoprogramadores.util.GenerarPDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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

        inicioDAO dao = new inicioDAO();
        ArrayList<Programador>  listaProgramadores = dao.listarProgramadores();
        ObservableList<Programador> datos = FXCollections.observableArrayList(listaProgramadores);
        tablaProgramadores.setItems(datos);

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

        if(idComboBox.getValue().equals("Todos")){
            ArrayList<Programador>  listaProgramadores = dao.listarProgramadores();
            ObservableList<Programador> datos = FXCollections.observableArrayList(listaProgramadores);
            tablaProgramadores.setItems(datos);
        } else {
            ArrayList<Programador>  listaProgramadores = dao.filtrarPorLenguaje(idComboBox.getValue().toString());

            // lista observable para que se actualice automaticamente con los cambios
            ObservableList<Programador> datos = FXCollections.observableArrayList(listaProgramadores);
            tablaProgramadores.setItems(datos);
        }







    }

    public void generarPDF(ActionEvent actionEvent) throws FileNotFoundException {
        GenerarPDF generarPDF = new GenerarPDF();

        // se crea un arraylist con los datos que hay en la tabla en ese momento
        ArrayList<Programador> listaProgramadores = new ArrayList<>(tablaProgramadores.getItems());


        if(generarPDF.generarPDF(listaProgramadores)){
            ventanaEmergenteInformativa("PDF generado exitosamente");
        } else {
            ventanaEmergenteError("Error al crear pdf");
        }
    }

    public void ventanaEmergenteError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al crear PDF");
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void ventanaEmergenteInformativa(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Crear PDF");
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void generarExcel(ActionEvent actionEvent) throws FileNotFoundException {
        GenerarExcel generarExcel = new GenerarExcel();

        ArrayList<Programador> listaProgramadores = new ArrayList<>(tablaProgramadores.getItems());

        if(generarExcel.GenerarExcel(listaProgramadores)){
            ventanaEmergenteInformativa("Excel generado exitosamente");
        } else {
            ventanaEmergenteError("Error al crear excel");
        }
    }
}
