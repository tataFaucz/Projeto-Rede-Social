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
    private JButton cadastrarButton;

    public LoginPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);

        loginButton = new JButton("Entrar");
        cadastrarButton = new JButton("Cadastrar");

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
        add(cadastrarButton, gbc);

        // Ações dos botões
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (sistema.login(email, senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    mainFrame.mostrarFeed(); // troca para painel principal
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.mostrarCadastro(); // troca para painel de cadastro
            }
        });
    }
}
