package Vista;


import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private SessionController session;
    private ResultadoController resultadoController;
    private RuletaController ruletaController;
    private int indiceHistorial = 1;
    private int indiceBorrarHistorial = 1;
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

    private final JComboBox<String> comboTipo = new JComboBox<>();
    private final JComboBox<String> comboColor = new JComboBox<>();
    private final JComboBox<String> comboParidad = new JComboBox<>();
    private final JSpinner spinnerMonto = new JSpinner(new SpinnerNumberModel(0, 0, 5000000, 50));
    private final JButton btnGirar = new JButton("Girar ruleta");
    private final JButton btnVolverMenu = new JButton("Volver al menu");


    public VentanaRuleta(SessionController session) {
     this.session = session;
     this.resultadoController = new ResultadoController(session.getUsuarioActual());
     this.ruletaController = new RuletaController(session.getUsuarioActual(), session.getUsuarios());
     iniciarComponentes();
    }
    private void iniciarComponentes() {
        SwingUtilities.invokeLater(() -> {

            frame.setSize(800, 600);
            comboTipo.setPreferredSize(new Dimension(200, 30));
            comboColor.setPreferredSize(new Dimension(200, 30));
            comboParidad.setPreferredSize(new Dimension(200, 30));
            txtProceRuleta.setPreferredSize(new Dimension(600, 100));
            txtProceRuleta.setMaximumSize(new Dimension(600, 100));
            txtProceRuleta.setMinimumSize(new Dimension(600, 100));
            panelPrincipalVertical.setLayout(new BoxLayout(panelPrincipalVertical, BoxLayout.Y_AXIS));
            panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
            panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));
            panelLabels.setLayout(new GridLayout(4, 1, 20, 20));
            panelSeleccion.setLayout(new GridLayout(4, 1, 20, 10));

            comboTipo.addItem("Color");
            comboColor.addItem("Rojo");
            comboColor.addItem("Negro");
            comboTipo.addItem("Paridad");
            comboParidad.addItem("Par");
            comboParidad.addItem("Impar");

            txtSaldo.setEditable(false);


            panelLabels.add(lblTipoApuesta);
            panelLabels.add(lblColor);
            panelLabels.add(lblParidad);

            panelSeleccion.add(comboTipo);
            panelSeleccion.add(comboColor);
            panelSeleccion.add(comboParidad);

            panelBtnGirar.add(lblMonto);
            panelBtnGirar.add(spinnerMonto);
            panelBtnGirar.add(btnGirar);
            panelBtnGirar.add(txtSaldo);
            panelBtnGirar.add(btnVolverMenu);
            actualizarSaldo();

            comboParidad.setEnabled(false);
            panelPrincipalVertical.add(panelPrincipalHorizontal);
            txtProceRuleta.setEnabled(true);
            txtProceRuleta.setEditable(false);
            panelPrincipalHorizontal.add(panelLabels);
            panelPrincipalHorizontal.add(panelSeleccion);
            panelPrincipalVertical.add(panelBtnGirar);
            panelPrincipalVertical.add(txtProceRuleta);


            comboTipo.addActionListener(e -> actualizarOpciones());
            btnGirar.addActionListener(e -> iniciarRuleta());
            btnVolverMenu.addActionListener(e -> ventanaMenu());
            frame.add(panelPrincipalVertical, BorderLayout.NORTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
    private void actualizarSaldo() {
        txtSaldo.setText("Saldo: $"+ session.getSaldoUsuario());
    }
    private void ventanaMenu() {
        VentanaMenu ventanaMenu = new VentanaMenu(session);
        frame.dispose();
        ventanaMenu.mostrarVentana();
    }
    public void mostrarVentana() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> {frame.setLocationRelativeTo(null);});
    }

    private void actualizarOpciones() {
        String tipoApuesta = comboTipo.getSelectedItem().toString();

        if (tipoApuesta.equals("Color")) {
            comboColor.setEnabled(true);
            comboParidad.setEnabled(false);

        } else if (tipoApuesta.equals("Paridad")) {
            comboParidad.setEnabled(true);
            comboColor.setEnabled(false);
        }
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
        actualizarSaldo();
        if (comboColor.isEnabled()) {
        ruletaController.realizarApuesta(getMonto(),comboColor.getSelectedItem().toString());
        }
        if (comboParidad.isEnabled()) {
        ruletaController.realizarApuesta(getMonto(),comboParidad.getSelectedItem().toString());
        }
        imprimirResultado();
        actualizarSaldo();
    }

    private void imprimirResultado(){
        if (indiceHistorial <= resultadoController.getHistorialResultados().size()){
            String textoInicial = "> " + indiceHistorial + " | " + resultadoController.getHistorialResultados().getLast().toString();
            txtProceRuleta.setText(txtProceRuleta.getText() +"\n" + textoInicial);
            indiceHistorial++;
            if (indiceHistorial == 23*indiceBorrarHistorial) {
             borrarHistorial();
             txtProceRuleta.setText(txtProceRuleta.getText() +"\n" + textoInicial);
             indiceBorrarHistorial++;
            }
        }
    }
    private void borrarHistorial() {
        txtProceRuleta.setText("");
    }
}
