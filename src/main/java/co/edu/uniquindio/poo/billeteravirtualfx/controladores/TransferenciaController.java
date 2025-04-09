package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Billetera;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.CATEGORIA;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class TransferenciaController {


    //INSTANCIAS
        ObservableList<CATEGORIA> categoriasTransferencia = FXCollections.observableArrayList(CATEGORIA.values());
        private Usuario usuarioActual;
        private Billetera billeteraActual;
        private Billetera billeteraOrigen;
        private Runnable callbackActualizarTabla;
        @FXML private ComboBox<CATEGORIA> categoriaComboBox;
        @FXML private TextField campoCuentaATransferir;
        @FXML private TextField campoCantidadATransferir;
        public Button Transferir;
        public Button Cancelar;

    //INCIALIZACION DE DATOS
        public void inicializarUsuario(Usuario usuario, Runnable callback) {
            this.usuarioActual = usuario;
            this.billeteraActual = Banco.getInstancia().obtenerBilleteraDeUsuario(usuario);
            this.callbackActualizarTabla = callback;
            categoriaComboBox.setItems(categoriasTransferencia);

        }

    //METODOS
        /*@FXML
        private void realizarTransferenciaFX(){
            try {
                float monto = Float.parseFloat(campoCantidadATransferir.getText());
                this.billeteraOrigen = Banco.getInstancia().obtenerBilleteraNumCuenta(campoCuentaATransferir.getText());
                billeteraActual.realizarTransaccion(monto, categoriaComboBox.getValue(), billeteraActual, billeteraOrigen);
            } catch (Exception e){
                Alertas.mostrarAlertaError("Error",  e.getMessage());
            }
        }*/
    @FXML
    private void realizarTransferenciaFX() {
        try {
            double monto = Double.parseDouble(campoCantidadATransferir.getText());
            this.billeteraOrigen = Banco.getInstancia().obtenerBilleteraNumCuenta(campoCuentaATransferir.getText());
            System.out.println("Saldo actual de billeteraActual: " + billeteraActual.getSaldo());
            System.out.println("Monto a transferir: " + monto);
            billeteraActual.realizarTransaccion(monto, categoriaComboBox.getValue(), billeteraActual, billeteraOrigen);
            Alertas.mostrarAlertaInfo("Transaccion exitosa!", +monto+"$ transferidos a "+billeteraOrigen.getPropietario().getNombre());
            cancelarTransaccionFX();
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error", e.getMessage());
        }
    }
        @FXML
        private void cancelarTransaccionFX(){
            Stage stage = (Stage) categoriaComboBox.getScene().getWindow();
            stage.close();
        }


}
