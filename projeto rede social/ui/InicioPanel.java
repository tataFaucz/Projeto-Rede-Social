package ui;

import negocios.Sistema;
import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {
    
    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);
    
    public InicioPanel(Sistema sistema, MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Bem-vindo ao Omellety!");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(MainFrame.AMARELO_ESCURO);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton entrarButton = new JButton("Entrar");

        cadastrarButton.setBackground(MainFrame.AMARELO_BOTAO);
        cadastrarButton.setForeground(Color.DARK_GRAY);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));

        entrarButton.setBackground(MainFrame.AMARELO_MEDIO);
        entrarButton.setForeground(Color.DARK_GRAY);
        entrarButton.setFocusPainted(false);
        entrarButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        entrarButton.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.insets = new Insets(20, 10, 20, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(cadastrarButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(entrarButton, gbc);

        cadastrarButton.addActionListener(e -> mainFrame.mostrarCadastro());
        entrarButton.addActionListener(e -> mainFrame.mostrarLogin());
    }
}
