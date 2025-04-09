package co.edu.uniquindio.poo.billeteravirtualfx.controladores;
import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.helpers.NavegadorVentanas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Banco;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Billetera;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Transaccion;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;

import java.awt.*;

public class PanelPrincipalController {

    //INSTANCIAS DE CODIGO
        private Usuario usuarioActual;
        private Billetera billeteraActual;
        private ObservableList<Transaccion> listaTransacciones;

    //INSTANCIAS VISUALES
        @FXML private Label txtNumeroCuenta;
        @FXML private Label txtBienvenidaNombre;
        @FXML private Label txtSaldoCuenta;
        @Getter
        @FXML private TableView<Transaccion> tablaTransacciones;
        @FXML private TableColumn<Transaccion, String> columnaTipo;
        @FXML private TableColumn<Transaccion, String> columnaFecha;
        @FXML private TableColumn<Transaccion, String> columnaValor;
        @FXML private TableColumn<Transaccion, String> columnaUsuario;
        @FXML private TableColumn<Transaccion, String> columnaCategoria;


    //INICIALIZACION DE LOS DATOS
        public void inicializarUsuario(Usuario usuario) {
            columnaTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipoTransaccion(usuarioActual)));
            columnaFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
            columnaValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMonto())));
            columnaUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerUsuarioInvolucrado(usuarioActual).getNombre()));
            columnaCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCategoria())));
            tablaTransacciones.setItems(listaTransacciones);
            this.usuarioActual = usuario;
            this.billeteraActual = Banco.getInstancia().obtenerBilleteraDeUsuario(usuario);
            this.listaTransacciones  = FXCollections.observableArrayList(Banco.getInstancia().getListaTransacciones());


            System.out.println("Sesión iniciada con: " + usuario.getNombre());
            txtBienvenidaNombre.setText("Bienvenido "+usuario.getNombre()+ ", aqui se mostrarán todos sus movimientos.");
            txtNumeroCuenta.setText("Numero de cuenta: " +billeteraActual.getNumTarjeta()+ ".");
            txtSaldoCuenta.setText("Saldo disponible: " +billeteraActual.getSaldo()+ ".");
        }


    //METODOS DEL CONTROLADOR
        @FXML
        public void cerrarSesionFX(){
            Stage stage = (Stage) tablaTransacciones.getScene().getWindow();
            stage.close();
        }
        @FXML
        public void hacerTransferenciaFX(){

            NavegadorVentanas.navegarVentanaTransferenciaConUsuario("/Transferencia.fxml", "Realizar Transferencia", usuarioActual);
        }
        @FXML
        public void hacerConsultaFX(){

        }
        @FXML
        public void editarDatosFX(){

        }
}
