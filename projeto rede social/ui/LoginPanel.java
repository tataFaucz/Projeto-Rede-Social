package ui;

import negocios.Sistema;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;

    public LoginPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnVoltar = new JButton("Voltar");

        // Cores
        lblEmail.setForeground(Color.DARK_GRAY);
        lblSenha.setForeground(Color.DARK_GRAY);

        txtEmail.setBackground(MainFrame.AMARELO_CLARO);
        txtSenha.setBackground(MainFrame.AMARELO_CLARO);

        txtEmail.setForeground(Color.DARK_GRAY);
        txtSenha.setForeground(Color.DARK_GRAY);

        btnEntrar.setBackground(MainFrame.AMARELO_BOTAO);
        btnEntrar.setForeground(Color.DARK_GRAY);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));

        btnVoltar.setBackground(MainFrame.AMARELO_BOTAO);
        btnVoltar.setForeground(Color.DARK_GRAY);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 13));

        gbc.gridx = 0; gbc.gridy = 0; add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(txtEmail, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(lblSenha, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtSenha, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(btnEntrar, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(btnVoltar, gbc);

        btnEntrar.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword());

            if (sistema.login(email, senha)) {
                mainFrame.mostrarFeed();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        });

        btnVoltar.addActionListener(e -> mainFrame.mostrarInicio());
    }
}