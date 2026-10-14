package Controlador;
import Modelo.Resultado;
import Modelo.LogicaRuleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {

    private LogicaRuleta ruleta;
    private SessionController session;
    private Controlador.ResultadoController resultadoController;

    public RuletaController(SessionController sessionController, Controlador.ResultadoController resultadoController) {
            this.ruleta = new LogicaRuleta();
            this.session = sessionController;
            this.resultadoController = resultadoController;
        }
        public int getRandNumb() {
        return ruleta.girarRuleta();
        }

        public Resultado realizarApuesta(TipoApuesta tipo, int monto) {
            Usuario usuario = session.getUsuarioActual();

            if (monto <= 0) {
                throw new IllegalArgumentException("El monto apostado debe ser mayor a 0");
            }
            if (usuario.getSaldo() < monto) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar esta apuesta");
            }

            int numeroObtenido = getRandNumb();
            boolean gano = resultadoController.evaluarResultado(numeroObtenido, tipo);

            if (gano) {
                int ganancia = monto * 2;
                usuario.depositar(ganancia);
                Resultado resultado = new Resultado(numeroObtenido, tipo, monto, gano, ganancia);
                resultadoController.registrarResultado(resultado);
                return resultado;
            } else {
                int ganancia = 0;
                usuario.quitarSaldo(monto);
                Resultado resultado = new Resultado(numeroObtenido, tipo, monto, gano, ganancia);
                resultadoController.registrarResultado(resultado);
                return resultado;


            }
        }
    }
