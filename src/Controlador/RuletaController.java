package Controlador;
import Modelo.Resultado;
import Modelo.LogicaRuleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class RuletaController {

    private LogicaRuleta ruleta;
    private SessionController session;

    public RuletaController(SessionController sessionController) {
            this.ruleta = new LogicaRuleta();
            this.session = sessionController;
        }
        public int getRandNumb() {
        return ruleta.girarRuleta();
        }

        public Resultado realizarApuesta(TipoApuesta tipo, int monto) {
            Usuario usuario = session.getUsuarioActual();
            ResultadoController resultadoController = new ResultadoController(session);
            usuario.quitarSaldo(monto);
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto apostado debe ser mayor a 0");
            }
            if (usuario.getSaldo() < monto) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar esta apuesta");
            }

            int numeroObtenido = getRandNumb();
            boolean gano = resultadoController.evaluarResultado(numeroObtenido, tipo);

            if (!gano) {
                int ganancia = 0;
                Resultado resultado = new Resultado(numeroObtenido, tipo, monto, gano, ganancia);
                resultadoController.registrarResultado(resultado);
                return resultado;
            } else {
                int ganancia = monto * 2;
                usuario.depositar(ganancia);
                Resultado resultado = new Resultado(numeroObtenido, tipo, monto, gano, ganancia);
                resultadoController.registrarResultado(resultado);
                return resultado;


            }
        }
    }
