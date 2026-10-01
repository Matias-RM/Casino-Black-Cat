package Launcher;

import Modelo.LogicaRuleta;
import Vista.VentanaLogin;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import javax.swing.*;
public class Launcher {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    new FlatDarkPurpleIJTheme()
            );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.mostrarVentana();
        LogicaRuleta ruleta = new LogicaRuleta();
        ruleta.main(args);
    }
}
