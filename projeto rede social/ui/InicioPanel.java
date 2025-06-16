package ui;

import negocios.Sistema;

import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {
    public InicioPanel(Sistema sistema, MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");

        btnEntrar.setBackground(MainFrame.AMARELO_BOTAO);
        btnEntrar.setForeground(Color.DARK_GRAY);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));

        btnCadastrar.setBackground(MainFrame.AMARELO_BOTAO);
        btnCadastrar.setForeground(Color.DARK_GRAY);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0; add(btnEntrar, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(btnCadastrar, gbc);

        btnEntrar.addActionListener(e -> mainFrame.mostrarLogin());
        btnCadastrar.addActionListener(e -> mainFrame.mostrarCadastro());
    }
}