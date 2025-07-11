package ui;

import javax.swing.*;
import java.awt.*;
import dados.Usuario;
import dados.Foto;
import java.util.List;

public class PhotoPanel extends JPanel {
    
    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);

    public PhotoPanel(Usuario usuario) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Omeletty - Suas fotos:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        JPanel fotosPanel = new JPanel();
        fotosPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        List<Foto> fotos = usuario.getPublicacoes();

        for (Foto foto : fotos) {
            JPanel mini = new JPanel();
            mini.setLayout(new BoxLayout(mini, BoxLayout.Y_AXIS));
            mini.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            ImageIcon icon = new ImageIcon(foto.getCaminhoArquivo());
            if (icon.getIconWidth() <= 0) {
                mini.add(new JLabel("[Imagem não encontrada]"));
            } else {
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                mini.add(new JLabel(new ImageIcon(img)));
            }
            JLabel legenda = new JLabel(foto.getLegenda());
            legenda.setHorizontalAlignment(SwingConstants.CENTER);
            mini.add(legenda);
            fotosPanel.add(mini);
        }
        add(new JScrollPane(fotosPanel), BorderLayout.CENTER);
    }
}