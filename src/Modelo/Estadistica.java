package Modelo;

import java.util.List;
import java.util.Collections;
import java.util.Map;

public class Estadistica {

	public Estadistica() {
	}

	/**
	 *
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna la ganancia obtenida
	 */
	public int gananciaTotal(List<Resultado> resultados) {
		int ganancia = 0;
		for (Resultado resultado : resultados) {
			if (resultado.EsGanador()){
				ganancia += resultado.getMontoGanado();
			}
		}
		return ganancia;
	}

	/**
	 *
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna el total del monto apostado.
	 */
	public int montoApostadoTotal(List<Resultado> resultados) {
		int montoApostado = 0;
		for (Resultado resultado : resultados) {
			montoApostado += resultado.getMontoApostado();
		}
		return montoApostado;
	}

	/**
	 * 
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna el tamaño de la lista de resultados.
	 */
	public int totalJuegos(List<Resultado> resultados) {
		if (resultados.isEmpty()) {
			return 0;
		}
		return resultados.size();
	}

	/**
	 * 
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna el total de victorias.
	 */
	public int victorias(List<Resultado> resultados) {
		int victorias = 0;
		for (Resultado resultado : resultados) {
			if (resultado.EsGanador()) victorias++;
		}
		return victorias;
	}

	/**
	 * 
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna el porcentaje de victorias en texto.
	 */
	public String porcentajeVictoria(List<Resultado> resultados) {
		int juegos = totalJuegos(resultados);
		int victorias = victorias(resultados);
		float porcentaje = (float) (victorias * 100) / juegos;
		if (Float.isNaN(porcentaje)) {
			return "Sin Datos";
		}
		return porcentaje + "%";
	}

	/**
	 * 
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna la racha maxima de victorias.
	 */
	public int rachaMaxima(List<Resultado> resultados) {
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
	 * @param resultados Lista  de resultados a evaluar.
	 * @return retorna el tipo de apuesta mas jugado.
	 */
	public String tipoMasJugado(List<Resultado> resultados) {
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
		if (contRojo == 0 && contNegro == 0 && contPar == 0 && contImpar == 0) {
			return "Sin Datos";
		}
		return tipoMasJugado;
	}



}