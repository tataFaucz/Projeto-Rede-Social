package dados;

import java.util.ArrayList;
import java.util.List;

public class Foto {
    private String caminho;
    private String legenda;
    private Usuario autor;
    private List<Usuario> curtidas = new ArrayList<>();
    private List<Usuario> compartilhamentos = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();

    public Foto(String caminho, String legenda, Usuario autor) {
        this.caminho = caminho;
        this.legenda = legenda;
        this.autor = autor;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getLegenda() {
        return legenda;
    }

    public Usuario getAutor() {
        return autor;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public List<Usuario> getCompartilhamentos() {
        return compartilhamentos;
    }

    public void adicionarCompartilhamento(Usuario usuario) {
        if (!compartilhamentos.contains(usuario)) {
            compartilhamentos.add(usuario);
        }
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
}