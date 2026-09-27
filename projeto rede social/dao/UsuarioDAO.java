package dao;

import dados.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // CREATE - Cadastrar novo usuário
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, login, senha, caminho_foto_perfil, biografia) VALUES (?, ?, ?, ?, ?)";

           try (Connection conn = ConnectionFactory.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail()); // email como login
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getFotoPerfil());
            stmt.setString(5, usuario.getBiografia());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    usuario.setId(keys.getInt(1));
                }
            }
        }
    }

    // READ - Buscar usuário por login (email) e senha
    public Usuario buscarPorLogin(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("caminho_foto_perfil"),
                    rs.getString("biografia")
                );
            }
        }
        return null;
    }

    public boolean existeLogin(String email) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM usuarios WHERE LOWER(login) = LOWER(?))";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getBoolean(1);
            }
        }
    }

    // UPDATE - Atualizar dados do usuário
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, senha = ?, caminho_foto_perfil = ?, biografia = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getFotoPerfil());
            stmt.setString(4, usuario.getBiografia());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE - Excluir usuário
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // LISTAR TODOS
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("caminho_foto_perfil"),
                    rs.getString("biografia")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}