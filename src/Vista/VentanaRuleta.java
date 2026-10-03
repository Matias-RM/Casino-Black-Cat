package Vista;

import Modelo.LogicaRuleta;
import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private int indiceHistorial = 1;
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JPanel panelLabels = new JPanel();
    private final JPanel panelSeleccion = new JPanel();
    private final JPanel panelPrincipalHorizontal = new JPanel();
    private final JPanel panelPrincipalVertical = new JPanel();
    private final JPanel panelBtnGirar = new JPanel();
    private final JLabel lblTipoApuesta = new JLabel("Tipo de apuesta: ");
    private final JLabel lblColor = new JLabel("Seleccione Color: ");
    private final JLabel lblParidad = new JLabel("Seleccione paridad: ");
    private final JLabel lblMonto = new JLabel("Monto: ");
    private final JTextField txtSaldo = new JTextField();
    private final JTextArea txtProceRuleta = new JTextArea(100, 1);

    private final JComboBox<String> comboTipoApuesta = new JComboBox<>();
    private final JComboBox<String> comboColor = new JComboBox<>();
    private final JComboBox<String> comboParidad = new JComboBox<>();
    private final JSpinner spinnerMonto = new JSpinner(new SpinnerNumberModel(0, 0, 5000000, 50));
    private final JButton btnGirar = new JButton("Girar");


    public VentanaRuleta() {

        SwingUtilities.invokeLater(() -> {

            frame.setSize(800, 600);

            comboTipoApuesta.setPreferredSize(new Dimension(200, 30));
            comboColor.setPreferredSize(new Dimension(200, 30));
            comboParidad.setPreferredSize(new Dimension(200, 30));
            txtProceRuleta.setPreferredSize(new Dimension(600, 300));
            txtProceRuleta.setMaximumSize(new Dimension(600, 300));
            txtProceRuleta.setMinimumSize(new Dimension(600, 300));
            panelPrincipalVertical.setLayout(new BoxLayout(panelPrincipalVertical, BoxLayout.Y_AXIS));
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));
            panelLabels.setLayout(new GridLayout(4, 1, 20, 20));
            panelSeleccion.setLayout(new GridLayout(4, 1, 20, 10));

            comboTipoApuesta.addItem("Color");
            comboTipoApuesta.addItem("Paridad");
            comboColor.addItem("Rojo");
            comboColor.addItem("Negro");
            comboParidad.addItem("Par");
            comboParidad.addItem("Impar");
            txtSaldo.setEditable(false);

            panelLabels.add(lblTipoApuesta);
            panelLabels.add(lblColor);
            panelLabels.add(lblParidad);


            panelSeleccion.add(comboTipoApuesta);
            panelSeleccion.add(comboColor);
            panelSeleccion.add(comboParidad);

            panelBtnGirar.add(lblMonto);
            panelBtnGirar.add(spinnerMonto);
            panelBtnGirar.add(btnGirar);
            panelBtnGirar.add(txtSaldo);

            comboParidad.setEnabled(false);
            panelPrincipalVertical.add(panelPrincipalHorizontal);
            txtProceRuleta.setEnabled(true);
            txtProceRuleta.setEditable(false);
            panelPrincipalHorizontal.add(panelLabels);
            panelPrincipalHorizontal.add(panelSeleccion);
            panelPrincipalVertical.add(panelBtnGirar);
            panelPrincipalVertical.add(txtProceRuleta);

            comboTipoApuesta.addActionListener(e -> actualizarOpciones());
            btnGirar.addActionListener(e -> iniciarRuleta());

            frame.add(panelPrincipalVertical, BorderLayout.NORTH);
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

        } else if (tipoApuesta.equals("Paridad")) {
            comboParidad.setEnabled(true);
            comboColor.setEnabled(false);
        }
    }

    private void saldoUsuario() {
        //TODO: este método obtendra el  saldo del usuario.

    }
    private int getMonto() {
        try {
            return Integer.parseInt(spinnerMonto.getValue().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Monto invalido, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;

        }
    }
     private void iniciarRuleta() {
        LogicaRuleta logica = new LogicaRuleta();
         int numeroJuego = logica.getRandomNum();
         boolean acierto = logica.evaluarResultado(numeroJuego, comboTipoApuesta.getSelectedItem().toString(), comboColor.getSelectedItem().toString(), comboParidad.getSelectedItem().toString());
         int monto = logica.montoAcierto(getMonto(), acierto);
        imprimirResultado(numeroJuego, acierto, monto);

     }
    private String tipoApuesta() {
        if (comboColor.isEnabled()) {
            return comboColor.getSelectedItem().toString();
        }
        if (comboParidad.isEnabled()) {
            return comboParidad.getSelectedItem().toString();
        }
    return "";
    }
    private String resultado(boolean acierto) {
        if  (acierto) {
            return "¡¡Has Ganado!!";
        } else {
            return "Has Perdido";
        }
    }
    private void imprimirResultado(int numeroJuego,  Boolean acierto, int monto) {
        if (indiceHistorial <= 100) {
            String textoInicial = ">" + indiceHistorial + "  Numero aparecido " + numeroJuego + "  |  Apuesta: " + tipoApuesta() + "  | Monto: " + getMonto() + "  | Resultado: " + resultado(acierto) + "  | Total monto: " + monto + "\n";
            txtProceRuleta.setText(txtProceRuleta.getText() + textoInicial);
            indiceHistorial++;
        }
    };
}
