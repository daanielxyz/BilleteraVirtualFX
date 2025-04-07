package co.edu.uniquindio.poo.billeteravirtualfx.helpers;

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
}

