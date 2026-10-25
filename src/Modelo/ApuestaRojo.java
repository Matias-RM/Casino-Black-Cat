package Modelo;

import java.util.Objects;

public class ApuestaRojo extends ApuestaBase {
    public ApuestaRojo(int montoApuesta, String color) {
        super(montoApuesta, color);
    }
    @Override
    public boolean acierta(int numero, String color) {
        return Objects.equals(color, "Rojo");
    }
}
