package Controlador;
import Modelo.Resultado;
import Modelo.Usuario;
import Persistencia.GestorArchivos;

import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    // --- Lista dinámica de usuarios ---
    private List<Usuario> USUARIOS = new ArrayList<>();
    private GestorArchivos gestorArchivos = new GestorArchivos();

    public SessionController() {
        this.usuarioActual = null;
        this.USUARIOS = gestorArchivos.cargarUsuarios(USUARIOS);
    }
    private boolean verificarUsuarioUnico(String nombreUsuario) {
        for (Object usuario: USUARIOS) {
            if (((Usuario) usuario).getNombre().equals(nombreUsuario)) {
                return false;
            }
        }
        return true;
    }

    // 1. Iniciar sesión tradicional
    public boolean iniciarSesion(String user, String pass) {
        for (Usuario usuarios : USUARIOS) {
            if ( usuarios.validarCredencialess(user, pass)) {
                this.usuarioActual =  usuarios;
                gestorArchivos.cargarResultados(USUARIOS);
                return true;
            }
        }
        return false;
    }
    public String validarCredenciales(String nombreUsuario, String password) {
        for (Usuario usuario : USUARIOS) {
            if (usuario.validarCredencialess(nombreUsuario, password)) {
                this.usuarioActual = usuario;
                return usuario.getNombre();
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
        if (verificarUsuarioUnico(usuario)) {
            usuarioActual = new Usuario(usuario, clave, nombre, 0);
            USUARIOS.add(usuarioActual);}

        gestorArchivos.guardarUsuarios(USUARIOS);
    }

    public void agregarResultado(Resultado resultado) {
        usuarioActual.agregarResultados(resultado);
    }

    public List<Resultado> obtenerResultados() {return usuarioActual.getHistorialResultados();}
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public String getNombreUsuario() {
      return (usuarioActual !=null) ? usuarioActual.getNombre() : "Invitado";
    };
    public String getUsernameUsuario() {return usuarioActual.getUsername();}
    public List<Usuario> getUsuarios() {return USUARIOS;}
    public int getSaldoUsuario() {
        return (usuarioActual !=null) ? usuarioActual.getSaldo() : 0;
    }
    public void setUsernameUsuario(String nombre) {
        gestorArchivos.actualizarPerfilUsuario(nombre, usuarioActual.getUsername());
        this.usuarioActual.setUsername(nombre);
        gestorArchivos.guardarUsuarios(USUARIOS);
    }
    public void recargarSaldoUsuario(int monto) {
        usuarioActual.depositar(monto);
        gestorArchivos.guardarUsuarios(USUARIOS);
    }
    public void cerrarSession() {
        usuarioActual = null;
    }
}
