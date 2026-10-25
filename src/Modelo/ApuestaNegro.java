package Modelo;

import java.util.Objects;

public class ApuestaNegro extends ApuestaBase {
    public ApuestaNegro(int montoApuesta, String color) {
        super(montoApuesta, color);
    }

    @Override
    public boolean acierta(int numero, String color) {
        return Objects.equals(color, "Negro");
    }
}
