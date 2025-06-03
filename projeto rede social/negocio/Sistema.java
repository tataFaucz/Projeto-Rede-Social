package negocios;

import dados.Usuario;
/*import dados.Foto;
import dados.Recado;*/
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;

        public Sistema() {
            this.usuarios = new ArrayList<>();
        }

        public void adicionarUsuario(Usuario usuario) { usuarios.add(usuario); }
        public Usuario buscarUsuario(String nome) {
            for (Usuario u : usuarios) {
                if (u.getNome().equalsIgnoreCase(nome)) 
                return u;
            }
            return null;
        }
        public List<Usuario> listarUsuarios(){ 
            return usuarios; 
        }
        public void adicionarAmg(Usuario usuario, Usuario amg) {
        usuario.adicionarAmigo(amg);
    }

    public void removerAmg(Usuario usuario, Usuario amg) {
        usuario.removerAmigo(amg);
    }

    public List<Usuario> listarAmgs(Usuario usuario) {
        return new ArrayList<>(usuario.getAmigos());
    }
    public List<Usuario> listarNovosPerfis(Usuario usuario) {
        List<Usuario> novos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!usuario.getAmigos().contains(u) && !u.equals(usuario)) {
                novos.add(u);
            }
        }
        return novos;
    }
}
