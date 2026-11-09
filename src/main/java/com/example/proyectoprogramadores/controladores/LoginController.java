package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.DAO.loginDAO;
import com.example.proyectoprogramadores.entity.Programador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class LoginController {
    @FXML
    private TextField txtUsuarioLogin;
    @FXML
    private PasswordField txtPasswordLogin;
    @FXML
    private Label labelMensajeError;

    @FXML
    private Button buttonRegistro;

    static loginDAO  loginDAO = new loginDAO();


// comprueba que el campo no este vacio. si lo esta devuelve true
    public boolean comprobarCampoVacio(String campo) {
        if(campo.equals("")){
            return true;
        } else  return false;
    }

    // logica del boton login
    public void botonLogin(ActionEvent actionEvent) throws IOException {

        String usuario = txtUsuarioLogin.getText().trim();
        String password = txtPasswordLogin.getText().trim();
        boolean valoresValidos = true;
        String mensajeError = "";



        // usuario debe de tener minimo 5 caracteres.
        if(usuario.length() < 5){
            valoresValidos = false;
            mensajeError += "El usuario debe de tener mínimo 5 caracteres.\n";
        }

        if(comprobarCampoVacio(usuario)){
            mensajeError += "El campo usuario es requerido.\n";
            valoresValidos = false;
        }




        // la contraseña tiene que tener 8 caracteres, una minus y una mayus
        if(password.length() < 8){
            valoresValidos = false;
            mensajeError += "La contraseña debe de tener un minimo de 8 caracteres.\n";
        }

        if(!password.matches(".*[A-Z].*")){
            valoresValidos = false;
            mensajeError += "La contraseña tiene que tener al menos una mayuscula\n";
        }

        if(!password.matches(".*[a-z].*")){
            valoresValidos = false;
            mensajeError += "La contraseña tiene que tener al menos una minuscula\n";
        }

        if(comprobarCampoVacio(password)){
            mensajeError += "El campo contraseña es requerido.\n";
            valoresValidos = false;
        }



        if(valoresValidos){
            Programador programador = new Programador();
            programador.setUsuario(usuario);
            programador.setPassword(password);

            if(loginDAO.login(programador)){
                cambiarVentana("/com/example/proyectoprogramadores/inicio.fxml");
            } else {
                ventanaEmergenteError("Registrar programador", "Programador no encontrado");
            }
        } else {
            ventanaEmergenteError("Iniciar sesion", mensajeError);
        }

    }


    public void botonRegistrarse(ActionEvent actionEvent) throws IOException {
        cambiarVentana("/com/example/proyectoprogramadores/registro.fxml");
    }

    public void cambiarVentana(String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(ruta));
        Parent root = loader.load();

        Stage stage = (Stage) buttonRegistro.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.centerOnScreen();

    }

    public void ventanaEmergenteError(String tituloVentana, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(tituloVentana);
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
