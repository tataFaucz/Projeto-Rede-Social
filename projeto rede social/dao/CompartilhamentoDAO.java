package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompartilhamentoDAO {

    public void compartilhar(int idUsuario, int idFoto) throws SQLException {
        String sql = "INSERT INTO compartilhamentos (id_usuario, id_foto) VALUES (?, ?) ON CONFLICT DO NOTHING";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idFoto);
            stmt.executeUpdate();
        }
    }

    public void removerCompartilhamento(int idUsuario, int idFoto) throws SQLException {
        String sql = "DELETE FROM compartilhamentos WHERE id_usuario = ? AND id_foto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idFoto);
            stmt.executeUpdate();
        }
    }

    public List<Integer> listarUsuariosQueCompartilharam(int idFoto) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_usuario FROM compartilhamentos WHERE id_foto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFoto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("id_usuario"));
                }
            }
        }
        return ids;
    }
}
