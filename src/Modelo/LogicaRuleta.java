package Modelo;

import java.util.Random;

public class LogicaRuleta {
    private static final int MAX_HISTORIAL = 100;
    private static final int CANTIDAD_NUMEROS = 37;
    private static Random rng = new Random();
    private static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };
    private final int randNum = girarRuleta();
    public int getRandomNum() {
        return randNum;
    }

    /**
     * Simula el giro de la ruleta generando un número
     * aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    public int girarRuleta() {
        return rng.nextInt(CANTIDAD_NUMEROS);
    }

    /**
     * Determina si un número corresponde a color rojo.
     *
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public String esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i++) {
            if (numerosRojos[i] == n) {
                return "Rojo";
            }
        }
        return "Negro";
    }

    /**
     *
     * @param usuario Usuario actual en el juego.
     * @param apuesta Apuesta seleccionada.
     * @return Resultado de la apuesta realizada.
     */
    public Resultado realizarApuesta(Usuario usuario, ApuestaBase apuesta) {

        if (apuesta.getMontoApuesta() <= 0) {
            throw new IllegalArgumentException("El monto apostado debe ser mayor a 0");
        }
        if (usuario.getSaldo() < apuesta.getMontoApuesta()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar esta apuesta");
        }

        usuario.quitarSaldo(apuesta.getMontoApuesta());

        int numeroObtenido = girarRuleta();
        boolean gano = apuesta.acierta(numeroObtenido, esRojo(numeroObtenido));

        if (!gano) {
            int ganancia = 0;
            return new Resultado(numeroObtenido, apuesta.getTipoApuesta(), apuesta.getMontoApuesta(), gano, ganancia);
        } else {
            int ganancia = apuesta.getMontoApuesta() * 2;
            usuario.depositar(ganancia);
            return new Resultado(numeroObtenido, apuesta.getTipoApuesta(), apuesta.getMontoApuesta(), gano, ganancia);
        }
    }
}