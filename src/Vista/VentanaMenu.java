package Vista;

import Controlador.SessionController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaMenu {
    private SessionController session;
    private final JFrame frame = new JFrame();
    private final JPanel panelBotones = new JPanel();
    private final JPanel panelInfo = new JPanel();
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnRecargarSaldo = new JButton("Recargar");
    private final JButton btnCambiarNombre = new JButton("Cambiar nombre");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel textArea = new JLabel("RULETA - Casino Black Cat");
    private final JTextArea textAreaInfo = new JTextArea("Bienvenido/a al menú principal \nA la izquierda tienes:\n -Jugar: abre la ventana de juego\n -Recargar: abre una ventana para que recarges saldo \n -Historial: abre la ventana de historial\n- Salir: cierra sesion y vuelve al login.");
    private final JTextArea textPerfil = new JTextArea();
    private final JPanel panelPerfil = new JPanel(new BorderLayout(15,0));
    private final JPanel panelBotonesPerfil = new JPanel();
    public VentanaMenu(SessionController session) {
        this.session = session;
        iniciarComponentes();

    }
    private void iniciarComponentes() {

        SwingUtilities.invokeLater(()-> {
            frame.setTitle("Menú - Casino Black Cat **" + session.getUsuarioActual().getNombre()+"**");
            frame.setSize(800,600);
            panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.Y_AXIS));
            panelBotones.setBorder(new EmptyBorder(2,10,2,10));
            panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
            panelInfo.setBorder(new EmptyBorder(2,10,2,10));
            panelPerfil.setBorder(new EmptyBorder(2,0,2,0));
            panelBotonesPerfil.setLayout(new BoxLayout(panelBotonesPerfil, BoxLayout.Y_AXIS));
            btnRecargarSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCambiarNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            textPerfil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
            textPerfil.setLineWrap(true);
            textPerfil.setWrapStyleWord(true);

            panelBotonesPerfil.add(Box.createVerticalStrut(5));
            panelBotonesPerfil.add(btnCambiarNombre);
            panelBotonesPerfil.add(btnRecargarSaldo);

            btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRecargarSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCambiarNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelBotones.add(btnInicio);
            panelBotones.add(btnJugar);
            panelBotones.add(btnHistorial);
            panelBotones.add(btnSalir);
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            textAreaInfo.setEditable(false);
            panelInfo.add(textArea);
            panelInfo.add(textAreaInfo);
            textPerfil.setEditable(false);
            textPerfil.setOpaque(false);
            textPerfil.setBorder(null);
            textPerfil.setFont(textPerfil.getFont().deriveFont(Font.PLAIN, 14f));
            actualizarTxtPerfil();

            panelPerfil.add(textPerfil, BorderLayout.WEST);
            panelPerfil.add(panelBotonesPerfil, BorderLayout.CENTER);
            panelInfo.add(panelPerfil);

            frame.add(panelBotones,BorderLayout.WEST);
            frame.add(panelInfo);


            btnInicio.addActionListener(a -> irIngreso());
            btnJugar.addActionListener(e ->irRuleta());
            btnRecargarSaldo.addActionListener(e -> recargarSaldo());
            btnCambiarNombre.addActionListener(e -> {cambiarUsername();});
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });
    }
    private void cambiarUsername() {
        session.getUsuarioActual().setUsername(mensajeInputUsername());
        actualizarTxtPerfil();
    }
    private String mensajeInputUsername() {
        String input = JOptionPane.showInputDialog(frame,"Ingrese el nuevo nombre: ", JOptionPane.QUESTION_MESSAGE);
        if (input==null){
            JOptionPane.showMessageDialog(frame,"Sin cambios");
        }
        return input;
    }
    private void actualizarTxtPerfil() {
        textPerfil.setText("Nombre de usuario: "+ session.getUsernameUsuario() + "\nSaldo: "+ session.getSaldoUsuario());
    }
    private void recargarSaldo(){
        session.recargarSaldoUsuario(mensajeInputSaldo());
        actualizarTxtPerfil();
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
    public void mostrarVentana() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(()-> {frame.setLocationRelativeTo(null);});

    }
    private void irRuleta() {

        VentanaRuleta ventanaRuleta =  new VentanaRuleta(session);
        frame.dispose();
        SwingUtilities.invokeLater(()-> {
            ventanaRuleta.mostrarVentana();
        });
    }
    private void irIngreso() {
        VentanaLogin ventanaLogin = new VentanaLogin(session);
        session.cerrarSession();
        frame.dispose();
        SwingUtilities.invokeLater(()-> {
            ventanaLogin.mostrarVentana();
        });
    }
}
