package com.example.proyectoprogramadores.controladores;

import com.example.proyectoprogramadores.DAO.editarPerfilDAO;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.entity.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class editarPerfilController {
    @FXML
    private ComboBox<String> idComboBox;



    @FXML
    private ToggleGroup opcionesGenero;

    @FXML
    private TextField idUsuario;

    @FXML
    private TextField idPassword;

    @FXML
    public void initialize() {
        Programador programadorActual = Sesion.getProgramador();
        idUsuario.setText(programadorActual.getUsuario());
        idUsuario.setDisable(true);


        idComboBox.getItems().addAll("Java", "Python", "JavaScript");
    }


    public void guardarDatos(ActionEvent actionEvent) {
        Programador programadorActual = Sesion.getProgramador();

        boolean valoresValidos = true;
        String mensajeError = "";




        String password = idPassword.getText().trim();

        if(comprobarCampoVacio(password))
            valoresValidos = false;

        // la contraseña tiene que tener 8 caracteres, una minus y una mayus
        if(password.length() < 8){
            valoresValidos = false;
            mensajeError += "La contraseña debe de tener un minimo de 8 caracteres.\n";
        }

        // tiene que tener al menos una mayus
        if(!password.matches(".*[A-Z].*")){
            valoresValidos = false;
            mensajeError += "La contraseña tiene que tener al menos una mayuscula\n";
        }
// tiene que tener al menos una minus
        if(!password.matches(".*[a-z].*")){
            valoresValidos = false;
            mensajeError += "La contraseña tiene que tener al menos una minuscula\n";
        }


        RadioButton seleccionado = (RadioButton) opcionesGenero.getSelectedToggle();

        String genero = "";
        if (seleccionado != null) {
            genero = seleccionado.getText();   // "Hombre" o "Mujer"
        }
        if(comprobarCampoVacio(genero))
            valoresValidos = false;

        String lenguajeProgramacion = idComboBox.getValue();
        if(comprobarCampoVacio(lenguajeProgramacion))
            valoresValidos = false;

        if(valoresValidos){
            // si son validos se llama al dao y se le pasa el programador con las credenciales acutales para buscarlo en la bd
            // y se pasa tambien el programador modificado para modificarlo por el actual
            editarPerfilDAO editarPerfilDAO = new editarPerfilDAO();

            Programador programadorModificado = new Programador();
            programadorModificado.setUsuario(programadorActual.getUsuario());
            programadorModificado.setPassword(password);
            programadorModificado.setGenero(genero);
            programadorModificado.setLenguaje(lenguajeProgramacion);

           if( editarPerfilDAO.modificarProgramador(programadorActual,programadorModificado)){
               ventanaEmergenteInformativa("Datos actualizados","Datos actualizados con exito");
           } else {
               ventanaEmergenteError("Error","Error al actualizar los datos");
           }

        }

    }

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

    public void ventanaEmergenteInformativa(String tituloVentana, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(tituloVentana);
        alerta.setHeaderText(null);   // sin cabecera
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}




