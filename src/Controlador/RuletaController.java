package Controlador;
import Modelo.*;

public class RuletaController {

    private LogicaRuleta ruleta;
    private Usuario usuario;

    public RuletaController(Usuario usuario) {
            this.ruleta = new LogicaRuleta();
            this.usuario = usuario;
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
        }
    }
}
