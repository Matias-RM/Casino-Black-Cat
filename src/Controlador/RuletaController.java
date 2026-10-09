package Controlador;
import Modelo.Resultado;
import Modelo.LogicaRuleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {

    private LogicaRuleta ruleta;
    private SessionController sessionController;
    private Controlador.ResultadoController resultadoController;

    public RuletaController(SessionController sessionController, Controlador.ResultadoController resultadoController) {
            this.ruleta = new LogicaRuleta();
            this.sessionController = sessionController;
            this.resultadoController = resultadoController;
        }
        public int getRandNumb() {
        return ruleta.girarRuleta();
        }

        public Resultado realizarApuesta(TipoApuesta tipo, int monto) {
            Usuario usuario = sessionController.getUsuarioActual();

            if (monto <= 0) {
                throw new IllegalArgumentException("El monto apostado debe ser mayor a 0");
            }
            if (usuario.getSaldo() < monto) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar esta apuesta");
            }

            int numeroObtenido = getRandNumb();
            boolean gano = ruleta.evaluarResultado(numeroObtenido, tipo);
            int ganancia = gano ? monto * 2 : 0;

            if (gano) {
                usuario.depositar(monto);
            } else {
                usuario.quitarSaldo(monto);
            }

            Resultado resultado = new Resultado(numeroObtenido, tipo, monto, gano, ganancia);
            resultadoController.registrarResultado(resultado);

            return resultado;
        }
    }
