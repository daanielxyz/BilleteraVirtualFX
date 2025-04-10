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


    //METODO PARA REALIZAR UNA TRANSACCION
        public Transaccion realizarTransaccion(double saldoTransferir, CATEGORIA categoria, Billetera origen,  Billetera destino) throws Exception{

            Transaccion transaccion=new Transaccion(LocalDateTime.now(), categoria,destino, origen, (float)saldoTransferir);

            validarTransaccion(transaccion);

            origen.restarSaldo(saldoTransferir,origen);
            destino.sumarMonto(saldoTransferir,destino);

            origen.agregarTransaccion(transaccion);
            destino.agregarTransaccion(transaccion);
            Banco.getInstancia().getListaTransacciones().add(transaccion);

            return transaccion;
        }

    //METODO PARA VALIDAR UNA TRANSACCION
        public void validarTransaccion(Transaccion transaccion) throws Exception{
            if(!Banco.getInstancia().billeteraExiste(transaccion.getOrigen())){
                throw new Exception("La billetera de origen indicada no está registrada");
            } else if(!Banco.getInstancia().usuarioExiste(transaccion.getOrigen().getPropietario())){
                throw new Exception("El propietario de la billetera de origen no está registrado");
            } else if(!Banco.getInstancia().billeteraExiste(transaccion.getDestinatario())){
                throw new Exception("La billetera de destino indicada no está registrada");
            } else if(!Banco.getInstancia().usuarioExiste(transaccion.getDestinatario().getPropietario())){
                throw new Exception("El propietario de la billetera de destino no está registrado");
            } else if(transaccion.getOrigen().getSaldo() < transaccion.getMonto() + COSTO){
                throw new Exception("Fondos insuficientes, porfavor recargar la billetera");
            }
        }

    //METODO PARA AGREGAR UNA TRANSACCION A LA LISTA
        public void agregarTransaccion(Transaccion transaccion){
            transacciones.add(transaccion);
        }

    //METODO PARA SUMAR MONTO
        public void sumarMonto(double saldoTransferencia, Billetera billetera) {
            double nuevoSaldoDestino = saldoTransferencia + billetera.getSaldo();
            billetera.setSaldo(nuevoSaldoDestino);
        }
    //METODO PARA RESTAR MONTO
        public void restarSaldo(double saldoTransferencia, Billetera billetera) {
            double nuevoSaldoOrigen = billetera.getSaldo() - saldoTransferencia - COSTO;
            System.out.println("Deducting: " + saldoTransferencia + " + COSTO (" + COSTO + ") from " + billetera.getSaldo() + " = " + nuevoSaldoOrigen);
            billetera.setSaldo(nuevoSaldoOrigen);
        }

     //METODO CONSULTAR TRANSACCIONES DADO TIEMPO
     public ArrayList<Transaccion> consultarTransaccionesTiempo(LocalDateTime fechaInicio, LocalDateTime fechaFinal) throws Exception {
         if (fechaInicio.isAfter(fechaFinal)) {
             throw new Exception("La fecha final debe ser posterior a la fecha inicial.");
         }
         ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
         for (Transaccion transaccion : transacciones) {
             if (transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)) {
                 listaTransacciones.add(transaccion);
             }
         }
         return listaTransacciones;
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
