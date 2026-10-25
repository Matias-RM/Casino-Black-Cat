package Modelo;
public class ApuestaImpar extends ApuestaBase {
    private int montoApuesta;
    private String tipoApuesta;

    public ApuestaImpar(int montoApuesta, String tipoApuesta) {
        super(montoApuesta, tipoApuesta);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero % 2 != 0;
    }
}
