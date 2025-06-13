package ui;

import javax.swing.*;
import java.awt.*;
import negocios.Sistema;
import dados.Usuario;
import java.util.List;

public class ExplorePanel extends JPanel {
    
    public static final Color AMARELO_CLARO = new Color(255, 249, 196);
    public static final Color AMARELO_MEDIO = new Color(255, 235, 59);
    public static final Color AMARELO_ESCURO = new Color(255, 193, 7);
    public static final Color AMARELO_BOTAO = new Color(255, 213, 79);
    public static final Color AMARELO_BORDA = new Color(255, 179, 0);
    
    public ExplorePanel(Sistema sistema) {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Explorar Usuários", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setLayout(new BoxLayout(usuariosPanel, BoxLayout.Y_AXIS));

        Usuario logado = sistema.getUsuarioLogado();
        List<Usuario> usuarios = sistema.getUsuarios();

        for (Usuario usuario : usuarios) {
            if (logado != null && usuario.equals(logado)) continue;
            usuariosPanel.add(new JLabel(usuario.getNome()));
        }

        add(new JScrollPane(usuariosPanel), BorderLayout.CENTER);
    }
}
