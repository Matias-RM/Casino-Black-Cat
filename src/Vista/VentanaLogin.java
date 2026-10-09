package Vista;
import Controlador.SessionController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaLogin {
    private SessionController session;
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("LogIn - Casino Black Cat");
    private final JPanel panelTextos = new JPanel();
    private final JPanel panelBoton = new JPanel();
    private final JLabel lblUsuario = new JLabel("Nombre de usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final VentanaRegistro registro = new VentanaRegistro();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnInvitado = new JButton("Invitado");
    private final Dimension dimensionGeneral = new Dimension(200, 30);
    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin(SessionController session) {
        this.session = session;
        iniciarComponentes();

    }

    private void iniciarComponentes() {
        SwingUtilities.invokeLater(() -> {

            frame.setSize(800, 600);
            panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
            panelTextos.setBorder(new EmptyBorder(200, 200, 0, 200));

            lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnInvitado.setAlignmentX(Component.CENTER_ALIGNMENT);

            txtUsuario.setPreferredSize(dimensionGeneral);
            txtUsuario.setMaximumSize(dimensionGeneral);
            txtClave.setPreferredSize(dimensionGeneral);
            txtClave.setMaximumSize(dimensionGeneral);

            panelTextos.add(lblUsuario);
            panelTextos.add(txtUsuario);
            panelTextos.add(lblClave);
            panelTextos.add(txtClave);
            panelBoton.add(btnIngresar);
            panelBoton.add(btnRegistrar);
            panelBoton.add(btnInvitado);
            btnIngresar.addActionListener(a -> login());
            btnRegistrar.addActionListener(b -> abrirRegistro());
            btnInvitado.addActionListener(c -> cuentaInvitado());

            panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
            frame.add(panelTextos,  BorderLayout.NORTH);
            frame.add(panelBoton);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    private void cerrarVentana() {
        frame.dispose();

    }

    private void cuentaInvitado() {

        JOptionPane.showMessageDialog(frame, "Ingresando en cuenta de invitado", "Ingreso invitado",  JOptionPane.INFORMATION_MESSAGE);
        cerrarVentana();
        session.iniciarInvitado();
        VentanaMenu ventanaMenu = new VentanaMenu(session);

        ventanaMenu.mostrarVentana();
    }

    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {

        frame.setVisible(true);
        SwingUtilities.invokeLater(()->frame.setLocationRelativeTo(null));
    }
    /**
     * Gestiona el inicio de sesión al presionar el botón.
     * Debe validar las credenciales ingresadas y abrir la siguiente
     * ventana o mostrar un mensaje de error.
     */
    private void login() {

        String nombreUsuario = txtUsuario.getText();
        String claveUsuario = new String(txtClave.getPassword());
        boolean resultado = session.iniciarSesion(nombreUsuario, claveUsuario);

        if (!resultado) {
            JOptionPane.showMessageDialog(frame, "Usuario o Clave no valido", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(frame, "Bienvenido usuario: " + session.getNombreUsuario());
            cerrarVentana();
            VentanaMenu ventanaMenu = new VentanaMenu(session);
            ventanaMenu.mostrarVentana();
        }

    }
    /**
     * Abre la ventana de registro para crear un nuevo usuario.
     * Debe cerrar la ventana actual e invocar a VentanaRegistro.
     */
    private void abrirRegistro() {

        cerrarVentana();
        registro.mostrarVentanaRegistro();

    }
}