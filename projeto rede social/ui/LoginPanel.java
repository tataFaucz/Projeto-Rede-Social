package ui;

import javax.swing.*;
import java.awt.*;
import negocios.Sistema;

public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame frame, Sistema sistema) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Nome do usuário:");
        JTextField textField = new JTextField(15);
        JButton loginButton = new JButton("Entrar");

        loginButton.addActionListener(e -> {
            String nome = textField.getText();
            if (sistema.buscarUsuario(nome) != null) {
                frame.showUserPanel(nome);
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; add(label, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(textField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(loginButton, gbc);
    }
}