package Controlador;

import Modelo.LogicaRuleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;
import Modelo.Estadistica;

import java.util.List;

public class ResultadoController {
    private SessionController session;
    private Estadistica estadistica;

    public ResultadoController(SessionController session) {
        this.session = session;
        this.estadistica = new Estadistica();
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
        return session.obtenerResultados();
    }
    public void registrarResultado(Resultado resultado) {
        session.agregarResultado(resultado);
    }
    public int gananciaTotal() {return estadistica.gananciaTotal(session.obtenerResultados());}
    public int montoApostadoTotal(){return estadistica.montoApostadoTotal(session.obtenerResultados());}
    public int getVictoriasTotales() {return estadistica.victorias(session.obtenerResultados());}
    public int getRachaMaxima() {return estadistica.rachaMaxima(session.obtenerResultados());}
    public int getJuegos() {return estadistica.totalJuegos(session.obtenerResultados());}
    public String getPorcentajeVictorias() {return estadistica.porcentajeVictoria(session.obtenerResultados());}
    public String getTipoMasJugado() {return estadistica.tipoMasJugado(session.obtenerResultados());}
}