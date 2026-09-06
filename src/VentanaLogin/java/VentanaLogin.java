import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    // --- Lista dinámica de usuarios ---
    public static final List USUARIOS = new ArrayList<>();
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JPanel panel = new JPanel();
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JFrame registroSesion = new JFrame("Sign up - Casino Black Cat");

    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin() {
// TODO: Agregar los usuarios iniciales a la lista
// TODO: Inicializar y configurar la ventana


        SwingUtilities.invokeLater(() -> {


            frame.setSize(800, 600);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.setBorder(new EmptyBorder(200, 200, 200, 200));

            lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtClave.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);

            Dimension tamañoCampo = new Dimension(200, 30);
            txtUsuario.setPreferredSize(tamañoCampo);
            txtUsuario.setMaximumSize(tamañoCampo);

            txtClave.setPreferredSize(tamañoCampo);
            txtClave.setMaximumSize(tamañoCampo);

            panel.add(lblUsuario);
            panel.add(txtUsuario);
            panel.add(lblClave);
            panel.add(txtClave);
            panel.add(btnIngresar);
            btnIngresar.addActionListener(e -> login());
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        });

    }

    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {
// TODO: Centrar y mostrar la ventana

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Gestiona el inicio de sesión al presionar el botón.
     * Debe validar las credenciales ingresadas y abrir la siguiente
     * ventana o mostrar un mensaje de error.
     */
    private void login() {
// TODO: Implementar la lógica de inicio de sesión


    }

    /**
     * Valida las credenciales ingresadas utilizando la lista de usuarios.
     *
     * @param u nombre de usuario ingresado
     * @param p contraseña ingresada
     * @return el nombre del usuario si las credenciales son válidas o una cadena vacía
     * si no existe una coincidencia
     */
    private String validarCredenciales(String u, String p) {
// TODO: Recorrer la lista y validar las credenciales

        return "";
    }

    /**
     * Abre la ventana de registro para crear un nuevo usuario.
     * Debe cerrar la ventana actual e invocar a VentanaRegistro.
     */
    private void abrirRegistro() {
// TODO: Cerrar la ventana actual y abrir la ventana de registro

    }
}