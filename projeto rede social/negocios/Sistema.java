package negocios;

import dao.ComentarioDAO;
import dao.CurtidaDAO;
import dao.FotoDAO;
import dao.MensagemDAO;
import dao.SeguidorDAO;
import dao.CompartilhamentoDAO;
import dao.UsuarioDAO;
import dados.Comentario;
import dados.Foto;
import dados.Mensagem;
import dados.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FotoDAO fotoDAO = new FotoDAO();
    private final ComentarioDAO comentarioDAO = new ComentarioDAO();
    private final MensagemDAO mensagemDAO = new MensagemDAO();
    private final CurtidaDAO curtidaDAO = new CurtidaDAO();
    private final SeguidorDAO seguidorDAO = new SeguidorDAO();
    private final CompartilhamentoDAO compartilhamentoDAO = new CompartilhamentoDAO();

    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogado;

    public Sistema() {
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        try {
            usuarios = usuarioDAO.listarTodos();
        } catch (SQLException e) {
            usuarios = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private void carregarDadosUsuario(Usuario usuario) {
        if (usuario == null) {
            return;
        }

        usuario.getPublicacoes().clear();
        usuario.getSeguindo().clear();
        usuario.getSeguidores().clear();
        usuario.getMensagensEnviadas().clear();
        usuario.getMensagensRecebidas().clear();

        try {
            for (Foto foto : fotoDAO.listarPorUsuario(usuario.getId())) {
                foto.setAutor(usuario);
                usuario.adicionarPublicacao(foto);
            }

            for (int idSeguido : seguidorDAO.listarSeguindo(usuario.getId())) {
                Usuario seguido = buscarUsuarioPorId(idSeguido);
                if (seguido != null) {
                    usuario.getSeguindo().add(seguido);
                }
            }

            for (int idSeguidor : seguidorDAO.listarSeguidores(usuario.getId())) {
                Usuario seguidor = buscarUsuarioPorId(idSeguidor);
                if (seguidor != null) {
                    usuario.getSeguidores().add(seguidor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarUsuario(String nome, String email, String senha, String fotoPerfil, String biografia)
            throws SQLException {
        if (usuarioDAO.existeLogin(email)) {
            return false;
        }

        Usuario usuario = new Usuario(nome, email.trim(), senha, fotoPerfil, biografia);
        try {
            usuarioDAO.inserir(usuario);
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return false;
            }
            throw e;
        }

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
        return true;
    }

    public boolean login(String email, String senha) {
        try {
            Usuario usuario = usuarioDAO.buscarPorLogin(email, senha);
            if (usuario != null) {
                usuarioLogado = usuario;
                carregarDadosUsuario(usuario);
                if (!usuarios.contains(usuario)) {
                    usuarios.add(usuario);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        if (usuarios == null || usuarios.isEmpty()) {
            carregarUsuarios();
        }
        return usuarios;
    }

    public Usuario buscarUsuario(String emailOuNome) {
        carregarUsuarios();
        for (Usuario u : getUsuarios()) {
            if (u.getEmail().equalsIgnoreCase(emailOuNome) || u.getNome().equalsIgnoreCase(emailOuNome)) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : getUsuarios()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public boolean publicarFoto(String caminho, String legenda) {
        if (usuarioLogado == null) {
            return false;
        }

        try {
            Foto foto = new Foto(caminho, legenda, usuarioLogado);
            fotoDAO.inserir(foto, usuarioLogado.getId());
            foto.setAutor(usuarioLogado);
            usuarioLogado.adicionarPublicacao(foto);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean curtirFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) {
            return false;
        }

        try {
            curtidaDAO.curtir(usuarioLogado.getId(), foto.getId());
            foto.adicionarCurtida(usuarioLogado);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean compartilharFoto(Foto foto) {
        if (usuarioLogado == null || foto == null) {
            return false;
        }

        try {
            compartilhamentoDAO.compartilhar(usuarioLogado.getId(), foto.getId());
            foto.adicionarCompartilhamento(usuarioLogado);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void comentarFoto(Foto foto, String texto) {
        if (usuarioLogado == null || foto == null || texto == null || texto.trim().isEmpty()) {
            return;
        }

        try {
            Comentario comentario = new Comentario(usuarioLogado.getNome(), texto.trim());
            comentarioDAO.inserir(comentario, usuarioLogado.getId(), foto.getId());
            foto.adicionarComentario(comentario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagem(String emailDestino, String conteudo) {
        Usuario destino = buscarUsuario(emailDestino);
        if (usuarioLogado == null || destino == null || conteudo == null || conteudo.trim().isEmpty()) {
            return;
        }

        try {
            Mensagem mensagem = new Mensagem(usuarioLogado, destino, conteudo.trim());
            mensagemDAO.enviar(mensagem);
            usuarioLogado.enviarMensagem(destino, conteudo.trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mensagem> visualizarMensagens(String emailOutroUsuario) {
        Usuario outro = buscarUsuario(emailOutroUsuario);
        if (usuarioLogado == null || outro == null) {
            return null;
        }

        try {
            return mensagemDAO.listarMensagensEntre(usuarioLogado, outro);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean seguirUsuario(Usuario usuario) {
        if (usuarioLogado == null || usuario == null || usuario.getId() == usuarioLogado.getId()) {
            return false;
        }

        try {
            seguidorDAO.seguir(usuarioLogado.getId(), usuario.getId());
            usuarioLogado.seguir(usuario);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deixarDeSeguirUsuario(Usuario usuario) {
        if (usuarioLogado == null || usuario == null || usuario.getId() == usuarioLogado.getId()) {
            return false;
        }

        try {
            seguidorDAO.deixarDeSeguir(usuarioLogado.getId(), usuario.getId());
            usuarioLogado.deixarDeSeguir(usuario);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}