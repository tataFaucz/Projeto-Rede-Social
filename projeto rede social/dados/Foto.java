package dados;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Foto {
    private String caminho;
    private String legenda;
    private Usuario autor;
    private List<Comentario> comentarios = new ArrayList<>();
    private Set<Usuario> curtidas = new HashSet<>();
    private Set<Usuario> compartilhamentos = new HashSet<>();

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

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Set<Usuario> getCurtidas() {
        return curtidas;
    }

    public boolean curtir(Usuario usuario) {
        return curtidas.add(usuario); // true se curtiu, false se já tinha curtido
    }

    public boolean compartilhar(Usuario usuario) {
        return compartilhamentos.add(usuario); // true se compartilhou, false se já tinha compartilhado
    }

    public int getTotalCurtidas() {
        return curtidas.size();
    }

    public int getTotalCompartilhamentos() {
        return compartilhamentos.size();
    }
}
