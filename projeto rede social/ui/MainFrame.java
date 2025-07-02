package ui;

import negocios.Sistema;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Sistema sistema;

    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);

    public MainFrame(Sistema sistema) {
        this.sistema = sistema;
        setTitle("Omellety - Rede Social");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(AMARELO_CLARO);
        mostrarInicio();
    }

    public void mostrarInicio() {
        setContentPane(new InicioPanel(sistema, this));
        revalidate();
        repaint();
    }

    public void mostrarCadastro() {
        setContentPane(new CadastroPanel(sistema, this));
        revalidate();
        repaint();
    }

    public void mostrarLogin() {
        setContentPane(new LoginPanel(sistema, this));
        revalidate();
        repaint();
    }

    public void mostrarFeed() {
        setContentPane(new FeedPanel(sistema, this));
        revalidate();
        repaint();
    }
}