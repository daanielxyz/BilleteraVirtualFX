package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarDatosController{


    @FXML private TextField campoNombre;
    @FXML private TextField campoDireccion;
    @FXML private TextField campoCorreo;
    @FXML private PasswordField campoContraseña;

    private Banco banco = Banco.getInstancia();
    private Usuario usuarioActual;



    public void inicializarUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        campoNombre.setText(usuario.getNombre());
        campoDireccion.setText(usuario.getDireccion());
        campoCorreo.setText(usuario.getCorreo());
        campoContraseña.setText(usuario.getContraseña());
    }

    @FXML
    public void guardarCambiosFX() {
        try {

            if (campoNombre.getText().isEmpty() || campoDireccion.getText().isEmpty() ||
                    campoCorreo.getText().isEmpty() || campoContraseña.getText().isEmpty()) {
                Alertas.mostrarAlertaError("Error", "Todos los campos son obligatorios.");
                return;
            }


            if (!campoCorreo.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                Alertas.mostrarAlertaError("Error", "Ingrese un correo válido.");
                return;
            }

            usuarioActual.setNombre(campoNombre.getText());
            usuarioActual.setDireccion(campoDireccion.getText());
            usuarioActual.setCorreo(campoCorreo.getText());
            usuarioActual.setContraseña(campoContraseña.getText());

            Alertas.mostrarAlertaInfo("Éxito", "Datos actualizados correctamente.");
            cerrarVentanaFX();
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error al guardar cambios", e.getMessage());
        }
    }

    @FXML
    public void cerrarVentanaFX() {
        Stage stage = (Stage) campoNombre.getScene().getWindow();
        stage.close();
    }
}
