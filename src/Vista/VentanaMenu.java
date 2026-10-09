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
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel textArea = new JLabel("RULETA - Casino Black Cat");
    private final JTextArea textAreaInfo = new JTextArea();
    private final JButton btnPerfil = new JButton("Perfil");
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

            btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
            actualizartexto();

            panelBotones.add(btnInicio);
            panelBotones.add(btnJugar);
            panelBotones.add(btnPerfil);
            panelBotones.add(btnHistorial);
            panelBotones.add(btnSalir);
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            textAreaInfo.setEditable(false);
            panelInfo.add(textArea);
            panelInfo.add(textAreaInfo);

            frame.add(panelBotones,BorderLayout.WEST);
            frame.add(panelInfo);


            btnInicio.addActionListener(a -> irIngreso());
            btnJugar.addActionListener(e ->irRuleta());
            btnPerfil.addActionListener(e -> verPerfil());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });
    }
    private void actualizartexto() {
        textAreaInfo.setText("Bienvenido/a al menú principal \nA la izquierda tienes:\n -Jugar: abre la ventana de juego\n -Recargar: abre una ventana para que recarges saldo \n -Historial: abre la ventana de historial\n- Salir: cierra sesion y vuelve al login.\n\n\nSaldo: $" + session.getSaldoUsuario());
    }
    private void verPerfil() {
        VentanaPerfil ventanaperfil = new VentanaPerfil(session);
        frame.dispose();
        ventanaperfil.mostrarVentana();
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
