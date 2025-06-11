package ui;

import negocios.Sistema;

import javax.swing.*;

public class MainFrame extends JFrame {
    private Sistema sistema;

    public MainFrame(Sistema sistema) {
        sistema = new Sistema();
        setTitle("Rede Social");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mostrarLogin();
    }

    public void mostrarLogin() {
        setContentPane(new LoginPanel(sistema, this));
        revalidate();
    }

    public void mostrarCadastro() {
        setContentPane(new CadastroPanel(sistema, this));
        revalidate();
    }

    public void mostrarFeed() {
        setContentPane(new FeedPanel(sistema, this));
        revalidate();
    }
}
