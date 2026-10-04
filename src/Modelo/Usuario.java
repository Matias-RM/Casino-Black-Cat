package Modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int id;
    private static int contador = 0;
    private int saldo;

    public Usuario() {
        this.username = "invitado";
        this.password = "";
        this.nombre = "Invitado";
        this.id = 0;
        this.saldo = 50000;
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.id = contador++;
        this.saldo = 0;
    }
    // Verifica si las credenciales ingresadas pertenecen al usuario
    public boolean validarCredencialess(String nombreUsuario, String contraseña) {
        return this.username.equals(nombreUsuario) && this.password.equals(contraseña);
    }

    public String getNombre() {
        return nombre;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;;
    }

    public int getSaldo() {
        return saldo;
    }
}
