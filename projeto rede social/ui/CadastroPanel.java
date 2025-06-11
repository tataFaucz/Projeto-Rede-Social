package ui;

import negocios.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;

    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton cadastrarButton;
    private JButton voltarButton;

    public CadastroPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);

        cadastrarButton = new JButton("Cadastrar");
        voltarButton = new JButton("Voltar");

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(nomeLabel, gbc);
        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(senhaLabel, gbc);
        gbc.gridx = 1;
        add(senhaField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(cadastrarButton, gbc);
        gbc.gridx = 1;
        add(voltarButton, gbc);

        // Ações dos botões
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (sistema.criarConta(nome, email, senha)) {
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    mainFrame.mostrarLogin();
                } else {
                    JOptionPane.showMessageDialog(null, "Email já está em uso.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.mostrarLogin();
            }
        });
    }
}
