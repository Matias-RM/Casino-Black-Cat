package Vista;

import Controlador.ResultadoController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {
    SessionController session;

    private final JFrame frame = new JFrame("Historial de Resultados");
    private final JPanel panelVertical = new JPanel();
    private final JPanel panelHorizontal = new JPanel();
    private final JTextPane txtNombreUsuario = new JTextPane();
    private final JTextArea txtAreaHistorial = new JTextArea();
    private final JScrollPane scrollHistorial = new JScrollPane(txtAreaHistorial);
    private final JButton btnVolver = new JButton("Volver");
    public VentanaHistorial(SessionController session) {
        this.session = session;
        iniciarComponentes();
    }
    private void iniciarComponentes() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(800, 600);

            panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));

            scrollHistorial.setMinimumSize(new Dimension(600, 500));
            scrollHistorial.setPreferredSize(new Dimension(600, 500));
            scrollHistorial.setMaximumSize(new Dimension(600, 500));
            txtAreaHistorial.setLineWrap(true);
            txtAreaHistorial.setWrapStyleWord(true);
            scrollHistorial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollHistorial.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


            panelVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtNombreUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtAreaHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

            txtAreaHistorial.setEditable(false);
            actualizarText();

            txtNombreUsuario.setEditable(false);
            txtNombreUsuario.setFont(new Font("Arial", Font.BOLD, 20));
            txtNombreUsuario.setFocusable(false);
            txtAreaHistorial.setFocusable(false);

            panelVertical.add(txtNombreUsuario);
            panelVertical.add(scrollHistorial);
            panelHorizontal.add(panelVertical);
            panelHorizontal.add(btnVolver);

            btnVolver.addActionListener(e -> {irMenu();});

            frame.add(panelHorizontal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    private void irMenu(){
        VentanaMenu ventanaMenu = new VentanaMenu(session);
        frame.dispose();
        ventanaMenu.mostrarVentana();
    }
    private void actualizarText(){
        ResultadoController resultado = new ResultadoController(session);

        txtNombreUsuario.setText("                                      Historial de " + session.getNombreUsuario() + "\n");
        for (int i = 0; i < resultado.getHistorialResultados().size(); i++) {
            txtAreaHistorial.setText(txtAreaHistorial.getText() + "\n" + resultado.getHistorialResultados().get(i).toString());
        }
    }
    public void mostrarVentana(){
        frame.setVisible(true);
        SwingUtilities.invokeLater(()-> {frame.setLocationRelativeTo(null);});
    }
}
