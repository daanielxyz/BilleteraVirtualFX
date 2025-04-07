package co.edu.uniquindio.poo.billeteravirtualfx.modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import lombok.*;



@Data
@RequiredArgsConstructor
public class Billetera {
    //ATRIBUTOS
        @NonNull private double saldo;
        @NonNull private Usuario propietario;
        String numTarjeta = crearNumeroUnicoBilletera();
        private ArrayList<Transaccion> transacciones = new ArrayList<>();
        private static final float COSTO = 200;

    //METODO PARA CREAR UN NUMERO UNICO DE BILLETERA
        public String crearNumeroUnicoBilletera() {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }










    public Transaccion realizarTransaccion(Banco banco, float saldoTransferir, CATEGORIA categoria, Billetera origen, Billetera destino) throws Exception {

        ArrayList<Billetera> billeteras = banco.getListaBilleteras();
        boolean origenValido = false;
        boolean destinoValido = false;
        boolean transaccionValida = false;
        if (saldoTransferir + COSTO > saldo) {
            throw new Exception("No hay saldo suficiente en la billetera ");
        } else if (saldoTransferir <= 0) {
            throw new Exception("No se permite transferir un saldo menor a cero");
        }
        while (!transaccionValida) {
            for (Billetera billetera : billeteras) {
                if (billetera.getNumTarjeta().equals(origen.getNumTarjeta())) {
                    origenValido = true;
                } else if (billetera.getNumTarjeta().equals(destino.getNumTarjeta())) {
                    destinoValido = true;
                }
            }
            if (origenValido && destinoValido) {
                transaccionValida = true;
            } else {
                throw new Exception("La billetera destino o billetera origen no estan registradas en el banco");
            }
        }


        Transaccion transaccion = new Transaccion(LocalDateTime.now(), categoria, destino, origen, saldoTransferir);

        origen.restarSaldo(saldoTransferir, origen);
        destino.sumarMonto(saldoTransferir, destino);

        origen.agregarTransaccion(transaccion);

        return transaccion;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public void sumarMonto(double saldoTransferencia, Billetera billetera) {
        double nuevoSaldoDestino = saldoTransferencia + billetera.getSaldo();
        billetera.setSaldo(nuevoSaldoDestino);
    }

    public void restarSaldo(double saldoTransferencia, Billetera billetera) {
        double nuevoSaldoOrigen = billetera.getSaldo() - saldoTransferencia - COSTO;
        billetera.setSaldo(nuevoSaldoOrigen);
    }

    public void recargarBilletera(double saldoARecargar) throws Exception {
        boolean recargaValida = false;
        while (!recargaValida) {
            if (saldoARecargar <= 0) {
                throw new Exception("Seleccione un saldo correcto para recargar!");
            } else {
                recargaValida = true;
            }
        }
        if (recargaValida) {
            double saldoNuevo = saldo + saldoARecargar;
            setSaldo(saldoNuevo);
        }
    }

    //METODO CONSULTAR TRANSACCIONES DADO TIEMPO
    public ArrayList<Transaccion> consultarTransaccionesTiempo(LocalDateTime fechaInicio, LocalDateTime fechaFinal) throws Exception {
        ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
        if (fechaInicio.isAfter(fechaFinal)) {
            throw new Exception("La segunda fecha tiene que ser despues de la primera");
        }
        boolean tiempoValido = false;
        while (!tiempoValido) {
            for (Transaccion transaccion : transacciones) {
                if (transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)) {
                    listaTransacciones.add(transaccion);
                    tiempoValido = true;
                } else {
                    throw new Exception("No hay transacciones realizadas por esta billetera en ese intervalo de tiempo.");
                }
            }
        }
        return listaTransacciones;
    }

    //METODO PARA OBTENER EL PORCENTAJE DE GASTOS
    public double porcentajeGastos(Billetera billetera, LocalDateTime fechaInicio, LocalDateTime fechaFinal, CATEGORIA categoria) throws Exception {
        double gastosTotales = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal) && transaccion.getCategoria().equals(categoria)){
                gastosTotales += (transaccion.getMonto());
            }
        }
        return Math.round((gastosTotales / billetera.getSaldo()) * 100 * 1.0) / 1.0;
    }


    //METODO PARA OBTENER EL PORCENTAJE DE INGRESOS
    public double porcentajeIngresos(Billetera billetera, LocalDateTime fechaInicio, LocalDateTime fechaFinal, Banco banco, CATEGORIA categoria) throws Exception{
        double ingresosTotales = 0;
        ArrayList<Transaccion> transacciones = banco.getListaTransacciones();
        for(Transaccion transaccion : transacciones) {
            if(transaccion.getDestinatario().equals(billetera) && transaccion.getCategoria().equals(categoria) && transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)){
                ingresosTotales+= transaccion.getMonto();
            }
        }
        return (ingresosTotales/ billetera.getSaldo()) * 100;
    }
}
