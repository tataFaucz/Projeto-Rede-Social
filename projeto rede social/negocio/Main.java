package negocios;

import dados.*;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Usuario alice = new Usuario("Alice");
        Usuario bob = new Usuario("Bob");
        Usuario carol = new Usuario("Carol");
        Usuario baldo = new Usuario("Baldo");

        sistema.adicionarUsuario(alice);
        sistema.adicionarUsuario(baldo);
        sistema.adicionarUsuario(bob);
        sistema.adicionarUsuario(carol);

        sistema.adicionarAmg(alice, baldo);
        sistema.adicionarAmg(bob, baldo);
        sistema.adicionarAmg(alice, bob);

        Foto fotoBob = new Foto("imagem.bob.png", "Minha primeira foto!");
        bob.adicionarFoto(fotoBob);

        Foto fotoBaldo = new Foto("imagem.baldo.png", "Lecionando!");
        baldo.adicionarFoto(fotoBaldo);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame(sistema).setVisible(true);
        });
    }
}
