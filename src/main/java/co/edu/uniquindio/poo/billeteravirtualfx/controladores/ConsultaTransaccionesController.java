package co.edu.uniquindio.poo.billeteravirtualfx.controladores;

import co.edu.uniquindio.poo.billeteravirtualfx.helpers.Alertas;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Billetera;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Transaccion;
import co.edu.uniquindio.poo.billeteravirtualfx.modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ConsultaTransaccionesController implements Initializable {


    @FXML private DatePicker fechaInicioPicker;
    @FXML private DatePicker fechaFinalPicker;
    @FXML private TableView<Transaccion> tablaConsulta;
    @FXML private TableColumn<Transaccion, String> columnaTipoConsulta;
    @FXML private TableColumn<Transaccion, String> columnaFechaConsulta;
    @FXML private TableColumn<Transaccion, String> columnaValorConsulta;
    @FXML private TableColumn<Transaccion, String> columnaUsuarioConsulta;
    @FXML private TableColumn<Transaccion, String> columnaCategoriaConsulta;


    private Billetera billeteraActual;
    private Usuario usuarioActual;
    private ObservableList<Transaccion> listaConsulta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaConsulta = FXCollections.observableArrayList();
        configurarTabla();
    }


    public void inicializarDatos(Billetera billetera, Usuario usuario) {
        this.billeteraActual = billetera;
        this.usuarioActual = usuario;
    }

    private void configurarTabla() {
        columnaTipoConsulta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipoTransaccion(usuarioActual)));
        columnaFechaConsulta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        columnaValorConsulta.setCellValueFactory(cellData -> {
            Transaccion transaccion = cellData.getValue();
            double valorMostrado = transaccion.getOrigen().equals(billeteraActual) ?
                    transaccion.getMonto() + 200 :
                    transaccion.getMonto();
            return new SimpleStringProperty(String.valueOf(valorMostrado));
        });
        columnaUsuarioConsulta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerUsuarioInvolucrado(usuarioActual).getNombre()));
        columnaCategoriaConsulta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().toString()));
        tablaConsulta.setItems(listaConsulta);
    }

    @FXML
    public void consultarTransaccionesFX() {
        try {
            if (fechaInicioPicker.getValue() == null || fechaFinalPicker.getValue() == null) {
                Alertas.mostrarAlertaError("Error", "Por favor seleccione ambas fechas.");
                return;
            }
            LocalDateTime fechaInicio = fechaInicioPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fechaFinal = fechaFinalPicker.getValue().atTime(23, 59, 59).withNano(0); // Fin del día
            listaConsulta.setAll(billeteraActual.consultarTransaccionesTiempo(fechaInicio, fechaFinal));
            if (listaConsulta.isEmpty()) {
                Alertas.mostrarAlertaInfo("Sin resultados", "No se encontraron transacciones en ese rango de fechas.");
            }
        } catch (Exception e) {
            Alertas.mostrarAlertaError("Error al consultar", e.getMessage());
        }
    }

    @FXML
    public void cerrarVentanaFX() {
        Stage stage = (Stage) tablaConsulta.getScene().getWindow();
        stage.close();
    }
}
