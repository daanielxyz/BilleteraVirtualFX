package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.BilleteraVirtualApp;
import co.edu.uniquindio.poo.billeteravirtualfx.helpers.NavegadorVentanas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;

public class PrincipalController {

    //METODOS DEL CONTROLADOR
        @FXML
        private void iniciarSesionFX(){
           NavegadorVentanas.navegarVentana("/InicioSesion.fxml", "Inicio de sesión");
        }

        @FXML
        private void registrarseFX(){
            NavegadorVentanas.navegarVentana("/Registro.fxml", "Registro de usuario");
        }
}
