package ui;

import javax.swing.*;
import java.awt.*;
import negocios.Sistema;

public class MainFrame extends JFrame {
    private Sistema sistema;

    public MainFrame(Sistema sistema) {
        this.sistema = sistema;
        setTitle("Floguinho - De Volta aos 15");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(255, 204, 255));
        setLayout(new BorderLayout());

        add(new LoginPanel(this, sistema), BorderLayout.CENTER);
    }

    public void showUserPanel(String nomeUsuario) {
        getContentPane().removeAll();
        add(new UserPanel(this, sistema, nomeUsuario), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}