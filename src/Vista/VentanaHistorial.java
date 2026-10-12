package Vista;

import Controlador.ResultadoController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {
    SessionController session;
    ResultadoController resultado;

    private final JFrame frame = new JFrame("Historial de Resultados");
    private final JPanel panelVertical = new JPanel();
    private final JPanel panelHorizontal = new JPanel();
    private final JTextArea txtNombreUsuario = new JTextArea();
    private final JTextArea txtAreaHistorial = new JTextArea();
    public VentanaHistorial(SessionController session) {
        this.session = session;
        iniciarComponentes();
    }
    private void iniciarComponentes() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(800, 600);

            panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));


            txtAreaHistorial.setMinimumSize(new Dimension(100, 100));
            txtAreaHistorial.setPreferredSize(new Dimension(100, 100));
            txtAreaHistorial.setMaximumSize(new Dimension(100, 100));


            panelVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtAreaHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);

            txtAreaHistorial.setEditable(false);
            panelVertical.setBackground(Color.GREEN);
            actualizarText();
            panelVertical.add(txtAreaHistorial);
            frame.add(panelVertical);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
    private void actualizarText(){
        txtNombreUsuario.setText(txtNombreUsuario.getText());
        txtAreaHistorial.setText(txtAreaHistorial.getText());
    }
    public void mostrarVentana(){
        frame.setVisible(true);
        SwingUtilities.invokeLater(()-> {frame.setLocationRelativeTo(null);});
    }
}
