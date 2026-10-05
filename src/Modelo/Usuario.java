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
        this.saldo = 0;
    }
    // Constructor con parámetros.
    public Usuario(String username, String password, String nombre, int saldoInicial) {
        this.username = username;
        this.password = password;
        setNombre(nombre);
        this.id = contador++;
        this.saldo = Math.max(saldoInicial, 0);
    }
    // Verifica si las credenciales ingresadas pertenecen al usuario
    public boolean validarCredencialess(String nombreUsuario, String contraseña) {
        return this.username.equals(nombreUsuario) && this.password.equals(contraseña);
    }


    public void setNombre(String nombre) {
        if (nombre == null||nombre.equals("")) {
            throw  new IllegalArgumentException("El nombre es obligatorio");
        };
        this.nombre = nombre;
    }

    public void depositar(int monto) {
        if (monto<=0) {
            throw  new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        this.saldo += monto;
        }
    public String getNombre() {
        return nombre;
    }
    public int getSaldo() {
        return saldo;
    }
}