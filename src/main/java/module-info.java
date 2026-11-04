module com.example.proyectoprogramadores {
    requires javafx.controls;
    requires javafx.fxml;

    // Esta línea permite que JavaFX FXML cree instancias de tus controladores mediante reflexión
    opens com.example.proyectoprogramadores.controladores to javafx.fxml;

    // Si tu clase principal (HelloApplication) está en la raíz, mantén también esta línea
    opens com.example.proyectoprogramadores to javafx.fxml;
    exports com.example.proyectoprogramadores;
}
