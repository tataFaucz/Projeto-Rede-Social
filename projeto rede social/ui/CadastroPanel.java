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

    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);

    public CadastroPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(MainFrame.AMARELO_ESCURO);
        nomeField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(MainFrame.AMARELO_ESCURO);
        emailField = new JTextField(20);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(MainFrame.AMARELO_ESCURO);
        senhaField = new JPasswordField(20);

        cadastrarButton = new JButton("Cadastrar");
        voltarButton = new JButton("Voltar");

        cadastrarButton.setBackground(MainFrame.AMARELO_BOTAO);
        cadastrarButton.setForeground(Color.DARK_GRAY);
        cadastrarButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 14));

        voltarButton.setBackground(MainFrame.AMARELO_MEDIO);
        voltarButton.setForeground(Color.DARK_GRAY);
        voltarButton.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        voltarButton.setFont(new Font("Arial", Font.BOLD, 14));

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

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (sistema.criarConta(nome, email, senha)) {
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    mainFrame.mostrarInicio();
                } else {
                    JOptionPane.showMessageDialog(null, "Email já está em uso.");
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
