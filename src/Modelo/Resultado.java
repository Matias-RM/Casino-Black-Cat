package Modelo;

public class Resultado {
    private int numeroGanador;
    private TipoApuesta tipoApuesta;
    private int montoApostado;
    private boolean esGanador;
    private int montoGanado;

    public Resultado(int numeroGanador, TipoApuesta tipoApuesta, int montoApostado, boolean esGanador, int montoGanado) {
        this.numeroGanador = numeroGanador;
        this.tipoApuesta = tipoApuesta;
        this.montoApostado = montoApostado;
        this.esGanador = esGanador;
        this.montoGanado = montoGanado;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public int getMontoApostado() { return montoApostado; }
    public boolean isEsGanador() { return esGanador; }
    public int getMontoGanado() { return montoGanado; }

    public String toString() {
        String estado;
        if (esGanador) {
            estado = "Ganó $" + montoGanado;
            return "Número: " + numeroGanador + " | Apuesta: " + tipoApuesta + " | " + estado;

        } else if (!esGanador) {
            estado = "Perdió $" + montoApostado;
            return "Número: " + numeroGanador + " | Apuesta: " + tipoApuesta + " | " + estado;

        }
        return"";
    }
}