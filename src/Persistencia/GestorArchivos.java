package Persistencia;

import Modelo.Resultado;
import Modelo.Usuario;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class GestorArchivos {
    private static final Path RUTA_DIRECTORIO_DATOS = Paths.get("src\\Datos");
    private static final Path RUTA_USUARIOS = Paths.get("src\\Datos\\usuarios.csv");
    private static final Path RUTA_RESULTADOS = Paths.get("src\\Datos\\resultados.csv");

    public GestorArchivos(){
        verificarExistenciaDirectorio();
    }

    public List<Usuario> cargarUsuarios(List<Usuario> usuarios) {
        List<String> lineasLeidas;
        try {
            lineasLeidas = Files.readAllLines(RUTA_USUARIOS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String linea : lineasLeidas) {
            String[] datos = linea.split(";");
            String nombre = datos[0];
            String nombreUsuario = datos[1];
            int saldo = Integer.parseInt(datos[2]);
            String password = datos[3];
            Usuario usuario = new Usuario(nombreUsuario, password, nombre, saldo);
            usuarios.add(usuario);
        }
        return usuarios;
    }
    public void cargarResultados(List<Usuario> usuarios) {
        List<String> lineasLeidas = null;
        try {
            lineasLeidas = Files.readAllLines(RUTA_RESULTADOS);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
        for (String linea : lineasLeidas) {
            String[] datos = linea.split(";");
            String nombre = datos[0];
            boolean esGanador = Boolean.parseBoolean(datos[1]);
            int numero = Integer.parseInt(datos[2]);
            String tipoApuesta = datos[3];
            int montoApostado = Integer.parseInt(datos[4]);
            int montoGanado = Integer.parseInt(datos[5]);

            Resultado resultado = new Resultado(numero, tipoApuesta, montoApostado, esGanador,  montoGanado);
            for (Usuario usuario: usuarios) {
                if (usuario.getUsername().equals(nombre)) {
                    usuario.agregarResultados(resultado);
                    break;
                }
            }
        }
    }

    public void guardarUsuarios(List<Usuario> usuarios) {
    String nombreUsuarioAnterior = "";
    List<String> lineas = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (!nombreUsuarioAnterior.equals(usuario.getUsername())) {
                lineas.add(usuario.getNombre() + ";" + usuario.getUsername() + ";" + usuario.getSaldo() + ";" + usuario.getPassword());
            }
            nombreUsuarioAnterior = usuario.getUsername();
        }
        try {
            Files.write(RUTA_USUARIOS, lineas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarPerfilUsuario(String usernameNuevo, String usernameAntiguo) {

        if (!usernameAntiguo.equals(usernameNuevo)) {
            actualizarUsernameEnHistorial(usernameAntiguo, usernameNuevo);
        }
    }

    private void actualizarUsernameEnHistorial(String usernameAntiguo, String usernameNuevo) {
        try {
            List<String> lineasResultados = Files.readAllLines(RUTA_RESULTADOS);
            List<String> lineasActualizadas = new ArrayList<>();

            for (String linea : lineasResultados) {
                String[] datos = linea.split(";");
                if (datos[0].equals(usernameAntiguo)) {
                    datos[0] = usernameNuevo;
                    lineasActualizadas.add(String.join(";", datos));
                } else {
                    lineasActualizadas.add(linea);
                }
            }
            Files.write(RUTA_RESULTADOS, lineasActualizadas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarResultados(String nombreUsuario,  Resultado resultado) {
        String linea = nombreUsuario + ";" + resultado.EsGanador() + ";" + resultado.getNumero() + ";" + resultado.getTipoApuesta() + ";" + resultado.getMontoApostado() + ";" + resultado.getMontoGanado();

        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_RESULTADOS.toFile(), true))) {
            writer.println(linea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void verificarExistenciaDirectorio() {
        try {
            if (!Files.exists(RUTA_DIRECTORIO_DATOS)) {
                Files.createDirectory(RUTA_DIRECTORIO_DATOS);
                Files.createFile(RUTA_USUARIOS);
                Files.createFile(RUTA_RESULTADOS);
            }
        } catch (IOException e) {}
    }
}

