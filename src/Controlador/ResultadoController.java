package Controlador;

import Modelo.LogicaRuleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;

import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    private LogicaRuleta logica;
    private List<Resultado> historialResultados;

    public ResultadoController() {
        this.historialResultados = new ArrayList<>();
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
    public int montoAcierto(int monto,boolean acierto){
        if (acierto){
            monto = monto * 2;
            return monto;
        } else {
            return 1;
        }
    }

    public void registrarResultado(Resultado resultado) {
        this.historialResultados.add(resultado);
    }

    public List<Resultado> getHistorial() {
        return historialResultados;
    }

    public int getCantidadPartidas() {
        return historialResultados.size();
    }

    public int getVictoriasTotales() {
        int victorias = 0;
        for (Resultado r : historialResultados) {
            if (r.isEsGanador()) victorias++;
        }
        return victorias;
    }

    public void limpiarHistorial() {
        this.historialResultados.clear();
    }
}