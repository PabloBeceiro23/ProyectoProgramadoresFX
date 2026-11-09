package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.DAO.registroDAO;
import com.example.proyectoprogramadores.entity.Programador;
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
        // sirve para que el combobox tenga seleccionado el primer elemento por defecto
        idComboBox.getSelectionModel().selectFirst();
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

        boolean valoresCorrectos = true;
        String mensajeError = "";

        // se recogen los datos y se comprueban que no esten vacios
        String usuario = txtNombreRegistro.getText();
        if(comprobarCampoVacio(usuario))
            valoresCorrectos = false;


        String password = txtPasswordRegistro.getText();
        if(comprobarCampoVacio(password))
            valoresCorrectos = false;


        RadioButton seleccionado = (RadioButton) grupoOpciones.getSelectedToggle();

        String genero = "";
        if (seleccionado != null) {
             genero = seleccionado.getText();   // "Hombre" o "Mujer"
        }

        if(comprobarCampoVacio(genero))
            valoresCorrectos = false;

        String lenguajeProgramacion = idComboBox.getValue();
        if(comprobarCampoVacio(lenguajeProgramacion))
            valoresCorrectos = false;

        //a partir de aqui se comprueban los requisitos que tienen que tener cada campo

        if(usuario.length() < 5){
            valoresCorrectos = false;
            mensajeError += "El usuario debe de tener mínimo 5 caracteres.\n";
        }

        // la contraseña tiene que tener 8 caracteres, una minus y una mayus
        if(password.length() < 8){
            valoresCorrectos = false;
            mensajeError += "La contraseña debe de tener un minimo de 8 caracteres.\n";
        }

        // tiene que tener al menos una mayus
        if(!password.matches(".*[A-Z].*")){
            valoresCorrectos = false;
            mensajeError += "La contraseña tiene que tener al menos una mayuscula\n";
        }
// tiene que tener al menos una minus
        if(!password.matches(".*[a-z].*")){
            valoresCorrectos = false;
            mensajeError += "La contraseña tiene que tener al menos una minuscula\n";
        }


        if(valoresCorrectos){
            Programador programador = new  Programador();
            programador.setUsuario(usuario);
            programador.setPassword(password);
            programador.setLenguaje(lenguajeProgramacion);
            programador.setGenero(genero);

            registroDAO registroDAO = new registroDAO();

            // si es true entonces se ha registrado el programador
            if(registroDAO.registrarProgramador(programador)){
                ventanaEmergenteInformativa("Registrar programador", "Programador registrado correctamente");
            } else {
                ventanaEmergenteError("Registrar programador", "El programador que has intentado registrar ya existe");
            }
        } else {
            ventanaEmergenteError("Registrar programador", mensajeError);
        }


    }

    public void ventanaEmergenteInformativa(String tituloVentana, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(tituloVentana);
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // comprueba que el campo no este vacio. si lo esta devuelve true
    public boolean comprobarCampoVacio(String campo) {
        if(campo.equals("")){
            return true;
        } else  return false;
    }

    public void ventanaEmergenteError(String tituloVentana, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(tituloVentana);
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
