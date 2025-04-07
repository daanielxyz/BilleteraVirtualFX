package co.edu.uniquindio.poo.billeteravirtualfx.modelo;
import lombok.NonNull;
import lombok.Builder;
import lombok.*;



@Data
@Builder
public class Usuario {
    private String nombre;
    private String direccion;
    private String id;
    private String correo;
    private String contraseña;
}
