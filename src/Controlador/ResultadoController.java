package Controlador;

import Modelo.Estadistica;
import Modelo.Resultado;
import Modelo.Usuario;

import java.util.List;

public class ResultadoController {
    private Usuario usuario;
    private Estadistica estadistica;

    public ResultadoController(Usuario session) {
        this.usuario = session;
        this.estadistica = new Estadistica();
    }

    public List<Resultado> getHistorialResultados(){
        return usuario.getHistorialResultados();
    }
    public void registrarResultado(Resultado resultado) {
        usuario.agregarResultados(resultado);
    }
    public int gananciaTotal() {return estadistica.gananciaTotal(getHistorialResultados());}
    public int montoApostadoTotal(){return estadistica.montoApostadoTotal(getHistorialResultados());}
    public int getVictoriasTotales() {return estadistica.victorias(getHistorialResultados());}
    public int getRachaMaxima() {return estadistica.rachaMaxima(getHistorialResultados());}
    public int getJuegos() {return estadistica.totalJuegos(getHistorialResultados());}
    public String getPorcentajeVictorias() {return estadistica.porcentajeVictoria(getHistorialResultados());}
    public String getTipoMasJugado() {return estadistica.tipoMasJugado(getHistorialResultados());}
}