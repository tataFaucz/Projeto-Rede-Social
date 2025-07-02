package negocios;

import dados.Foto;
import dados.Usuario;
import dados.Comentario;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Usuario alice = new Usuario("Alice", "alice@email.com", "123", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_Qhl0p5rg83hXO3CrWNY-olr6K7xoIMhSoQ&s", "Alice no país das maravilhas");
        Usuario bob = new Usuario("Bob", "bob@email.com", "123", "https://i.pinimg.com/236x/94/36/1e/94361e0ddf929122c5438a36235d44b5.jpg", "Bob, o construtor");
        Usuario carol = new Usuario("Carol", "carol@email.com", "123", "https://i.pinimg.com/originals/a8/65/77/a86577eeaa8d0c3bb3d419defa71fe81.jpg", "🌠💫🌟");
        Usuario baldo = new Usuario("Baldo", "baldo@email.com", "123", "https://media.licdn.com/dms/image/v2/D4D03AQHTPPo3IlbPVg/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1668387986400?e=2147483647&v=beta&t=4SEyWKkONJ4e13RAmNwHUoa0STRgtcO1xXEgqBrx_yE", "Professor - UDESC CCT");

        sistema.getUsuarios().add(alice);
        sistema.getUsuarios().add(bob);
        sistema.getUsuarios().add(carol);
        sistema.getUsuarios().add(baldo);

        alice.seguir(baldo);
        bob.seguir(baldo);
        alice.seguir(bob);
        alice.seguir(carol);

        Foto fotoBob1 = new Foto("images/imagem.bob.png", "Minha primeira foto!", bob);
        bob.adicionarPublicacao(fotoBob1);

        Foto fotoBaldo1 = new Foto("https://www1.udesc.br/agencia/arquivos/22969/images/69465375_492298808222914_8507880908257230848_o.jpg", "Muito feliz em participar do JEDI", baldo);
        baldo.adicionarPublicacao(fotoBaldo1);
        Foto fotoCarol1 = new Foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNu8AKGC7HN7KAbfnVw_nwzraNRQ1LY0VbeQ&s", "Meu lugar!", carol);
        carol.adicionarPublicacao(fotoCarol1);
        Foto fotoCarol2 = new Foto("https://gataempreendedora.com/wp-content/uploads/2024/07/Aesthetic-Girl.jpg", "I ❤️ Aesthetics", carol);
        carol.adicionarPublicacao(fotoCarol2);
        alice.enviarMensagem(bob, "Oi Bob, tudo bem?");
        bob.enviarMensagem(alice, "Oi Alice! Tudo ótimo e você?");

        Comentario comentario1 = new Comentario("Parabéns pelo projeto!", alice.getNome());
        fotoBaldo1.getComentarios().add(comentario1);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame(sistema).setVisible(true);
        });
    }
}