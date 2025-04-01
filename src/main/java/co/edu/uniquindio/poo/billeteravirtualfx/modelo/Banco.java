package co.edu.uniquindio.poo.billeteravirtualfx.modelo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.*;

@Getter
@Setter
@Data
@RequiredArgsConstructor
public class Banco {
    @NonNull private String nombre;
    @NonNull private ArrayList<Usuario> usuarios;
    @NonNull private ArrayList<Transaccion> transacciones;
    @NonNull private ArrayList<Billetera> billeteras;
}
