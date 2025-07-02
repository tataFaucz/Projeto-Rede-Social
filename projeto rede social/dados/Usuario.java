package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id; 
    private String nome;
    private String email; 
    private String senha;
    private String biografia;
    private String fotoPerfil;

    private List<Usuario> seguidores;
    private List<Usuario> seguindo;
    private List<Foto> publicacoes;
    private List<Mensagem> mensagensEnviadas;
    private List<Mensagem> mensagensRecebidas;

    // Construtor completo (usado ao recuperar do banco)
    public Usuario(int id, String nome, String email, String senha, String fotoPerfil, String biografia) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
        this.seguidores = new ArrayList<>();
        this.seguindo = new ArrayList<>();
        this.publicacoes = new ArrayList<>();
        this.mensagensEnviadas = new ArrayList<>();
        this.mensagensRecebidas = new ArrayList<>();
    }

    // Construtor sem ID (usado ao cadastrar um novo usuário)
    public Usuario(String nome, String email, String senha, String fotoPerfil, String biografia) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
        this.seguidores = new ArrayList<>();
        this.seguindo = new ArrayList<>();
        this.publicacoes = new ArrayList<>();
        this.mensagensEnviadas = new ArrayList<>();
        this.mensagensRecebidas = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public List<Usuario> getSeguindo() {
        return seguindo;
    }

    public List<Foto> getPublicacoes() {
        return publicacoes;
    }

    public void adicionarPublicacao(Foto foto) {
        publicacoes.add(foto);
    }

    public void seguir(Usuario usuario) {
        if (!seguindo.contains(usuario) && !usuario.equals(this)) {
            seguindo.add(usuario);
            usuario.seguidores.add(this);
        }
    }

    public void deixarDeSeguir(Usuario usuario) {
        if (seguindo.contains(usuario)) {
            seguindo.remove(usuario);
            usuario.seguidores.remove(this);
        }
    }

    public void enviarMensagem(Usuario destinatario, String conteudo) {
        Mensagem mensagem = new Mensagem(this, destinatario, conteudo);
        mensagensEnviadas.add(mensagem);
        destinatario.mensagensRecebidas.add(mensagem);
    }

    public List<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }
}
