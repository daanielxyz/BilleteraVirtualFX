package co.edu.uniquindio.poo.billeteravirtualfx.helpers;
import co.edu.uniquindio.poo.billeteravirtualfx.controladores.PanelPrincipalController;
import co.edu.uniquindio.poo.billeteravirtualfx.controladores.PrincipalController;
import co.edu.uniquindio.poo.billeteravirtualfx.controladores.TransferenciaController;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavegadorVentanas {


    public static void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
                FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(true);
                stage.setTitle(tituloVentana);
                stage.show();

        } catch (Exception e) {
                Alertas.mostrarAlertaError("Error", "Error al intentar abrir nueva ventana");
        }
    }

    public static void navegarVentanaConUsuario(String nombreArchivoFxml, String tituloVentana, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            PanelPrincipalController controller = loader.getController();
            controller.inicializarUsuario(usuario);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);
            stage.show();

        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", "Error al abrir la ventana con el usuario");
        }
    }

    public static void navegarVentanaTransferenciaConUsuario(String nombreArchivoFxml, String tituloVentana, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            TransferenciaController controller = loader.getController();
            controller.inicializarUsuario(usuario, () -> panelPrincipalController.getTablaTransacciones().refresh());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);
            stage.show();

        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", "Error al abrir la ventana con el usuario");
        }
    }
}

