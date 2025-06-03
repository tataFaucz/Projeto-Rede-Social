package ui;

import javax.swing.*;
import java.awt.*;
import negocios.Sistema;
import dados.Usuario;
import dados.Foto;
import dados.Recado;

public class UserPanel extends JPanel {
    public UserPanel(MainFrame frame, Sistema sistema, String nomeUsuario) {
        setLayout(new BorderLayout());
        Usuario usuario = sistema.buscarUsuario(nomeUsuario);

        // Perfil
        JPanel perfilPanel = new JPanel();
        perfilPanel.setBackground(new Color(255, 255, 255, 200));
        perfilPanel.setLayout(new BoxLayout(perfilPanel, BoxLayout.Y_AXIS));
        perfilPanel.setBorder(BorderFactory.createTitledBorder("Perfil"));

        JLabel fotoPerfil = new JLabel(new ImageIcon(usuario.getFotoPerfil()));
        JLabel nome = new JLabel(usuario.getNome());
        nome.setFont(new Font("Arial", Font.BOLD, 22));
        perfilPanel.add(fotoPerfil);
        perfilPanel.add(nome);

        // Álbum de fotos
        JPanel fotosPanel = new JPanel();
        fotosPanel.setBorder(BorderFactory.createTitledBorder("Floguinho"));
        fotosPanel.setLayout(new FlowLayout());
        for (Foto foto : usuario.getFotos()) {
            JPanel mini = new JPanel();
            mini.setLayout(new BoxLayout(mini, BoxLayout.Y_AXIS));
            mini.add(new JLabel(new ImageIcon(foto.getCaminho())));
            mini.add(new JLabel(foto.getLegenda()));
            fotosPanel.add(mini);
        }

        // Recados
        JPanel recadosPanel = new JPanel();
        recadosPanel.setBorder(BorderFactory.createTitledBorder("Recados"));
        recadosPanel.setLayout(new BoxLayout(recadosPanel, BoxLayout.Y_AXIS));
        for (Recado recado : usuario.getRecados()) {
            recadosPanel.add(new JLabel(recado.getRemetente() + ": " + recado.getMensagem()));
        }

        // Layout principal
        add(perfilPanel, BorderLayout.WEST);
        add(fotosPanel, BorderLayout.CENTER);
        add(recadosPanel, BorderLayout.SOUTH);
    }
}