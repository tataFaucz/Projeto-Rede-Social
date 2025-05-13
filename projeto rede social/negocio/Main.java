package negocio;

import dados.Foto;
import dados.Usuario;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Usuario usuario1 = new Usuario("Alice", "alice@email.com", "senha123");
        Usuario usuario2 = new Usuario("Liz", "liz@email.com", "senha456");
        sistema.adicionarUsuario(usuario1);
        sistema.adicionarUsuario(usuario2);

        Foto foto1 = new Foto("Foto na praia", "/imagens/praia.jpg", LocalDateTime.now());
        Foto foto2 = new Foto("Foto no parque", "/imagens/parque.jpg", LocalDateTime.now());
        sistema.adicionarFoto(foto1);
        sistema.adicionarFoto(foto2);

        System.out.println("Usuários cadastrados:");
        for (Usuario u : sistema.listarUsuarios()) {
            System.out.println(u);
        }

        System.out.println("\nFotos postadas:");
        for (Foto f : sistema.listarFotos()) {
            System.out.println(f);
        }
    }
}