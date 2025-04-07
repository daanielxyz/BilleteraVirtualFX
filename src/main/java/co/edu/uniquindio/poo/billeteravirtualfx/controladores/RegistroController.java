package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    //TEXTFIELDS
        @FXML public TextField campoIdentificacion;
        @FXML public TextField campoNombre;
        @FXML public TextField campoCorreo;
        @FXML public TextField campoDireccion;
        @FXML public PasswordField campoContraseña;




    //INSTANCIAS
        private final Banco banco = Banco.getInstancia();

    //METODOS DEL CONTROLADOR
        @FXML
        public void registrarUsuarioFX() throws Exception{
            try {
                Usuario usuario = Usuario.builder().nombre(campoNombre.getText()).direccion(campoDireccion.getText()).id(campoIdentificacion.getText()).correo(campoCorreo.getText()).contraseña(campoContraseña.getText()).build();
                banco.registrarUsuario(usuario);
                Alertas.mostrarAlertaInfo("Usuario registrado exitosamente", "Gracias por confiar en nosotros " +campoNombre.getText()+ ".");
                cerrarVentanaFX();
            } catch (Exception e) {
                Alertas.mostrarAlertaError("Error al registrar usuario", e.getMessage());
            }
        }

        @FXML
        public void cerrarVentanaFX(){
            Stage stage = (Stage) campoNombre.getScene().getWindow();
            stage.close();
        }

}
