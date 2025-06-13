package negocios;

import dados.Foto;
import dados.Usuario;
import dados.Comentario;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Usuario alice = new Usuario("Alice", "alice@email.com", "123", null);
        Usuario bob = new Usuario("Bob", "bob@email.com", "123", null);
        Usuario carol = new Usuario("Carol", "carol@email.com", "123", null);
        Usuario baldo = new Usuario("Baldo", "baldo@email.com", "123", null);

        sistema.getUsuarios().add(alice);
        sistema.getUsuarios().add(bob);
        sistema.getUsuarios().add(carol);
        sistema.getUsuarios().add(baldo);

        alice.seguir(baldo);
        bob.seguir(baldo);
        alice.seguir(bob);
        alice.seguir(carol);

        Foto fotoBob = new Foto("imagem.bob.png", "Minha primeira foto!", bob);
        bob.adicionarPublicacao(fotoBob);

        Foto fotoBaldo = new Foto("imagem.baldo.png", "Lecionando!!!", baldo);
        baldo.adicionarPublicacao(fotoBaldo);
        Foto fotoCarol = new Foto("imagem.carol.png", "Carol na praia!", carol);
        carol.adicionarPublicacao(fotoCarol);

        alice.enviarMensagem(bob, "Oi Bob, tudo bem?");
        bob.enviarMensagem(alice, "Oi Alice! Tudo ótimo e você?");

        Comentario comentario1 = new Comentario("Parabéns pelo projeto!", alice.getNome());
        fotoBob.getComentarios().add(comentario1);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame(sistema).setVisible(true);
        });
    }
}
