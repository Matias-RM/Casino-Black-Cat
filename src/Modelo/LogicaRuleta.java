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
    public boolean esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i++) {
            if (numerosRojos[i] == n) {
                return true;
            }
        }
        return false;
    }
}