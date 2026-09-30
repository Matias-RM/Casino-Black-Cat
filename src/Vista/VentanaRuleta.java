package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JPanel panelPrincipal = new JPanel();
    private final JPanel panelTipoApuesta = new JPanel();
    private final JPanel panelColor = new JPanel();
    private final JPanel panelParidad = new JPanel();
    private final JPanel panelMonto = new JPanel();
    private final JLabel lblTipoApuesta = new JLabel("Tipo de apuesta: ");
    private final JLabel lblColor = new JLabel("Seleccione Color: ");
    private final JLabel lblParidad = new JLabel("Seleccione paridad: ");
    private final JLabel lblMonto = new JLabel("Monto: ");

    private final JComboBox<String> comboTipoApuesta = new JComboBox<>();
    private final JComboBox<String> comboColor = new JComboBox<>();
    private final JComboBox<String> comboParidad = new JComboBox<>();
    private final JButton btnGirar = new JButton("Girar");

    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaRuleta() {

        SwingUtilities.invokeLater(()-> {

        frame.setSize(800,600);

        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));


        comboTipoApuesta.addItem("Color");
        comboTipoApuesta.addItem("Paridad");
        comboColor.addItem("Rojo");
        comboColor.addItem("Negro");
        comboParidad.addItem("Par");
        comboParidad.addItem("Impar");

        panelTipoApuesta.add(lblTipoApuesta);
        panelTipoApuesta.add(comboTipoApuesta);
        panelColor.add(lblColor);
        panelColor.add(comboColor);
        panelParidad.add(lblParidad);
        panelParidad.add(comboParidad);
        panelMonto.add(lblMonto);

        panelPrincipal.add(panelTipoApuesta);
        panelPrincipal.add(panelColor);
        panelPrincipal.add(panelParidad);
        panelPrincipal.add(panelMonto);

        comboParidad.setEnabled(false);
        comboColor.setEnabled(false);

        comboTipoApuesta.addActionListener(e -> actualizarOpciones());

        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarOpciones() {
        String tipoApuesta = comboTipoApuesta.getSelectedItem().toString();

        if (tipoApuesta.equals("Color")) {
            comboColor.setEnabled(true);
            comboParidad.setEnabled(false);

        }  else if (tipoApuesta.equals("Paridad")) {
            comboParidad.setEnabled(true);
            comboColor.setEnabled(false);
        }
    }

}
