import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("LogIn - CasinoBlackCat");
    private final JPanel panel = new JPanel();
    private final JLabel lblNombre = new JLabel("Ingrese su nombre:");
    private final JTextField txtNombre = new JTextField();
    private final JLabel lblUsuario = new JLabel("Ingrese su nombre de Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Crear cuenta");

    public VentanaRegistro() {

        SwingUtilities.invokeLater(() -> {

    });
    }
}
