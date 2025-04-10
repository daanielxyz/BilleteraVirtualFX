package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Billetera;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.CATEGORIA;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Transaccion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TransferenciaController implements Initializable {


    @FXML private TextField campoCuentaATransferir;
    @FXML private TextField campoCantidadATransferir;
    @FXML private ComboBox<CATEGORIA> categoriaComboBox;
    @FXML private Button Transferir;
    @FXML private Button Cancelar;


    private Banco banco = Banco.getInstancia();
    private Billetera billeteraOrigen;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        categoriaComboBox.setItems(FXCollections.observableArrayList(CATEGORIA.values()));
    }


    public void inicializarBilletera(Billetera billeteraOrigen) {
        this.billeteraOrigen = billeteraOrigen;
    }


    @FXML
    public void realizarTransferenciaFX() {
        try {

            String numCuentaDestino = campoCuentaATransferir.getText();
            String cantidadStr = campoCantidadATransferir.getText();
            CATEGORIA categoria = categoriaComboBox.getValue();

            if (numCuentaDestino.isEmpty() || cantidadStr.isEmpty() || categoria == null) {
                Alertas.mostrarAlertaError("Error", "Todos los campos son obligatorios.");
                return;
            }


            double cantidad;
            try {
                cantidad = Double.parseDouble(cantidadStr);
                if (cantidad <= 0) {
                    throw new NumberFormatException("La cantidad debe ser positiva.");
                }
            } catch (NumberFormatException e) {
                Alertas.mostrarAlertaError("Error", "Ingrese una cantidad válida.");
                return;
            }


            Billetera billeteraDestino = banco.obtenerBilleteraNumCuenta(numCuentaDestino);


            if (billeteraOrigen.getNumTarjeta().equals(billeteraDestino.getNumTarjeta())) {
                Alertas.mostrarAlertaError("Error", "No puedes transferir a tu propia cuenta.");
                return;
            }


            Transaccion transaccion = billeteraOrigen.realizarTransaccion(cantidad, categoria, billeteraOrigen, billeteraDestino);


            Alertas.mostrarAlertaInfo("Éxito", "Transferencia de " + cantidad + " realizada con éxito a " + billeteraDestino.getPropietario().getNombre());
            cerrarVentana();

        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error al realizar transferencia", e.getMessage());
        }
    }


    @FXML
    public void cancelarTransaccionFX() {
        cerrarVentana();
    }


    private void cerrarVentana() {
        Stage stage = (Stage) campoCuentaATransferir.getScene().getWindow();
        stage.close();
    }
}