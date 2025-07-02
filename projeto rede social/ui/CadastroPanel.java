package ui;

import negocios.Sistema;

import javax.swing.*;
import java.awt.*;

public class CadastroPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;

    public CadastroPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBackground(MainFrame.AMARELO_CLARO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JLabel lblFoto = new JLabel("Foto de Perfil (opcional):");
        JTextField txtFoto = new JTextField(20);

        JLabel lblBiografia = new JLabel("Biografia (opcional):");
        JTextField txtBiografia = new JTextField(20);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        lblNome.setForeground(Color.DARK_GRAY);
        lblEmail.setForeground(Color.DARK_GRAY);
        lblSenha.setForeground(Color.DARK_GRAY);
        lblFoto.setForeground(Color.DARK_GRAY);
        lblBiografia.setForeground(Color.DARK_GRAY);

        txtNome.setBackground(MainFrame.AMARELO_CLARO);
        txtEmail.setBackground(MainFrame.AMARELO_CLARO);
        txtSenha.setBackground(MainFrame.AMARELO_CLARO);
        txtFoto.setBackground(MainFrame.AMARELO_CLARO);
        txtBiografia.setBackground(MainFrame.AMARELO_CLARO);

        txtNome.setForeground(Color.DARK_GRAY);
        txtEmail.setForeground(Color.DARK_GRAY);
        txtSenha.setForeground(Color.DARK_GRAY);
        txtFoto.setForeground(Color.DARK_GRAY);
        txtBiografia.setForeground(Color.DARK_GRAY);

        btnCadastrar.setBackground(MainFrame.AMARELO_BOTAO);
        btnCadastrar.setForeground(Color.DARK_GRAY);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 13));

        btnVoltar.setBackground(MainFrame.AMARELO_BOTAO);
        btnVoltar.setForeground(Color.DARK_GRAY);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 13));

        // Layout
        gbc.gridx = 0; gbc.gridy = 0; add(lblNome, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(txtNome, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtEmail, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(lblSenha, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(txtSenha, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(lblFoto, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(txtFoto, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(lblBiografia, gbc);
        gbc.gridx = 1; gbc.gridy = 4; add(txtBiografia, gbc);
        gbc.gridx = 0; gbc.gridy = 5; add(btnCadastrar, gbc);
        gbc.gridx = 1; gbc.gridy = 5; add(btnVoltar, gbc);

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword());
            String fotoPerfil = txtFoto.getText().trim();
            String biografia = txtBiografia.getText().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
                return;
            }

            boolean sucesso = sistema.cadastrarUsuario(nome, email, senha, fotoPerfil, biografia);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                mainFrame.mostrarInicio();
            } else {
                JOptionPane.showMessageDialog(this, "Email já cadastrado.");
            }
        });

        btnVoltar.addActionListener(e -> mainFrame.mostrarInicio());
    }
}
