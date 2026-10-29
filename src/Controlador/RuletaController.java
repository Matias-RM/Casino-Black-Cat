package Controlador;
import Modelo.*;
import Persistencia.GestorArchivos;
import java.util.List;

public class RuletaController {

    private final List<Usuario> usuarios;
    private LogicaRuleta ruleta;
    private Usuario usuario;
    private GestorArchivos gestorArchivos;

    public RuletaController(Usuario usuario, List<Usuario> usuarios) {
            this.ruleta = new LogicaRuleta();
            this.usuario = usuario;
            this.usuarios = usuarios;
            this.gestorArchivos = new GestorArchivos();
        }
        public int getRandNumb() {
        return ruleta.girarRuleta();
        }

        public void realizarApuesta(int monto, String tipoApuesta) {
        ApuestaBase apuesta = null;
            switch (tipoApuesta) {
                case "Rojo"  -> apuesta = new ApuestaRojo(monto, "Rojo");
                case "Negro" -> apuesta = new ApuestaNegro(monto, "Negro");
                case "Par"   -> apuesta = new ApuestaPar(monto, "Par");
                case "Impar" -> apuesta = new ApuestaImpar(monto, "Impar");
            }
            if  (apuesta != null) {
                Resultado resultado = ruleta.realizarApuesta(usuario, apuesta);
                usuario.agregarResultados(resultado);
                gestorArchivos.guardarUsuarios(usuarios);
                gestorArchivos.guardarResultados(usuario.getUsername(), resultado);
        }
    }
}
