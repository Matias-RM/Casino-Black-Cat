import java.util.Random;
import java.util.Scanner;

public class Ruleta01 {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36

    };
    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        while (true) {
            mostrarMenu();
            Scanner entrada = new Scanner(System.in);
            int eleccion = leerOpcion(entrada);
            ejecutarOpcion(eleccion, );
        }

    }
    public static void mostrarMenu() {
        System.out.println("Menú");
        System.out.println("Elige que deseas hacer");
        System.out.println("1. Par | 2. Impar | 3. Rojo | 4. Negro | 0. Salir");
    }
    public static int leerOpcion(Scanner in) {
    return in.nextInt();

    }
    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 0) {
            System.exit(0);
        }
        else if (opcion == 1) {
            iniciarRonda(in);


        }
    }
    public static void iniciarRonda(Scanner in) {

    }

    }