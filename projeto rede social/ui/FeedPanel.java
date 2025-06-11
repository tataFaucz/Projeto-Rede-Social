package ui;

import dados.Foto;
import dados.Usuario;
import negocios.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

public class FeedPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;
    private JPanel feedArea;

    public FeedPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        criarMenuLateral();
        criarAreaFeed();
        carregarPublicacoes();
    }

    private void criarMenuLateral() {
        JPanel menu = new JPanel(new GridLayout(6, 1));
        menu.setPreferredSize(new Dimension(150, getHeight()));

        JButton btnPublicar = new JButton("Nova Publicação");
        JButton btnExplorar = new JButton("Explorar");
        JButton btnMensagens = new JButton("Mensagens");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnLogout = new JButton("Logout");

        btnPublicar.addActionListener(e -> publicarFoto());
        btnExplorar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidade em desenvolvimento."));
        btnMensagens.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidade em desenvolvimento."));
        btnPerfil.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidade em desenvolvimento."));
        btnLogout.addActionListener(e -> {
            sistema.logout();
            mainFrame.mostrarLogin();
        });

        menu.add(new JLabel(" MENU ", SwingConstants.CENTER));
        menu.add(btnPublicar);
        menu.add(btnExplorar);
        menu.add(btnMensagens);
        menu.add(btnPerfil);
        menu.add(btnLogout);

        add(menu, BorderLayout.WEST);
    }

    private void criarAreaFeed() {
        feedArea = new JPanel();
        feedArea.setLayout(new BoxLayout(feedArea, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(feedArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarPublicacoes() {
        feedArea.removeAll();

        Usuario atual = sistema.getUsuarioLogado();
        List<Usuario> seguindo = atual.getSeguindo();

        for (Usuario seguido : seguindo) {
            List<Foto> publicacoes = seguido.getPublicacoes();
            Collections.reverse(publicacoes); // mostrar as mais recentes primeiro
            for (Foto foto : publicacoes) {
                feedArea.add(criarCardPublicacao(foto));
            }
        }

        revalidate();
        repaint();
    }

    private JPanel criarCardPublicacao(Foto foto) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(foto.getAutor().getNome()));

        JLabel legendaLabel = new JLabel("<html><b>" + foto.getLegenda() + "</b></html>");
        JLabel curtidasLabel = new JLabel("Curtidas: " + foto.getCurtidas().size());

        JButton btnCurtir = new JButton("Curtir");
        JButton btnCompartilhar = new JButton("Compartilhar");

        btnCurtir.addActionListener(e -> {
            if (sistema.curtirFoto(foto)) {
                curtidasLabel.setText("Curtidas: " + foto.getCurtidas().size());
            } else {
                JOptionPane.showMessageDialog(null, "Você já curtiu esta foto.");
            }
        });

        btnCompartilhar.addActionListener(e -> {
            if (sistema.compartilharFoto(foto)) {
                JOptionPane.showMessageDialog(null, "Foto compartilhada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Você já compartilhou esta foto.");
            }
        });

        JPanel botoes = new JPanel();
        botoes.add(btnCurtir);
        botoes.add(btnCompartilhar);

        card.add(legendaLabel, BorderLayout.NORTH);
        card.add(curtidasLabel, BorderLayout.CENTER);
        card.add(botoes, BorderLayout.SOUTH);

        return card;
    }

    private void publicarFoto() {
        JTextField legendaField = new JTextField();
        JTextField caminhoField = new JTextField();

        Object[] fields = {
            "Legenda:", legendaField,
            "Caminho da imagem:", caminhoField
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Nova Publicação", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String legenda = legendaField.getText();
            String caminho = caminhoField.getText();

            if (!legenda.isEmpty() && !caminho.isEmpty()) {
                boolean sucesso = sistema.publicarFoto(caminho, legenda);
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Foto publicada!");
                    carregarPublicacoes();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao publicar.");
                }
            }
        }
    }
}
