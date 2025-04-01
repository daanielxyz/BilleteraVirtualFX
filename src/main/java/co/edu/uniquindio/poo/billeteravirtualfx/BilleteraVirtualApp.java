package co.edu.uniquindio.poo.billeteravirtualfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BilleteraVirtualApp extends Application {


    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(BilleteraVirtualApp.class.getResource("/Principal.fxml"));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Billetera Virtual");
        stage.show();
    }


    public static void main(String[] args) {
        launch(BilleteraVirtualApp.class, args);
    }
}

