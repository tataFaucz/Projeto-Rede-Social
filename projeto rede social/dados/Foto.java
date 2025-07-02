package dados;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Foto {
    private int id;  // ID da foto no banco
    private String caminho;
    private String legenda;
    private Timestamp dataPostagem;
    private Usuario autor;

    private List<Usuario> curtidas = new ArrayList<>();
    private List<Usuario> compartilhamentos = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();

    // Construtor para carregar do banco (com id e data)
    public Foto(int id, String legenda, String caminho, Timestamp dataPostagem) {
        this.id = id;
        this.legenda = legenda;
        this.caminho = caminho;
        this.dataPostagem = dataPostagem;
    }

    // Construtor para inserir nova foto (sem id e data)
    public Foto(String caminho, String legenda, Usuario autor) {
        this.caminho = caminho;
        this.legenda = legenda;
        this.autor = autor;
    }

    // Getters e Setters básicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaminhoArquivo() {
        return caminho;
    }

    public void setCaminhoArquivo(String caminho) {
        this.caminho = caminho;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public Timestamp getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(Timestamp dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    // Interações da rede social
    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void adicionarCurtida(Usuario usuario) {
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
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