package Modelo;

import java.util.Random;
import java.util.Scanner;

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
    /**
     * Método principal: inicia el programa llamando al menú.
     */
    public void main(String[] args) {
        menu();
    }

    /**
     * Controla el flujo principal del programa mostrando
     * un menú en consola.
     */

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
    private void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);

        in.close();
        System.out.println("¡Gracias por jugar en el Casino Black Cat!");
    }
    /**
     * Muestra en consola las opciones disponibles del menú.
     */
    private void mostrarMenu() {
        System.out.println("\n=== CASINO BLACK CAT - RULETA ===");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee la opción elegida por el usuario desde teclado.
     *
     * @param in Scanner para entrada por consola.
     * @return número de opción ingresado.
     */
    private static int leerOpcion(Scanner in) {
        return in.nextInt();
    }

    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     *
     * @param opcion opción elegida por el usuario.
     * @param in Scanner para entrada por consola.
     */
    private void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) {
            //iniciarRonda(in);
        } else if (opcion == 2) {
            mostrarEstadisticas();
        } else if (opcion == 3) {
        }
    }

    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     */
    public void iniciarRonda(String tipoApuesta,String color, String paridad , int monto) {
        if (historialSize >= MAX_HISTORIAL) {
            return;
        }
        int numero = randNum;
        boolean acierto = evaluarResultado(numero, tipoApuesta, color, paridad);
        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipoApuesta, monto, acierto);
    }

    /**
     * Simula el giro de la ruleta generando un número
     * aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    private int girarRuleta() {
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
    public boolean evaluarResultado(int numero, String tipoApuesta, String color, String paridad) {

        if (tipoApuesta.equals("Color")) {
            if (color.equals("Rojo")) {
                return esRojo(numero);
            } else {// Negro
                return !esRojo(numero) && numero != 0;
            }
        }
        if (tipoApuesta.equals("Paridad")) {
            if (paridad.equals("Par")) {
                return numero != 0 && numero % 2 == 0;
            } else {// Impar
                return numero % 2 != 0;
            }
        }
        return false;
    }

    /**
     * Determina si un número corresponde a color rojo.
     *
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    private boolean esRojo(int n) {
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

    /**
     * Muestra en consola el resultado de la ronda.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipoApuesta tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    private void mostrarResultado(int numero, String tipoApuesta, int monto, boolean acierto) {
        System.out.println("\n--- RESULTADO DE LA RONDA ---");
        System.out.println("Número obtenido: " + numero);

        if (esRojo(numero)) {
            System.out.println("Color: Rojo");
        } else if (numero == 0) {
            System.out.println("Color: Verde");
        } else {
            System.out.println("Color: Negro");
        }

        if (tipoApuesta == "Rojo") {
            System.out.println("Tipo de apuesta: Rojo");
        } else { // Negro
            System.out.println("Tipo de apuesta: Negro");
        }
        if (tipoApuesta == "Par") {
            System.out.println("Tipo de apuesta: Par");
        } else { // Impar
            System.out.println("Tipo de apuesta: Impar");
        }

        System.out.println("Monto apostado: $" + monto);

        if (acierto) {
            int ganancia = monto * 2;
            System.out.println("¡Felicidades! Has ganado $" + ganancia);
        } else {
            System.out.println("Has perdido $" + monto);
        }
    }

    /**
     * Muestra estadísticas generales de todas las
     * rondas jugadas.
     */
    private void mostrarEstadisticas() {
        System.out.println("\n ESTADÍSTICAS DEL JUEGO ");

        if (historialSize == 0) {
            System.out.println("No hay rondas jugadas aún.");
            return ;
        }

        System.out.println("Rondas jugadas: " + historialSize);

        int totalApuestas = 0;
        for (int i = 0; i < historialSize; i++) {
            totalApuestas = totalApuestas + historialApuestas[i];
        }
        System.out.println("Monto total apostado: $" + totalApuestas);

        int totalAciertos = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                totalAciertos = totalAciertos + 1;
            }
        }
        System.out.println("Total de aciertos: " + totalAciertos);

        double porcentajeAciertos = (double) totalAciertos / historialSize * 100;
        System.out.println("Porcentaje de aciertos: " + porcentajeAciertos + "%");

        int gananciaNeta = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                gananciaNeta = gananciaNeta + historialApuestas[i];
            } else {
                gananciaNeta = gananciaNeta - historialApuestas[i];
            }
        }
        System.out.println("Ganancia/Pérdida neta: $" + gananciaNeta);
    }

}