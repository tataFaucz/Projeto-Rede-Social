package dados;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Set<Usuario> seguindo; 
    private Set<Usuario> seguidores; 

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.seguindo = new HashSet<>();
        this.seguidores = new HashSet<>();
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

    public Set<Usuario> getSeguindo() {
        return seguindo;
    }

    public Set<Usuario> getSeguidores() {
        return seguidores;
    }

    public void seguir(Usuario usuario) {
        if (usuario != null && !this.equals(usuario)) {
            this.seguindo.add(usuario);
            usuario.seguidores.add(this);
        }
    }

    public void deixarDeSeguir(Usuario usuario) {
        if (usuario != null && this.seguindo.contains(usuario)) {
            this.seguindo.remove(usuario);
            usuario.seguidores.remove(this);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", seguindo=" + seguindo.size() +
                ", seguidores=" + seguidores.size() +
                '}';
    }
}