package co.edu.uniquindio.poo.billeteravirtualfx.modelo;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import lombok.*;


@Getter
@Setter
@Data
@RequiredArgsConstructor
public class Usuario {
    @NonNull private String nombre;
    @NonNull private String direccion;
    @NonNull private String id;
    @NonNull private String correo;
    @NonNull private String contrasena;
}
