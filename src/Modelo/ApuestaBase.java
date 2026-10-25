package Modelo;

public abstract class ApuestaBase {
    protected int montoApuesta;
    protected String tipoApuesta;

    public ApuestaBase(int montoApuesta, String tipoApuesta) {
        this.montoApuesta = montoApuesta;
        this.tipoApuesta = tipoApuesta;
    }
    public abstract boolean acierta(int numero, String color);
}
