package co.edu.uniquindio.poo.billeteravirtualfx.modelo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Getter
@Setter
@RequiredArgsConstructor
public class Banco {
    //ATRIBUTOS
            @NonNull private String nombre;
            private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
            private ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            private ArrayList<Billetera> listaBilleteras = new ArrayList<>();

    //SINGLETON
            public static Banco INSTANCIA;
            public static Banco getInstancia(){
                if(INSTANCIA == null){
                    INSTANCIA = new Banco("Banco UQ");
                }
                return INSTANCIA;
            }

    //METODOS DEL BANCO
        //VALIDAR USUARIO
            public void validarUsuario(Usuario usuario) throws Exception{
                if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
                    throw new Exception("El nombre no puede estar vacío.");
                } else if (usuario.getDireccion() == null || usuario.getDireccion().trim().isEmpty()) {
                    throw new Exception("La direccion no puede estar vacía.");
                } else if (usuario.getId() == null || usuario.getId().trim().isEmpty()) {
                    throw new Exception("La identificación no puede estar vacía.");
                } else if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
                    throw new Exception("El correo no puede estar vacío.");
                } else if (usuario.getContraseña() == null || usuario.getContraseña().trim().isEmpty()) {
                    throw new Exception("La contraseña no puede estar vacía.");
                }  else if (!usuario.getId().matches("\\d+")) {
                    throw new Exception("El ID no puede contener caracteres.");
                } else if (listaUsuarios.stream().anyMatch(u -> u.getId().equals(usuario.getId()))){
                    throw new Exception("Ya existe un usuario registrado con este ID.");
                } else if (!usuario.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new Exception("Ingrese un correo válido.");
                }
            }
        //REGISTRAR USUARIO
            public void registrarUsuario(Usuario usuario) throws Exception{
                validarUsuario(usuario);
                listaUsuarios.add(usuario);
                Billetera billetera = new Billetera(1000, usuario);
                listaBilleteras.add(billetera);
            }
        //INICIAR SESION
        public void iniciarSesion(Usuario usuario) throws Exception {
            for (Usuario u : listaUsuarios) {
                if (u.getId().equals(usuario.getId()) && u.getContraseña().equals(usuario.getContraseña())) {
                    return;
                }
            }
            throw new Exception("ID/Contraseña incorrectos.");
        }
        //ENCONTRAR USUARIO POR ID Y CONTRASEÑA
            public Usuario usuarioPorIdYContraseña(String id, String contraseña) throws Exception{
                for(int i=0; i<listaUsuarios.size(); i++) {
                    if (listaUsuarios.get(i).getId().equals(id) && listaUsuarios.get(i).getContraseña().equals(contraseña)) {
                        return listaUsuarios.get(i);
                    }
                }
                throw new Exception("No existe un usuario con ese id y esa contraseña");
            }
        //OBTENER BILLETERA DE UN USUARIO
            public Billetera obtenerBilleteraDeUsuario(Usuario usuario) {
                return listaBilleteras.stream()
                        .filter(b -> b.getPropietario().equals(usuario))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró una billetera asociada al usuario"));
            }
            //OBTENER BILLETERA POR NUMERO DE CUENTA
            public Billetera obtenerBilleteraNumCuenta(String numCuenta) {
                return listaBilleteras.stream()
                        .filter(b -> b.getNumTarjeta().equals(numCuenta))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró una billetera asociada a ese numero de cuenta"));
            }
        //VALIDAR SI UN USUARIO ESTA EN LA LISTA
            public boolean usuarioExiste(Usuario usuario) {
                return listaUsuarios.stream().anyMatch(u -> u.equals(usuario));
            }
        //VALIDAR SI UNA BILLETERA ESTA EN LA LISTA
            public boolean billeteraExiste(Billetera billetera) {
                return listaBilleteras.stream().anyMatch(b -> b.equals(billetera));
            }
}
