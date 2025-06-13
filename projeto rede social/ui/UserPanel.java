package ui;

import javax.swing.*;
import java.awt.*;
import negocios.Sistema;
import dados.Usuario;
import dados.Foto;
import dados.Comentario;
import java.util.List;

public class UserPanel extends JPanel {
    
    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);
    
    public UserPanel(MainFrame frame, Sistema sistema, String nomeUsuario) {
        setLayout(new BorderLayout());
        Usuario usuario = sistema.buscarUsuario(nomeUsuario);

        if (usuario == null) {
            add(new JLabel("Usuário não encontrado."), BorderLayout.CENTER);
            return;
        }

        JPanel perfilPanel = new JPanel();
        perfilPanel.setBackground(new Color(255, 255, 255, 200));
        perfilPanel.setLayout(new BoxLayout(perfilPanel, BoxLayout.Y_AXIS));
        perfilPanel.setBorder(BorderFactory.createTitledBorder("Perfil"));

        ImageIcon perfilIcon = new ImageIcon(usuario.getFotoPerfil());
        if (perfilIcon.getIconWidth() <= 0) {
            perfilPanel.add(new JLabel("[Sem foto de perfil]"));
        } else {
            Image img = perfilIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            perfilPanel.add(new JLabel(new ImageIcon(img)));
        }
        JLabel nome = new JLabel(usuario.getNome());
        nome.setFont(new Font("Arial", Font.BOLD, 22));
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);
        perfilPanel.add(nome);

        JPanel fotosPanel = new JPanel();
        fotosPanel.setBorder(BorderFactory.createTitledBorder("Omellety - Fotos de " + usuario.getNome()));
        fotosPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        List<Foto> fotos = usuario.getPublicacoes();
        for (Foto foto : fotos) {
            JPanel mini = new JPanel();
            mini.setLayout(new BoxLayout(mini, BoxLayout.Y_AXIS));
            mini.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            ImageIcon fotoIcon = new ImageIcon(foto.getCaminho());
            if (fotoIcon.getIconWidth() <= 0) {
                mini.add(new JLabel("[Imagem não encontrada]"));
            } else {
                Image img = fotoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                mini.add(new JLabel(new ImageIcon(img)));
            }
            JLabel legenda = new JLabel(foto.getLegenda());
            legenda.setHorizontalAlignment(SwingConstants.CENTER);
            mini.add(legenda);

            List<Comentario> comentarios = foto.getComentarios();
            if (!comentarios.isEmpty()) {
                mini.add(new JLabel("Comentários:"));
                for (Comentario c : comentarios) {
                    mini.add(new JLabel(c.getAutor() + ": " + c.getMensagem()));
                }
            }

            fotosPanel.add(mini);
        }

        add(perfilPanel, BorderLayout.WEST);
        add(new JScrollPane(fotosPanel), BorderLayout.CENTER);
    }
}
