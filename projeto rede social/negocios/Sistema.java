package negocios;

import dados.Usuario;
import dados.Foto;
import dados.Comentario;
import dados.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;

    public Sistema() {
        usuarios = new ArrayList<>();
        usuarioLogado = null;
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String fotoPerfil, String biografia) {
        if (buscarUsuario(email) != null) return false;
        usuarios.add(new Usuario(nome, email, senha, fotoPerfil, biografia));
        return true;
    }

    public boolean login(String email, String senha) {
        Usuario u = buscarUsuario(email);
        if (u != null && u.getSenha().equals(senha)) {
            usuarioLogado = u;
            return true;
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuario(String emailOuNome) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(emailOuNome) || u.getNome().equalsIgnoreCase(emailOuNome)) {
                return u;
            }
        }
        return null;
    }

    public boolean publicarFoto(String caminho, String legenda) {
        if (usuarioLogado == null) return false;
        Foto foto = new Foto(caminho, legenda, usuarioLogado);
        usuarioLogado.adicionarPublicacao(foto);
        return true;
    }

    public boolean curtirFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) return false;
        if (!foto.getCurtidas().contains(usuarioLogado)) {
            foto.getCurtidas().add(usuarioLogado);
            return true;
        }
        return false;
    }

    public boolean compartilharFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) return false;
        if (!foto.getCompartilhamentos().contains(usuarioLogado)) {
            foto.adicionarCompartilhamento(usuarioLogado);
            return true;
        }
        return false;
    }

    public void comentarFoto(Foto foto, String texto) {
        if (usuarioLogado != null && foto != null && texto != null && !texto.isEmpty()) {
            foto.adicionarComentario(new Comentario(usuarioLogado.getNome(), texto));
        }
    }

    public void enviarMensagem(String emailDestino, String conteudo) {
        Usuario destino = buscarUsuario(emailDestino);
        if (usuarioLogado != null && destino != null && conteudo != null && !conteudo.isEmpty()) {
            usuarioLogado.enviarMensagem(destino, conteudo);
        }
    }

    public List<Mensagem> visualizarMensagens(String emailOutroUsuario) {
        Usuario outro = buscarUsuario(emailOutroUsuario);
        if (usuarioLogado == null || outro == null) return null;
        List<Mensagem> todas = new ArrayList<>();
        for (Mensagem m : usuarioLogado.getMensagensEnviadas()) {
            if (m.getDestinatario().equals(outro)) todas.add(m);
        }
        for (Mensagem m : usuarioLogado.getMensagensRecebidas()) {
            if (m.getRemetente().equals(outro)) todas.add(m);
        }
        todas.sort((a, b) -> a.getData().compareTo(b.getData()));
        return todas;
    }
}