package ui;

import negocios.Sistema;
import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {
    public InicioPanel(Sistema sistema, MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton entrarButton = new JButton("Entrar");

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(cadastrarButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(entrarButton, gbc);

        cadastrarButton.addActionListener(e -> mainFrame.mostrarCadastro());
        entrarButton.addActionListener(e -> mainFrame.mostrarLogin());
    }
}
