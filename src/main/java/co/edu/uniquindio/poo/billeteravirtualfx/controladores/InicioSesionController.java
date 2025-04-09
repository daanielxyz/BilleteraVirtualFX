package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.NavegadorVentanas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import java.awt.*;

public class InicioSesionController {

    //TEXTFIELDS
        @FXML private TextField campoIdentificacion;
        @FXML private PasswordField campoContraseña;

    //INSTANCIAS
        private final Banco banco = Banco.getInstancia();

    //METODOS DEL CONTROLADOR
        @FXML
        public void iniciarSesionFX() throws Exception {
            Usuario usuarioIniciarSesion = banco.usuarioPorIdYContraseña(campoIdentificacion.getText(), campoContraseña.getText());
            try{
                banco.iniciarSesion(usuarioIniciarSesion);
                Alertas.mostrarAlertaInfo("Inicio de sesion exitoso.", "Bienvenido "+usuarioIniciarSesion.getNombre()+".");
                NavegadorVentanas.navegarVentanaConUsuario("/PanelPrincipal.fxml", "Panel Principal", usuarioIniciarSesion);
                cerrarVentanaFX();

            } catch (Exception e) {
                Alertas.mostrarAlertaError("Error al iniciar sesion", e.getMessage());
            }
        }

        @FXML
        public void cerrarVentanaFX() {
            Stage stage = (Stage) campoIdentificacion.getScene().getWindow();
            stage.close();
        }
}
