package Vista;

import Controlador.*;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadistica {
	private final JFrame frame = new JFrame("Estadisticas - Casino Blac Cat");
	private final JPanel panelVertical = new JPanel();
	private final JPanel panelHorizontal = new JPanel();
	private final JTextPane txtNombreUsuario = new JTextPane();
	private final JTextArea txtAreaEstadistica = new JTextArea();
	private final JButton btnVolver = new JButton("Volver");
	private SessionController session;

	/**
	 *
	 * @param session La sesion que se mantiene por el paso del juego.
	 */
	public VentanaEstadistica(SessionController session) {
		this.session = session;
		iniciarComponentes();
	}

	public void iniciarComponentes() {

		SwingUtilities.invokeLater(() -> {
			frame.setSize(800, 600);

			panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));

			txtAreaEstadistica.setLineWrap(true);
			txtAreaEstadistica.setWrapStyleWord(true);


			panelVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
			txtNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
			txtAreaEstadistica.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);


			txtNombreUsuario.setEditable(false);
			txtNombreUsuario.setFont(new Font("Arial", Font.BOLD, 20));
			txtNombreUsuario.setFocusable(false);
			txtAreaEstadistica.setEditable(false);
			txtAreaEstadistica.setFocusable(false);
			txtAreaEstadistica.setFont(new Font("Arial", Font.BOLD, 13));
			actualizarTexto();

			panelVertical.add(txtNombreUsuario);
			panelVertical.add(txtAreaEstadistica);
			panelHorizontal.add(panelVertical);
			panelHorizontal.add(btnVolver);

			btnVolver.addActionListener(e -> {
				volverMenu();
			});

			frame.add(panelHorizontal);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}

	public void volverMenu() {
		VentanaMenu ventanaMenu = new VentanaMenu(session);
		frame.dispose();
		SwingUtilities.invokeLater(() -> {ventanaMenu.mostrarVentana();});
	}

	public void mostrarVentana() {
		frame.setVisible(true);
		SwingUtilities.invokeLater(() -> {frame.setLocationRelativeTo(null);});
	}

	public void actualizarTexto() {
		txtNombreUsuario.setText("                            " +
				"Historial de " + session.getNombreUsuario());
		txtAreaEstadistica.setText(
				"\n\n> Total Juegos: " + totalJuegos() + "	> Victorias: " + numVictorias() +
				"\n\n> Total Apostado: $" + montoApostado()+
				"\n\n> Dinero Obtenido: $" + ganancias() +
				"\n\n> Racha Maxima: " + rachaMaxima() +
				"\n\n> Porcentaje de Victorias: " + porcentajeVictoria() +
				"\n\n> Tipo de Apuesta Preferido: " + tipoMasJugado());
		}
	private int numVictorias(){
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.getVictoriasTotales();
	}
	private int ganancias(){
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.gananciaTotal();
	}
	private int montoApostado(){
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.montoApostadoTotal();
	}
	private int rachaMaxima(){
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.getRachaMaxima();
	}
	private String porcentajeVictoria() {
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.getPorcentajeVictorias();
	}
	private String tipoMasJugado() {
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.getTipoMasJugado();
	}
	private int totalJuegos() {
		ResultadoController resultado = new ResultadoController(session.getUsuarioActual());
		return resultado.getJuegos();
	}
}