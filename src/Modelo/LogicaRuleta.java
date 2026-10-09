package Modelo;

import java.util.Random;

public class LogicaRuleta {
    private static final int MAX_HISTORIAL = 100;
    private static final int CANTIDAD_NUMEROS = 37;
    private static int[] historialNumeros = new int[MAX_HISTORIAL];
    private static int[] historialApuestas = new int[MAX_HISTORIAL];
    private static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private static int historialSize = 0;
    private static Random rng = new Random();
    private static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };
    private final int randNum = girarRuleta();
    public int getRandomNum() {
        return randNum;
    }
    public int montoAcierto(int monto,boolean acierto){
        if (acierto){
            monto = monto * 2;
            return monto;
        } else {
            return 0;
        }
    }
    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     */
    public void iniciarRonda(TipoApuesta tipoApuesta, int monto) {
        if (historialSize >= MAX_HISTORIAL) {
            return;
        }
        int numero = randNum;
        boolean acierto = evaluarResultado(numero, tipoApuesta);
        registrarResultado(numero, monto, acierto);

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
     * Evalúa si la apuesta realizada por el jugador
     * fue acertada.
     *
     * @param numero número obtenido en la ruleta.
     *
     * @return true si acertó, false si perdió.
     */
    //TODO: Agregar caso de color Verde
    public boolean evaluarResultado(int numero,TipoApuesta tipoApuesta) {
        if (numero == 0) {return false;}
        return switch (tipoApuesta) {
          case Rojo -> esRojo(numero);
          case Negro -> !esRojo(numero);
          case Par -> numero % 2 == 0;
          case Impar -> numero % 2 != 0;
        };
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

    /**
     * Registra los resultados de la ronda en los arreglos
     * de historial.
     *
     * @param numero número obtenido en la ruleta.
     * @param apuesta monto apostado.
     * @param acierto si el jugador acertó o no.
     */
    private boolean registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
            return acierto;
        }
        return acierto;
    }
}