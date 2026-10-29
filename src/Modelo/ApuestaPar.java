package Modelo;

public class ApuestaPar extends ApuestaBase {
    public ApuestaPar(int montoApuesta, String color) {
        super(montoApuesta, color);
    }
    @Override
    public boolean acierta(int numero, String color) {
        return numero % 2 == 0 && numero != 0;
    }
}
