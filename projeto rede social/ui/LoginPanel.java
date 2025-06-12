package ui;

import negocios.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;

    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton voltarButton;

    public LoginPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(MainFrame.AMARELO_ESCURO);
        emailField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(MainFrame.AMARELO_ESCURO);
        senhaField = new JPasswordField(20);

        loginButton = new JButton("Entrar");
        voltarButton = new JButton("Voltar");

        loginButton.setBackground(MainFrame.AMARELO_BOTAO);
        loginButton.setForeground(Color.DARK_GRAY);
        loginButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        voltarButton.setBackground(MainFrame.AMARELO_MEDIO);
        voltarButton.setForeground(Color.DARK_GRAY);
        voltarButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        voltarButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(emailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(senhaLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(senhaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(loginButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(voltarButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (sistema.autenticar(email, senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    mainFrame.mostrarFeed();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.mostrarInicio();
            }
        });
    }
}
