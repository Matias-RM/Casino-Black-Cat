import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.awt.*;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


class VentanaRegistroTest {

    @AfterEach
    void limpiar() {
        for (Window w : Window.getWindows()) {
            w.dispose();
        }
    }

    @Test
    void mostrarVentanaRegistro_debeHacerVisibleElFrame() throws Exception {
        VentanaRegistro registro = new VentanaRegistro();

        JFrame frame = new JFrame();
        Field frameField = VentanaRegistro.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frameField.set(registro, frame);

        SwingUtilities.invokeAndWait(() -> registro.mostrarVentanaRegistro());

        assertTrue(frame.isVisible());
    }

    @Test
    void quitarVentanaRegistro_debeCerrarElFrame() throws Exception {
        VentanaRegistro registro = new VentanaRegistro();

        JFrame frame = new JFrame();
        Field frameField = VentanaRegistro.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frameField.set(registro, frame);
        frame.setVisible(true);

        SwingUtilities.invokeAndWait(() -> registro.quitarVentanaRegistro());

        assertFalse(frame.isVisible());
    }


    @Test
    void signUp_camposLlenos_debeAgregarUsuario() throws Exception {
        VentanaRegistro registro = new VentanaRegistro();

        JFrame frame = new JFrame();
        Field frameField = VentanaRegistro.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frameField.set(registro, frame);
        frame.setVisible(true);

        JTextField txtNombre = new JTextField("Matías");
        JTextField txtUsuario = new JTextField("matias");
        JTextField txtClave = new JPasswordField("1234");

        Field fNombre = VentanaRegistro.class.getDeclaredField("txtNombre");
        fNombre.setAccessible(true);
        fNombre.set(registro, txtNombre);

        Field fUsuario = VentanaRegistro.class.getDeclaredField("txtUsuario");
        fUsuario.setAccessible(true);
        fUsuario.set(registro, txtUsuario);

        Field fClave = VentanaRegistro.class.getDeclaredField("txtClave");
        fClave.setAccessible(true);
        fClave.set(registro, txtClave);

        int antes = VentanaLogin.USUARIOS.size();

        Method m = VentanaRegistro.class.getDeclaredMethod("signUp");
        m.setAccessible(true);
        m.invoke(registro);
        boolean encontrado = false;

        for (Object obj : VentanaLogin.USUARIOS) {
            Usuario usuario = (Usuario) obj;
            if (usuario.getNombre().equals("Matías")) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
    }

    @Test
    void signUp_camposVacios_noDeberiaAgregarUsuario() throws Exception {
        VentanaRegistro registro = new VentanaRegistro();

        JFrame frame = new JFrame();
        Field frameField = VentanaRegistro.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frameField.set(registro, frame);

        JTextField txtNombre = new JTextField("");
        JTextField txtUsuario = new JTextField("");
        JTextField txtClave = new JPasswordField("");

        Field fNombre = VentanaRegistro.class.getDeclaredField("txtNombre");
        fNombre.setAccessible(true);
        fNombre.set(registro, txtNombre);

        Field fUsuario = VentanaRegistro.class.getDeclaredField("txtUsuario");
        fUsuario.setAccessible(true);
        fUsuario.set(registro, txtUsuario);

        Field fClave = VentanaRegistro.class.getDeclaredField("txtClave");
        fClave.setAccessible(true);
        fClave.set(registro, txtClave);

        int antes = VentanaLogin.USUARIOS.size();

        Method m = VentanaRegistro.class.getDeclaredMethod("signUp");
        m.setAccessible(true);
        m.invoke(registro);

        assertEquals(antes, VentanaLogin.USUARIOS.size());
    }
}