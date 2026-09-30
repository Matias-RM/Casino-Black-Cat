package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaMenu {

    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JPanel panelBotones = new JPanel();
    private final JPanel panelInfo = new JPanel();
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel textArea = new JLabel("RULETA - Casino Black Cat");
    private final JTextArea textAreaInfo = new JTextArea("Bienvenido/a al menú principal \nA la izquierda tienes:\n -Jugar: abre la ventana de juego\n -Historial: abre la ventana de historial\n- Salir: cierra sesion y vuelve al login.");

    public VentanaMenu() {
        SwingUtilities.invokeLater(()-> {

            frame.setSize(800,600);
            panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.Y_AXIS));
            panelBotones.setBorder(new EmptyBorder(10,10,10,10));
            panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
            panelInfo.setBorder(new EmptyBorder(10,10,10,10));

            btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelBotones.add(btnInicio);
            panelBotones.add(btnJugar);
            panelBotones.add(btnHistorial);
            panelBotones.add(btnSalir);
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            textAreaInfo.setEditable(false);
            panelInfo.add(textArea);
            panelInfo.add(textAreaInfo);

            frame.add(panelBotones,BorderLayout.WEST);
            frame.add(panelInfo,BorderLayout.CENTER);

            btnInicio.addActionListener(a -> irIngreso());
            btnJugar.addActionListener(e ->irRuleta());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });
    }
    public void mostrarVentana() {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    private void irRuleta() {

        final VentanaRuleta ventanaRuleta =  new VentanaRuleta();
        frame.dispose();
        SwingUtilities.invokeLater(()-> {
            ventanaRuleta.mostrarVentana();
        });
    }
    private void irIngreso() {
        final VentanaLogin ventanaLogin = new VentanaLogin();
        frame.dispose();
        SwingUtilities.invokeLater(()-> {
            ventanaLogin.mostrarVentana();
        });
    }
}
