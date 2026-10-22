package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;

public class Estadistica {
	private int ganancia;
	private int montoApostado;
	public Estadistica() {
		this.ganancia = 0;
		this.montoApostado = 0;

		// TODO - implement Estadistica.Estadistica
	}
	public int gananciaTotal(List<Resultado> resultados) {

		for (Resultado resultado : resultados) {
			if (resultado.EsGanador()){
				this.ganancia += resultado.getMontoGanado();
			}
		}
		return this.ganancia;
	}
	public int montoApostadoTotal(List<Resultado> resultados) {
		for (Resultado resultado : resultados) {
			this.montoApostado += resultado.getMontoApostado();
		}
		return montoApostado;
	}

	public int getGanancia() {return ganancia;}
	public int getMontoApostado() {return montoApostado;}
	/**
	 * 
	 * @param resultados
	 */
	public int totalJuegos(List<Resultado> resultados) {
		return resultados.size();
	}

	/**
	 * 
	 * @param resultados
	 */
	public int victorias(List<Resultado> resultados) {
		// TODO - implement Estadistica.victorias
		int victorias = 0;
		for (Resultado resultado : resultados) {
			if (resultado.EsGanador()) victorias++;
		}
		return victorias;
	}

	/**
	 * 
	 * @param resultados
	 */
	public String porcentajeVictoria(List<Resultado> resultados) {
		// TODO - implement Estadistica.porcentajeVictoria
		int juegos = totalJuegos(resultados);
		int victorias = victorias(resultados);
		float porcentaje = (float) (victorias * 100) / juegos;
		return porcentaje + "%";
	}

	/**
	 * 
	 * @param resultados
	 */
	public int rachaMaxima(List<Resultado> resultados) {
		// TODO - implement Estadistica.rachaMaxima
		int contadorVictorias = 0;
		int rachaMax = 0;
		for (Resultado resultado : resultados) {
			if (resultado.EsGanador()) {
				contadorVictorias++;
				rachaMax = Math.max(rachaMax, contadorVictorias);
			}
			if (!resultado.EsGanador()) {
				contadorVictorias = 0;
			}
		}
		return rachaMax;
	}

	/**
	 * 
	 * @param resultados
	 */
	public String tipoMasJugado(List<Resultado> resultados) {
		// TODO - implement Estadistica.tipoMasJugado
		int contRojo = 0;
		int contNegro = 0;
		int contPar = 0;
		int contImpar = 0;
		for  (Resultado resultado : resultados) {
			if (resultado.getTipoApuesta().equals(TipoApuesta.Rojo)) {
				contRojo++;
			}
			if (resultado.getTipoApuesta().equals(TipoApuesta.Negro)) {
				contNegro++;
			}
			if (resultado.getTipoApuesta().equals(TipoApuesta.Par)) {
				contPar++;
			}
			if (resultado.getTipoApuesta().equals(TipoApuesta.Impar)) {
				contImpar++;
			}
		}
		Map<String, Integer> tipoApuestas = Map.of(
                "Rojo", contRojo,
                "Negro", contNegro,
                "Par", contPar,
                "Impar", contImpar
		);
		String tipoMasJugado = Collections.max(tipoApuestas.entrySet(), Map.Entry.comparingByValue()).getKey();
		//int valorMayor = tipoApuestas.get(tipoMasJugado); // Puede mostrar la cantidad de veces que se jugo ese tipo.
		return tipoMasJugado;
	}



}