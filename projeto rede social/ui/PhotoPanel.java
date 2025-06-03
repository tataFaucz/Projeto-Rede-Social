package ui;

import javax.swing.*;
import java.awt.*;
import dados.Usuario;
import dados.Foto;
import java.util.List;

public class PhotoPanel extends JPanel {
    public PhotoPanel(Usuario usuario) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Floguinho - Suas fotos:");
        add(label, BorderLayout.NORTH);

        JPanel fotosPanel = new JPanel();
        fotosPanel.setLayout(new FlowLayout());
        List<Foto> fotos = usuario.getFotos();
        for (Foto foto : fotos) {
            JPanel mini = new JPanel();
            mini.setLayout(new BoxLayout(mini, BoxLayout.Y_AXIS));
            mini.add(new JLabel(new ImageIcon(foto.getCaminho())));
            mini.add(new JLabel(foto.getLegenda()));
            fotosPanel.add(mini);
        }
        add(new JScrollPane(fotosPanel), BorderLayout.CENTER);
    }
}