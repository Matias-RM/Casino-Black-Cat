package Controlador;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    // --- Lista dinámica de usuarios ---
    public static final List USUARIOS = new ArrayList<>();

    public SessionController() {
        this.usuarioActual = new Usuario("admin", "1234","Admin", 50000);
        this.USUARIOS.add(usuarioActual);

    }

    // 1. Iniciar sesión tradicional
    public boolean iniciarSesion(String user, String pass) {
        for (Object usuarios : USUARIOS) {
            if (((Usuario) usuarios).validarCredencialess(user, pass)) {
                this.usuarioActual = (Usuario) usuarios;
                return true;
            }
        }
        return false;
    }
    public String validarCredenciales(String nombreUsuario, String contraseña) {
        for (Object usuario : USUARIOS) {
            if (((Usuario) usuario).validarCredencialess(nombreUsuario, contraseña)) {
                this.usuarioActual = (Usuario) usuario;
                return ((Usuario) usuario).getNombre();
            }
        }
        return "";
    }
    public void iniciarInvitado(){
        this.usuarioActual = new Usuario();
    }

    public void registrarUsuario(String usuario, String clave, String nombre) {
        if (usuario == null || usuario.isBlank() || clave == null || clave.isBlank() ||
                nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }
        usuarioActual = new Usuario(usuario, clave, nombre, 0);
        USUARIOS.add(usuarioActual);
    }


    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public String getNombreUsuario() {
      return (usuarioActual !=null) ? usuarioActual.getNombre() : "Invitado";
    };
    public String getUsernameUsuario() {return usuarioActual.getUsername();}
    public String setUsernameUsuario(String usuario) {return this.usuarioActual.setUsername(usuario);}
    public int getSaldoUsuario() {
        return (usuarioActual !=null) ? usuarioActual.getSaldo() : 0;
    }
    public void recargarSaldoUsuario(int monto) {
        usuarioActual.depositar(monto);
    }
    public void cerrarSession() {
        usuarioActual = null;
    }
}
