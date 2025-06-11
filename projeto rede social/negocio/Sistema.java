package negocios;

import dados.Usuario;
import dados.Foto;
import dados.Mensagem;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;

public boolean criarConta(String nome, String email, String senha) {
    for (Usuario u : usuarios) {
        if (u.getEmail().equalsIgnoreCase(email)) {
            return false; 
        }
    }
    Usuario novo = new Usuario(nome, email, senha, null);
    usuarios.add(novo);
    return true;
}

    public boolean login(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public Usuario buscarUsuario(String emailOuNome) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(emailOuNome) || u.getNome().equalsIgnoreCase(emailOuNome)) {
                return u;
            }
        }
        return null;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public boolean publicarFoto(String caminhoImagem, String legenda) {
        if (usuarioLogado == null) return false;
        Foto foto = new Foto(caminhoImagem, legenda, usuarioLogado);
        usuarioLogado.adicionarPublicacao(foto);
        return true;
    }

    public boolean curtirFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) return false;
        return foto.curtir(usuarioLogado);
    }

    public boolean compartilharFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) return false;
        return foto.compartilhar(usuarioLogado);
    }

    public boolean enviarMensagem(String emailDestinatario, String conteudo) {
        if (usuarioLogado == null) return false;
        Usuario destinatario = buscarUsuario(emailDestinatario);
        if (destinatario != null) {
            usuarioLogado.enviarMensagem(destinatario, conteudo);
            return true;
        }
        return false;
    }

    public List<Mensagem> visualizarMensagens(String emailOutro) {
        Usuario outro = buscarUsuario(emailOutro);
        if (usuarioLogado == null || outro == null) return null;

        List<Mensagem> conversas = new ArrayList<>();
        for (Mensagem m : usuarioLogado.getMensagensEnviadas()) {
            if (m.getDestinatario().equals(outro)) {
                conversas.add(m);
            }
        }
        for (Mensagem m : usuarioLogado.getMensagensRecebidas()) {
            if (m.getRemetente().equals(outro)) {
                conversas.add(m);
            }
        }
        conversas.sort((a, b) -> a.getDataHora().compareTo(b.getDataHora()));
        return conversas;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
