package Modelo;

public abstract class ApuestaBase {
    private int montoApuesta;
    private String tipoApuesta;

    public ApuestaBase(int montoApuesta, String tipoApuesta) {
        this.montoApuesta = montoApuesta;
        this.tipoApuesta = tipoApuesta;
    }
    public abstract boolean acierta(int numero, String color);
    public int getMontoApuesta() {return montoApuesta;}
    public String getTipoApuesta() {return tipoApuesta;}
}
