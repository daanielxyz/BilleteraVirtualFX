package co.edu.uniquindio.poo.billeteravirtualfx.helpers;
import co.edu.uniquindio.poo.billeteravirtualfx.controladores.*;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Billetera;

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

    public static void navegarVentanaConBilletera(String nombreArchivoFxml, String tituloVentana, Billetera billetera, Runnable onCloseAction) {
        try {
            FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            TransferenciaController controller = loader.getController();
            controller.inicializarBilletera(billetera);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);


            stage.setOnHidden(event -> onCloseAction.run());

            stage.show();
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", "Error al abrir la ventana de transferencia");
        }
    }

    public static void navegarVentanaConsulta(String nombreArchivoFxml, String tituloVentana, Billetera billetera, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            ConsultaTransaccionesController controller = loader.getController();
            controller.inicializarDatos(billetera, usuario);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);
            stage.show();
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", "Error al abrir la ventana de consulta");
        }
    }

    public static Stage navegarVentanaEditarDatos(String nombreArchivoFxml, String tituloVentana, Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(NavegadorVentanas.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            EditarDatosController controller = loader.getController();
            controller.inicializarUsuario(usuario);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle(tituloVentana);
            stage.show();
            return stage; // Devolver el Stage
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", "Error al abrir la ventana de edición");
            return null;
        }
    }

}

