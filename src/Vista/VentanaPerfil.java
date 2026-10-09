package Vista;

import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class VentanaPerfil {
    private final SessionController session;
    private final JFrame frame = new JFrame();

    private final JPanel panelDatosUsuario = new JPanel();
    private final JPanel panelBotones = new JPanel();

    private final JButton btnRecargarSaldo  = new JButton("Recargar Saldo");
    private final JButton btnCambiarNombre  = new JButton("Cambiar Nombre");
    private final JButton btnVolverMenu     = new JButton("Volver al Menú");

    private final JLabel textPerfil = new JLabel("Perfil de Usuario");
    private final JTextArea txtArea = new JTextArea();
    public VentanaPerfil(SessionController session) {
        this.session = session;
        iniciarComponentes();
    }
    private void iniciarComponentes() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(800, 600);
            frame.setTitle("Perfil de Usuario - " + session.getNombreUsuario());
            frame.setLayout(new BorderLayout(20, 20));
            frame.getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));

            panelDatosUsuario.setLayout(new BorderLayout());
            panelDatosUsuario.setOpaque(false);

            textPerfil.setFont(textPerfil.getFont().deriveFont(Font.BOLD, 20f));
            textPerfil.setHorizontalAlignment(SwingConstants.CENTER);

            txtArea.setEditable(false);
            txtArea.setOpaque(false);
            txtArea.setBorder(null);
            txtArea.setFont(txtArea.getFont().deriveFont(Font.PLAIN, 16f));
            txtArea.setLineWrap(true);
            txtArea.setWrapStyleWord(true);
            actualizarText();

            JPanel panelTexto = new JPanel(new BorderLayout(0, 15));
            panelTexto.setOpaque(false);
            panelTexto.setBorder(new EmptyBorder(20, 20, 20, 20));
            panelTexto.add(textPerfil, BorderLayout.NORTH);
            panelTexto.add(txtArea, BorderLayout.CENTER);

            panelDatosUsuario.add(panelTexto, BorderLayout.CENTER);

            panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
            panelBotones.setOpaque(false);
            panelBotones.setBorder(new EmptyBorder(40, 10, 40, 10));

            Dimension btnSize = new Dimension(180, 45);
            for (JButton b : new JButton[]{btnRecargarSaldo, btnCambiarNombre, btnVolverMenu}) {
                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                b.setMaximumSize(btnSize);
                b.setPreferredSize(btnSize);
                b.setFont(b.getFont().deriveFont(Font.PLAIN, 14f));
            }

            panelBotones.add(Box.createVerticalGlue());
            panelBotones.add(btnRecargarSaldo);
            panelBotones.add(Box.createVerticalStrut(10));
            panelBotones.add(btnCambiarNombre);
            panelBotones.add(Box.createVerticalStrut(10));
            panelBotones.add(btnVolverMenu);
            panelBotones.add(Box.createVerticalGlue());

            frame.add(panelDatosUsuario, BorderLayout.CENTER);
            frame.add(panelBotones,      BorderLayout.EAST);

            btnCambiarNombre.addActionListener(e -> {
                cambiarNombreUsuario(mensajeInputUsername());
                actualizarText();
            });
            btnRecargarSaldo.addActionListener(e -> {
                recargarSaldo(mensajeInputSaldo());
                actualizarText();
            });
            btnVolverMenu.addActionListener(e -> volverAlMenu());

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }
    private void volverAlMenu() {
        VentanaMenu ventanaMenu = new VentanaMenu(session);
        frame.dispose();
        SwingUtilities.invokeLater(ventanaMenu::mostrarVentana);
    }
    public void mostrarVentana() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(  ()-> {
           frame.setLocationRelativeTo(null);
        });
    }
    private void actualizarText() {
        txtArea.setText("Nombre de Usuario: " + session.getUsernameUsuario() + "\nSaldo: " + session.getSaldoUsuario());
    }
    private void cambiarNombreUsuario(String nuevoNombreUsuario) {
        session.setUsernameUsuario(nuevoNombreUsuario);
    }
    private void recargarSaldo(int Saldo) {
        session.recargarSaldoUsuario(Saldo);
    }
    private int mensajeInputSaldo(){
        String input = JOptionPane.showInputDialog(frame,"Ingrese el monto a depositar", JOptionPane.QUESTION_MESSAGE);
        if (input!=null){
            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return 0;
    }
    private String mensajeInputUsername() {
        String input = JOptionPane.showInputDialog(frame,"Ingrese el nuevo nombre: ", JOptionPane.QUESTION_MESSAGE);
        if (input==null){
            JOptionPane.showMessageDialog(frame,"Sin cambios");
        }
        return input;
    }
}
