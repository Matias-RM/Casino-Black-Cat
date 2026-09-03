import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static final int CANTIDAD_NUMEROS = 37;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    /**
     * Método principal: inicia el programa llamando al menú.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Controla el flujo principal del programa mostrando
     * un menú en consola.
     */
    public static void menu() {
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
    public static void mostrarMenu() {
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
    public static int leerOpcion(Scanner in) {
        return in.nextInt();
    }

    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     *
     * @param opcion opción elegida por el usuario.
     * @param in Scanner para entrada por consola.
     */
    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) {
            iniciarRonda(in);
        } else if (opcion == 2) {
            mostrarEstadisticas();
        } else if (opcion == 3) {
        }
    }

    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     * @param in Scanner para entrada por consola.
     */
    public static void iniciarRonda(Scanner in) {
        if (historialSize >= MAX_HISTORIAL) {
            System.out.println("Error: Se ha alcanzado el límite máximo de historial.");
            return;
        }

        char tipoApuesta = leerTipoApuesta(in);
        System.out.print("Cantidad a apostar: ");
        int monto = in.nextInt();
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipoApuesta);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipoApuesta, monto, acierto);
    }

    /**
     * Permite al usuario seleccionar el tipo de apuesta
     * (R/N/P/I).
     *
     * @param in Scanner para entrada por consola.
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        System.out.print("Seleccione tipo de apuesta (R=Rojo, N=Negro, P=Par, I=Impar): ");
        return in.next().charAt(0);
    }

    /**
     * Simula el giro de la ruleta generando un número
     * aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
        return rng.nextInt(CANTIDAD_NUMEROS);
    }

    /**
     * Evalúa si la apuesta realizada por el jugador
     * fue acertada.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    public static boolean evaluarResultado(int numero, char tipo) {
        if (tipo == 'R') {
            return esRojo(numero);
        } else if (tipo == 'N') {
            return !esRojo(numero) && numero != 0;
        } else if (tipo == 'P') {
            return numero != 0 && numero % 2 == 0;
        } else { // tipo == 'I'
            return numero % 2 != 0;
        }
    }

    /**
     * Determina si un número corresponde a color rojo.
     *
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public static boolean esRojo(int n) {
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
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    /**
     * Muestra en consola el resultado de la ronda.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("\n--- RESULTADO DE LA RONDA ---");
        System.out.println("Número obtenido: " + numero);

        if (esRojo(numero)) {
            System.out.println("Color: Rojo");
        } else if (numero == 0) {
            System.out.println("Color: Verde");
        } else {
            System.out.println("Color: Negro");
        }

        if (tipo == 'R') {
            System.out.println("Tipo de apuesta: Rojo");
        } else if (tipo == 'N') {
            System.out.println("Tipo de apuesta: Negro");
        } else if (tipo == 'P') {
            System.out.println("Tipo de apuesta: Par");
        } else { // tipo == 'I'
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
    public static void mostrarEstadisticas() {
        System.out.println("\n ESTADÍSTICAS DEL JUEGO ");

        if (historialSize == 0) {
            System.out.println("No hay rondas jugadas aún.");
            return;
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