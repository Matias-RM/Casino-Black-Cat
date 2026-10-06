package Controlador;
import Modelo.Usuario;
public class SessionController {
    private Usuario usuarioActual;

    public boolean iniciarSesion(String usuario, String clave) {
        if (usuarioActual != null && usuarioActual.validarCredencialess(usuario, clave)) {
            return true;
        }
        return false;
    }
    public void registrarUsuario(String usuario, String clave, String nombre) {
        this.usuarioActual = new Usuario(usuario, clave, nombre, 0);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public String getNombreUsuario() {
      return (usuarioActual !=null) ? usuarioActual.getNombre() : "Invitado";
    };
    public int getSaldoUsuario() {
        return (usuarioActual !=null) ? usuarioActual.getSaldo() : 0;
    }
    public void cerrarSession() {
        usuarioActual = null;
    }
}
