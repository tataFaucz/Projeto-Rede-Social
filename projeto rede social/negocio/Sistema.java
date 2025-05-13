package negocio;

import dados.Foto;
import dados.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private List<Foto> fotos;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.fotos = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarFoto(Foto foto) {
        fotos.add(foto);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public List<Foto> listarFotos() {
        return fotos;
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "usuarios=" + usuarios +
                ", fotos=" + fotos +
                '}';
    }
}