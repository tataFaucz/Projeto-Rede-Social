package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String fotoPerfil;
    private List<Usuario> amigos;
    private List<Foto> fotos;
    private List<Recado> recados;

    public Usuario(String nome) {
        this.nome = nome;
        this.fotoPerfil = "default.png";
        this.amigos = new ArrayList<>();
        this.fotos = new ArrayList<>();
        this.recados = new ArrayList<>();
    }

    public String getNome(){ 
        return nome; 
    }
    public String getFotoPerfil(){ 
        return fotoPerfil; 
    }
    public void setFotoPerfil(String fotoPerfil){ 
        this.fotoPerfil = fotoPerfil; 
    }
    public List<Usuario> getAmigos(){ 
        return amigos;
    }
    public List<Foto> getFotos(){ 
        return fotos; 
    }
    public List<Recado> getRecados(){ 
        return recados; 
    }
    public void adicionarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo) && !amigo.equals(this)) amigos.add(amigo);
    }
    public void removerAmigo(Usuario amigo) {
        amigos.remove(amigo);
    }
    public boolean ehAmigo(Usuario amigo) {
        return amigos.contains(amigo);
    }
    public void adicionarFoto(Foto foto){ 
        fotos.add(foto); 
    }
    public void adicionarRecado(Recado recado){ 
        recados.add(recado); 
    }
}
