
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



class VentanaLoginTest {

    @AfterEach
    void limpiar() {
        for (Window w : Window.getWindows()) {
            w.dispose();
        }
    }


    @Test
    void mostrarVentana_debeHacerVisibleElFrame() throws Exception {
        VentanaLogin ventana = new VentanaLogin();
        SwingUtilities.invokeAndWait(() -> {
            ventana.mostrarVentana();
        });

        Field frameField = VentanaLogin.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        JFrame frame = (JFrame) frameField.get(ventana);

        assertTrue(frame.isVisible());

    }

    private String invocarValidarCredenciales(VentanaLogin ventana, String usuario, String password) {
        try {
            Method m = VentanaLogin.class.getDeclaredMethod("validarCredenciales", String.class, String.class);
            m.setAccessible(true);
            return (String) m.invoke(ventana, usuario, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validarCredenciales_usuarioExistente() {
        VentanaLogin ventana = new VentanaLogin();
        Usuario usuario = new Usuario("admin", "1234", "admin");
        VentanaLogin.USUARIOS.add(usuario);
        String resultado = invocarValidarCredenciales(ventana, "admin", "1234");
        assertEquals("admin", resultado);
    }

    @Test
    void validarCredenciales_usuarioNoExistente() {
        VentanaLogin ventana = new VentanaLogin();
        String resultado = invocarValidarCredenciales(ventana, "intruso", "pass");
        assertEquals("", resultado);
    }
    @Test
    void abrirRegistro_debeCerrarFrameYMostrarRegistro() throws Exception {
        VentanaLogin ventana = new VentanaLogin();

        JFrame frame = new JFrame();
        Field frameField = VentanaLogin.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frameField.set(ventana, frame);

        VentanaRegistro registro = new VentanaRegistro();
        Field regField = VentanaLogin.class.getDeclaredField("registro");
        regField.setAccessible(true);
        regField.set(ventana, registro);

        Method m = VentanaLogin.class.getDeclaredMethod("abrirRegistro");
        m.setAccessible(true);
        m.invoke(ventana);

        assertFalse(frame.isVisible());
    }
}
