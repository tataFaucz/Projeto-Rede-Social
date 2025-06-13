package ui;

import dados.Foto;
import dados.Usuario;
import negocios.Sistema;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FeedPanel extends JPanel {
    private Sistema sistema;
    private MainFrame mainFrame;
    private JPanel feedArea;

    public FeedPanel(Sistema sistema, MainFrame mainFrame) {
        this.sistema = sistema;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(MainFrame.AMARELO_CLARO);

        // Adiciona a logo no topo
        ImageIcon logoIcon = new ImageIcon("logo.png"); // Certifique-se que logo.png está na raiz do projeto
        Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        criarMenuLateral();
        criarAreaFeed();
        carregarPublicacoes();
    }

    private void criarMenuLateral() {
        JPanel menu = new JPanel(new GridLayout(6, 1));
        menu.setPreferredSize(new Dimension(150, getHeight()));
        menu.setBackground(MainFrame.AMARELO_MEDIO);

        JButton btnPublicar = new JButton("Nova Publicação");
        JButton btnExplorar = new JButton("Explorar");
        JButton btnMensagens = new JButton("Mensagens");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnLogout = new JButton("Logout");

        for (JButton btn : new JButton[]{btnPublicar, btnExplorar, btnMensagens, btnPerfil, btnLogout}) {
            btn.setBackground(MainFrame.AMARELO_BOTAO);
            btn.setForeground(Color.DARK_GRAY);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2));
            btn.setFont(new Font("Arial", Font.BOLD, 13));
        }

        btnPublicar.addActionListener(e -> publicarFoto());
        btnExplorar.addActionListener(e -> explorarUsuarios());
        btnMensagens.addActionListener(e -> mensagensUsuarios());
        btnPerfil.addActionListener(e -> mostrarPerfil());
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
        feedArea.setBackground(MainFrame.AMARELO_CLARO);
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
        card.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 2), foto.getAutor().getNome()));
        card.setBackground(MainFrame.AMARELO_CLARO);

        JLabel legendaLabel = new JLabel("<html><b>" + foto.getLegenda() + "</b></html>");
        JLabel curtidasLabel = new JLabel("Curtidas: " + foto.getCurtidas().size());

        JButton btnCurtir = new JButton("Curtir");
        JButton btnCompartilhar = new JButton("Compartilhar");

        for (JButton btn : new JButton[]{btnCurtir, btnCompartilhar}) {
            btn.setBackground(MainFrame.AMARELO_BOTAO);
            btn.setForeground(Color.DARK_GRAY);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(MainFrame.AMARELO_BORDA, 1));
            btn.setFont(new Font("Arial", Font.BOLD, 12));
        }

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
        botoes.setBackground(MainFrame.AMARELO_CLARO);
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

    private void explorarUsuarios() {
        Usuario atual = sistema.getUsuarioLogado();
        List<Usuario> usuarios = sistema.getUsuarios();

        while (true) {
            String termo = JOptionPane.showInputDialog(null, "Pesquisar usuário por nome:", "Explorar usuários da Omeletty", JOptionPane.QUESTION_MESSAGE);
            if (termo == null) return; // Cancelar

            List<Usuario> encontrados = new ArrayList<>();
            for (Usuario u : usuarios) {
                if (!u.equals(atual) && u.getNome().toLowerCase().contains(termo.toLowerCase())) {
                    encontrados.add(u);
                }
            }

            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado.");
                continue;
            }

            String[] nomes = encontrados.stream()
                    .map(u -> u.getNome() + " (" + u.getEmail() + ")")
                    .toArray(String[]::new);

            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione um usuário:",
                    "Resultados da pesquisa",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nomes,
                    nomes[0]
            );

            if (escolha == null) return; // Cancelar

            Usuario selecionado = encontrados.get(Arrays.asList(nomes).indexOf(escolha));
            mostrarPerfilDeOutroUsuario(atual, selecionado);

            int op = JOptionPane.showConfirmDialog(null, "Deseja voltar para a pesquisa?", "Explorar", JOptionPane.YES_NO_OPTION);
            if (op != JOptionPane.YES_OPTION) break;
        }
    }

    private void mostrarPerfilDeOutroUsuario(Usuario atual, Usuario usuario) {
        StringBuilder info = new StringBuilder();
        info.append("<html>");
        info.append("<h2>").append(usuario.getNome()).append("</h2>");
        info.append("<b>Email:</b> ").append(usuario.getEmail()).append("<br>");
        info.append("<b>Biografia:</b> ").append(usuario.getBiografia() != null ? usuario.getBiografia() : "Nenhuma").append("<br>");
        info.append("<b>Seguidores:</b> ").append(usuario.getSeguidores().size()).append("<br>");
        info.append("<b>Seguindo:</b> ").append(usuario.getSeguindo().size()).append("<br><br>");
        info.append("<b>Publicações:</b><br>");
        if (usuario.getPublicacoes().isEmpty()) {
            info.append("Nenhuma publicação ainda.");
        } else {
            for (dados.Foto f : usuario.getPublicacoes()) {
                info.append("- ").append(f.getLegenda()).append("<br>");
            }
        }
        info.append("</html>");

        JLabel label = new JLabel(info.toString());
        JScrollPane scroll = new JScrollPane(label);
        scroll.setPreferredSize(new Dimension(350, 200));

        boolean seguindo = atual.getSeguindo().contains(usuario);
        String acao = seguindo ? "Deixar de seguir" : "Seguir";
        Object[] opcoes = {acao, "Voltar"};

        int op = JOptionPane.showOptionDialog(
                null,
                scroll,
                "Perfil de " + usuario.getNome(),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[1]
        );

        if (op == 0) {
            if (seguindo) {
                atual.deixarDeSeguir(usuario);
                JOptionPane.showMessageDialog(null, "Você deixou de seguir " + usuario.getNome() + "!");
            } else {
                atual.seguir(usuario);
                JOptionPane.showMessageDialog(null, "Agora você segue " + usuario.getNome() + "!");
            }
            mostrarPerfilDeOutroUsuario(atual, usuario);
        }
    }

    private void mensagensUsuarios() {
        Usuario atual = sistema.getUsuarioLogado();
        List<Usuario> usuarios = sistema.getUsuarios();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Usuario u : usuarios) {
            if (!u.equals(atual)) {
                model.addElement(u.getNome() + " (" + u.getEmail() + ")");
            }
        }

        JList<String> lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int result = JOptionPane.showConfirmDialog(
            null,
            new JScrollPane(lista),
            "Mensagens - Escolha um usuário",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION && lista.getSelectedIndex() != -1) {
            Usuario selecionado = null;
            int idx = 0;
            for (Usuario u : usuarios) {
                if (!u.equals(atual)) {
                    if (idx == lista.getSelectedIndex()) {
                        selecionado = u;
                        break;
                    }
                    idx++;
                }
            }
            if (selecionado != null) {
                List<dados.Mensagem> conversas = sistema.visualizarMensagens(selecionado.getEmail());
                StringBuilder historico = new StringBuilder();
                if (conversas != null && !conversas.isEmpty()) {
                    for (dados.Mensagem m : conversas) {
                        historico.append(m.getRemetente().getNome())
                                 .append(": ")
                                 .append(m.getConteudo())
                                 .append("\n");
                    }
                } else {
                    historico.append("Nenhuma mensagem ainda.\n");
                }

                JTextArea area = new JTextArea(historico.toString(), 10, 30);
                area.setEditable(false);
                JTextField novaMsg = new JTextField();

                Object[] fields = {
                    new JLabel("Histórico:"), new JScrollPane(area),
                    new JLabel("Nova mensagem:"), novaMsg
                };

                int enviar = JOptionPane.showConfirmDialog(
                    null, fields, "Conversando com " + selecionado.getNome(), JOptionPane.OK_CANCEL_OPTION
                );

                if (enviar == JOptionPane.OK_OPTION) {
                    String conteudo = novaMsg.getText();
                    if (!conteudo.isEmpty()) {
                        sistema.enviarMensagem(selecionado.getEmail(), conteudo);
                        JOptionPane.showMessageDialog(null, "Mensagem enviada!");
                    }
                }
            }
        }
    }

    private void mostrarPerfil() {
        Usuario atual = sistema.getUsuarioLogado();
        StringBuilder info = new StringBuilder();
        info.append("<html>");
        info.append("<h2>Perfil de ").append(atual.getNome()).append("</h2>");
        info.append("<b>Email:</b> ").append(atual.getEmail()).append("<br>");
        info.append("<b>Biografia:</b> ").append(atual.getBiografia() != null ? atual.getBiografia() : "Nenhuma").append("<br>");
        info.append("<b>Seguidores:</b> ").append(atual.getSeguidores().size()).append("<br>");
        info.append("<b>Seguindo:</b> ").append(atual.getSeguindo().size()).append("<br><br>");
        info.append("<b>Publicações:</b><br>");
        if (atual.getPublicacoes().isEmpty()) {
            info.append("Nenhuma publicação ainda.");
        } else {
            for (dados.Foto f : atual.getPublicacoes()) {
                info.append("- ").append(f.getLegenda()).append("<br>");
            }
        }
        info.append("</html>");

        JLabel label = new JLabel(info.toString());
        JScrollPane scroll = new JScrollPane(label);
        scroll.setPreferredSize(new Dimension(350, 200));

        int op = JOptionPane.showOptionDialog(
            null,
            scroll,
            "Meu Perfil - Omeletty",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"Editar Perfil", "Fechar"},
            "Fechar"
        );

        if (op == 0) { // Editar Perfil
            JTextField nomeField = new JTextField(atual.getNome());
            JTextField bioField = new JTextField(atual.getBiografia() != null ? atual.getBiografia() : "");
            JTextField fotoField = new JTextField(atual.getFotoPerfil() != null ? atual.getFotoPerfil() : "");

            Object[] fields = {
                "Nome:", nomeField,
                "Biografia:", bioField,
                "Caminho da foto de perfil:", fotoField
            };

            int res = JOptionPane.showConfirmDialog(null, fields, "Editar Perfil", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                atual.setNome(nomeField.getText());
                atual.setBiografia(bioField.getText());
                atual.setFotoPerfil(fotoField.getText());
                JOptionPane.showMessageDialog(null, "Perfil atualizado!");
            }
        }
    }
}            
