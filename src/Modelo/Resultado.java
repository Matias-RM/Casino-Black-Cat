package Modelo;

public class Resultado {
    private int numero;
    private String tipoApuesta;
    private int montoApostado;
    private boolean esGanador;
    private int montoGanado;

    public Resultado(int numero, String tipoApuesta, int montoApostado, boolean esGanador, int montoGanado) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.montoApostado = montoApostado;
        this.esGanador = esGanador;
        this.montoGanado = montoGanado;
    }

    public int getNumero() { return numero; }
    public String getTipoApuesta() { return tipoApuesta; }
    public int getMontoApostado() { return montoApostado; }
    public boolean EsGanador() { return esGanador; }
    public int getMontoGanado() { return montoGanado; }
    @Override
    public String toString() {
        String estado;
        if (esGanador) {
            estado = "Ganó $" + montoGanado;
            return "Número: " + numero + " | Apuesta: " + tipoApuesta + " | " + "Monto: " + montoApostado + " | " + estado;

        } else if (!esGanador) {
            estado = "Perdió $" + montoApostado;
            return "Número: " + numero + " | Apuesta: " + tipoApuesta + " | " + "Monto: " + montoApostado + " | "+ estado;

        }
        return"";
    }
}