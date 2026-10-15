package Controlador;

import Modelo.LogicaRuleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;

import java.util.List;

public class ResultadoController {
    private LogicaRuleta logica;
    private SessionController session;
    private int ganancia;
    private int montoApostado;

    public ResultadoController(SessionController session) {
        this.session = session;
        this.ganancia = 0;
        this.montoApostado = 0;
        gananciaTotal();
        montoApostadoTotal();
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

    public List<Resultado> getHistorialResultados(){
        return session.getUsuarioActual().getHistorialResultados();
    }
    public void registrarResultado(Resultado resultado) {
        session.getUsuarioActual().agregarResultados(resultado);
    }
    private void gananciaTotal(){
        for (Resultado resultado : session.getUsuarioActual().getHistorialResultados()) {
            if (resultado.EsGanador()){
                this.ganancia += resultado.getMontoGanado();
            }
        }
    }
    private void montoApostadoTotal(){
        for (Resultado resultado : session.getUsuarioActual().getHistorialResultados()) {
            this.montoApostado += resultado.getMontoApostado();
        }
    }
    public int getMontoApostado() {
        return montoApostado;
    }
    public int  getGanancia(){
        return this.ganancia;
    }

    public int getVictoriasTotales() {
        int victorias = 0;
        for (Resultado resultado : session.getUsuarioActual().getHistorialResultados()) {
            if (resultado.EsGanador()) victorias++;
        }
        return victorias;
    }
}