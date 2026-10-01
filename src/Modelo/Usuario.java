package Modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int id;
    private static int contador = 0;
    private int saldo;

    public Usuario() {
        this("Invitado", "", "Invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.id = contador++;
        this.saldo = 0;
    }
    // Verifica si las credenciales ingresadas pertenecen al usuario
    public boolean validarCredencialess(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() {
        return nombre;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public int getSaldo() {
        return saldo;
    }
}
