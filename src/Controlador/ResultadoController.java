package Controlador;

import Modelo.LogicaRuleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;
import Modelo.Usuario;

public class ResultadoController {
    private LogicaRuleta logica;
    private SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }
    //TODO: Agregar caso de color Verde
    public boolean evaluarResultado(int numero, TipoApuesta tipoApuesta) {
        LogicaRuleta logica = new LogicaRuleta();
        if (numero == 0) {return false;}
        return switch (tipoApuesta) {
            case Rojo -> logica.esRojo(numero);
            case Negro -> !logica.esRojo(numero);
            case Par -> numero % 2 == 0;
            case Impar -> numero % 2 != 0;
        };
    }
    public void registrarResultado(Usuario usuario, Resultado resultado) {
        session.getUsuarioActual().getHistorialResultados().add(resultado);
    }

    public int getVictoriasTotales() {
        int victorias = 0;
        for (Resultado usuario : session.getUsuarioActual().getHistorialResultados()) {
            if (usuario.EsGanador()) victorias++;
        }
        return victorias;
    }

    public void limpiarHistorial() {
        session.getUsuarioActual().getHistorialResultados().clear();
    }
}