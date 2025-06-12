package negocios;

import dados.Foto;
import dados.Usuario;
import dados.Comentario;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // Criação dos usuários (nome, email, senha)
        Usuario alice = new Usuario("Alice", "alice@email.com", "123", null);
        Usuario bob = new Usuario("Bob", "bob@email.com", "123", null);
        Usuario carol = new Usuario("Carol", "carol@email.com", "123", null);
        Usuario baldo = new Usuario("Baldo", "baldo@email.com", "123", null);

        // Cadastro dos usuários no sistema
        sistema.getUsuarios().add(alice);
        sistema.getUsuarios().add(bob);
        sistema.getUsuarios().add(carol);
        sistema.getUsuarios().add(baldo);

        // Relações de seguindo
        alice.seguir(baldo);
        bob.seguir(baldo);
        alice.seguir(bob);

        // Publicações
        Foto fotoBob = new Foto("imagem.bob.png", "Minha primeira foto!", bob);
        bob.adicionarPublicacao(fotoBob);

        Foto fotoBaldo = new Foto("imagem.baldo.png", "Minha primeira foto!", baldo);
        baldo.adicionarPublicacao(fotoBaldo);

        // Envio de mensagens
        alice.enviarMensagem(bob, "Oi Bob, tudo bem?");
        bob.enviarMensagem(alice, "Oi Alice! Tudo ótimo e você?");

        // Comentários (adicionando comentário à foto do Bob)
        Comentario comentario1 = new Comentario("Parabéns pelo projeto!", alice.getNome());
        fotoBob.getComentarios().add(comentario1);

        // Inicialização da interface gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame(sistema).setVisible(true);
        });
    }
}
