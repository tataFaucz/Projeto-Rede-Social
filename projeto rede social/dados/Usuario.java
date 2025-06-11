package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String fotoPerfil;
    private List<Usuario> seguindo;
    private List<Usuario> seguidores;
    private List<Foto> publicacoes;
    private List<Mensagem> mensagensEnviadas;
    private List<Mensagem> mensagensRecebidas;

    public Usuario(String nome, String email, String senha, String fotoPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.seguindo = new ArrayList<>();
        this.seguidores = new ArrayList<>();
        this.publicacoes = new ArrayList<>();
        this.mensagensEnviadas = new ArrayList<>();
        this.mensagensRecebidas = new ArrayList<>();
    }

    public String getNome(){ 
        return nome; 
    }
    public String getEmail(){ 
        return email; 
    }
    public String getSenha() { 
        return senha; 
    }
    public String getFotoPerfil() { 
        return fotoPerfil; 
    }
    public List<Usuario> getSeguindo() { 
        return seguindo; 
    }
    public List<Usuario> getSeguidores() { 
        return seguidores; 
    }
    public List<Foto> getPublicacoes() { 
        return publicacoes; 
    }

    public boolean seguir(Usuario outro) {
        if (!seguindo.contains(outro) && !outro.equals(this)) {
            seguindo.add(outro);
            outro.seguidores.add(this);
            return true;
        }
        return false;
    }

    public boolean deixarDeSeguir(Usuario outro) {
        if (seguindo.remove(outro)) {
            outro.seguidores.remove(this);
            return true;
        }
        return false;
    }

    public void adicionarPublicacao(Foto foto) {
        publicacoes.add(foto);
    }

    public void enviarMensagem(Usuario destinatario, String conteudo) {
        Mensagem mensagem = new Mensagem(this, destinatario, conteudo);
        mensagensEnviadas.add(mensagem);
        destinatario.receberMensagem(mensagem);
    }

    private void receberMensagem(Mensagem mensagem) {
        mensagensRecebidas.add(mensagem);
    }

    public List<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    @Override
    public String toString() {
        return nome + " (" + email + ")";
    }
}
