package co.edu.uniquindio.poo.billeteravirtualfx.modelo;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import lombok.*;



@Data
@RequiredArgsConstructor
public class Transaccion {
    @NonNull private LocalDateTime fecha;
    @NonNull private CATEGORIA categoria;
    @NonNull private Billetera destinatario;
    @NonNull private Billetera origen;
    @NonNull private float monto;
    private UUID identificador;
}
