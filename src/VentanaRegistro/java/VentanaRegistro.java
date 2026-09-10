import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class VentanaRegistro {

    private final JFrame frame = new JFrame("SignUp - CasinoBlackCat");
    private final JPanel panel = new JPanel();
    private final JLabel lblNombre = new JLabel("Ingrese su nombre:");
    private final JTextField txtNombre = new JTextField();
    private final JLabel lblUsuario = new JLabel("Ingrese su nombre de Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnCrearCuenta = new JButton("Crear cuenta");

    /**
     * Constructor que inicializa la ventana de registro.
     * Configura sus componentes y eventos.
     */
    public VentanaRegistro() {
//
        SwingUtilities.invokeLater(() -> {

            frame.setSize(800, 600);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.setBorder(new EmptyBorder(200, 200, 200, 200));

            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCrearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);

            txtNombre.setPreferredSize(new Dimension(200, 30));
            txtNombre.setMaximumSize(new Dimension(200, 30));
            txtUsuario.setPreferredSize(new Dimension(200, 30));
            txtUsuario.setMaximumSize(new Dimension(200,30));
            txtClave.setPreferredSize(new Dimension(200, 30));
            txtClave.setMaximumSize(new Dimension(200,30));

            panel.add(lblNombre);
            panel.add(txtNombre);
            panel.add(lblUsuario);
            panel.add(txtUsuario);
            panel.add(lblClave);
            panel.add(txtClave);
            panel.add(btnCrearCuenta);

            btnCrearCuenta.addActionListener(e -> {signUp();});

            frame.add(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }
    /**
     * Muestra la ventana en pantalla.
     */
    public void mostrarVentanaRegistro() {

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Gestiona el registro de usuarios.
     */
    private void signUp() {
        // TODO: Implementar la logica del registro.

        if (txtNombre.getText().equals("") || txtUsuario.getText().equals("") || txtClave.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Hay campos vacios, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            VentanaLogin ventanaLogin = new VentanaLogin();
            Usuario usuario = new Usuario(txtUsuario.getText(), txtClave.getText(), txtNombre.getText());
            VentanaLogin.USUARIOS.add(usuario);
            SwingUtilities.invokeLater(() -> {
                ventanaLogin.mostrarVentana();
            });
            quitarVentanaRegistro();
        }
    }

    /**
     * Quita la ventana de registro
     */
    public void quitarVentanaRegistro() {
        frame.dispose();
    }
}
