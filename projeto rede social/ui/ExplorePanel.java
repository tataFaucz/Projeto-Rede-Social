package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import negocios.Sistema;
import dados.Usuario;

public class ExplorePanel extends JPanel {
    public ExplorePanel(MainFrame frame, Sistema sistema, Usuario usuario) {
        setLayout(new GridLayout(2, 1));

        // Novos perfis para adicionar como amigo
        List<Usuario> novosPerfis = sistema.listarNovosPerfis(usuario);
        DefaultListModel<String> novosModel = new DefaultListModel<>();
        for (Usuario u : novosPerfis) {
            novosModel.addElement(u.getNome());
        }
        JList<String> novosList = new JList<>(novosModel);
        JButton adicionarAmgBtn = new JButton("Adicionar amigo selecionado");
        adicionarAmgBtn.addActionListener(e -> {
            String nome = novosList.getSelectedValue();
            Usuario u = sistema.buscarUsuario(nome);
            if (u != null) {
                sistema.adicionarAmg(usuario, u);
                JOptionPane.showMessageDialog(this, "Agora você é amigo de " + nome);
            }
        });

        JPanel novosPanel = new JPanel(new BorderLayout());
        novosPanel.add(new JLabel("Novos perfis:"), BorderLayout.NORTH);
        novosPanel.add(new JScrollPane(novosList), BorderLayout.CENTER);
        novosPanel.add(adicionarAmgBtn, BorderLayout.SOUTH);

        // Lista de amigos
        List<Usuario> amigos = sistema.listarAmgs(usuario);
        DefaultListModel<String> amigosModel = new DefaultListModel<>();
        for (Usuario u : amigos) {
            amigosModel.addElement(u.getNome());
        }
        JList<String> amigosList = new JList<>(amigosModel);
        JButton removerAmgBtn = new JButton("Remover amigo selecionado");
        removerAmgBtn.addActionListener(e -> {
            String nome = amigosList.getSelectedValue();
            Usuario u = sistema.buscarUsuario(nome);
            if (u != null) {
                sistema.removerAmg(usuario, u);
                JOptionPane.showMessageDialog(this, "Você removeu " + nome + " dos seus amigos.");
            }
        });

        JPanel amigosPanel = new JPanel(new BorderLayout());
        amigosPanel.add(new JLabel("Seus amigos:"), BorderLayout.NORTH);
        amigosPanel.add(new JScrollPane(amigosList), BorderLayout.CENTER);
        amigosPanel.add(removerAmgBtn, BorderLayout.SOUTH);

        add(novosPanel);
        add(amigosPanel);
    }
}