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
    private final JButton btnEstadisticas = new JButton("Estadisticas");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel textAreaTitulo = new JLabel("RULETA - Casino Black Cat");
    private final JTextArea textAreaInfo = new JTextArea();
    private final JButton btnPerfil = new JButton("Perfil");
    public VentanaMenu(SessionController session) {
        this.session = session;
        iniciarComponentes();

    }
    private void iniciarComponentes() {

        SwingUtilities.invokeLater(()-> {
            frame.setTitle("Menú - Casino Black Cat * " + session.getUsuarioActual().getNombre()+" *");
            frame.setSize(800,600);
            panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.Y_AXIS));
            panelBotones.setBorder(new EmptyBorder(2,10,2,10));
            panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
            panelInfo.setBorder(new EmptyBorder(2,10,2,10));

            btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnEstadisticas.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
            actualizartexto();

            panelBotones.add(btnInicio);
            panelBotones.add(btnJugar);
            panelBotones.add(btnPerfil);
            panelBotones.add(btnHistorial);
            panelBotones.add(btnEstadisticas);
            panelBotones.add(btnSalir);
            textAreaTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
            textAreaInfo.setEditable(false);
            panelInfo.add(textAreaTitulo);
            panelInfo.add(textAreaInfo);

            frame.add(panelBotones,BorderLayout.WEST);
            frame.add(panelInfo);


            btnInicio.addActionListener(a -> irIngreso());
            btnJugar.addActionListener(e ->irRuleta());
            btnPerfil.addActionListener(e -> verPerfil());
            btnHistorial.addActionListener(e -> irHistorial());
            btnEstadisticas.addActionListener(e -> irEstadisticas());
            btnSalir.addActionListener(e -> salir());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });
    }
    public void mostrarVentana() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(()-> {frame.setLocationRelativeTo(null);});

    }
    private void salir(){
        frame.dispose();
    }
    private void irEstadisticas(){
        VentanaEstadistica ventanaestadistica = new VentanaEstadistica(session);
        ventanaestadistica.mostrarVentana();
        frame.dispose();
    }
    private void actualizartexto() {
        textAreaInfo.setText("Bienvenido/a al menú principal \nA la izquierda tienes:\n -Jugar: abre la ventana de juego\n -Recargar: abre una ventana para que recarges saldo \n -Historial: abre la ventana de historial\n- Salir: cierra sesion y vuelve al login.\n\n\nSaldo: $" + session.getSaldoUsuario());
    }
    private void verPerfil() {
        VentanaPerfil ventanaPerfil = new VentanaPerfil(session);
        frame.dispose();
        ventanaPerfil.mostrarVentana();
    }

    private void irHistorial() {
        VentanaHistorial ventanaHistorial = new VentanaHistorial(session);
        SwingUtilities.invokeLater(()-> {ventanaHistorial.mostrarVentana();});
        frame.dispose();
    }

    private void irRuleta() {
        VentanaRuleta ventanaRuleta =  new VentanaRuleta(session);
        SwingUtilities.invokeLater(()-> {ventanaRuleta.mostrarVentana();});
        frame.dispose();}

    private void irIngreso() {
        VentanaLogin ventanaLogin = new VentanaLogin(session);
        session.cerrarSession();
        SwingUtilities.invokeLater(()-> {ventanaLogin.mostrarVentana();});
        frame.dispose();}
}
