package ui;

import negocios.Sistema;
import javax.swing.*;

public class MainFrame extends JFrame {
    private Sistema sistema;

    public MainFrame(Sistema sistema) {
        this.sistema = sistema;
        setTitle("Rede Social");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
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
        // Troque para o painel principal do seu sistema
        // setContentPane(new FeedPanel(sistema, this));
        // revalidate();
        // repaint();
    }
}
