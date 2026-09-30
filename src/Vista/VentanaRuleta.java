package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JPanel panelLabels = new JPanel();
    private final JPanel panelSeleccion = new JPanel();
    private final JPanel panelPrincipal = new JPanel();
    private final JPanel panelTipoApuesta = new JPanel();
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

        comboTipoApuesta.setPreferredSize(new Dimension(200,30));
        comboColor.setPreferredSize(new Dimension(200,30));
        comboParidad.setPreferredSize(new Dimension(200,30));
        panelTipoApuesta.setPreferredSize(new Dimension(200,30));
        panelLabels.setLayout(new BoxLayout(panelLabels,BoxLayout.Y_AXIS));
        panelSeleccion.setLayout(new BoxLayout(panelSeleccion,BoxLayout.Y_AXIS));
        panelLabels.setLayout(new GridLayout(4,1, 15,15));

        comboTipoApuesta.addItem("Color");
        comboTipoApuesta.addItem("Paridad");
        comboColor.addItem("Rojo");
        comboColor.addItem("Negro");
        comboParidad.addItem("Par");
        comboParidad.addItem("Impar");

        panelLabels.add(lblTipoApuesta);
        panelLabels.add(lblColor);
        panelLabels.add(lblParidad);
        panelLabels.add(lblMonto);

        panelSeleccion.add(comboTipoApuesta);
        panelSeleccion.add(comboColor);
        panelSeleccion.add(comboParidad);
        panelSeleccion.add(btnGirar);

        comboParidad.setEnabled(false);

        panelPrincipal.add(panelLabels);
        panelPrincipal.add(panelSeleccion);

        comboTipoApuesta.addActionListener(e -> actualizarOpciones());
        //comboColor.addActionListener(e -> );

        frame.add(panelPrincipal, BorderLayout.NORTH);
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
    private void seleccionarColor() {

    }
}
