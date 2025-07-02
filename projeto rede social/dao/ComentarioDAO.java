package dao;

import dados.Comentario;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    public void inserir(Comentario comentario, int idUsuario, int idFoto) throws SQLException {
        String sql = "INSERT INTO comentarios (id_usuario, id_foto, conteudo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idFoto);
            stmt.setString(3, comentario.getMensagem()); // usa getMensagem, não getConteudo
            stmt.executeUpdate();
        }
    }

    public List<Comentario> listarPorFoto(int idFoto) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        // Consulta juntando tabela usuarios para pegar nome do autor
        String sql = "SELECT c.id, u.nome AS autor, c.conteudo, c.data_comentario " +
                     "FROM comentarios c " +
                     "JOIN usuarios u ON c.id_usuario = u.id " +
                     "WHERE c.id_foto = ? " +
                     "ORDER BY c.data_comentario ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFoto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String autor = rs.getString("autor");
                String mensagem = rs.getString("conteudo");
                Timestamp ts = rs.getTimestamp("data_comentario");
                LocalDateTime dataComentario = Comentario.fromTimestamp(ts);

                Comentario comentario = new Comentario(id, autor, mensagem, dataComentario);
                comentarios.add(comentario);
            }
        }
        return comentarios;
    }
}