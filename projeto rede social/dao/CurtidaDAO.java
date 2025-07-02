package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurtidaDAO {

    public void curtir(int idUsuario, int idFoto) throws SQLException {
        String sql = "INSERT INTO curtidas (id_usuario, id_foto) VALUES (?, ?) ON CONFLICT DO NOTHING";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idFoto);
            stmt.executeUpdate();
        }
    }

    public void descurtir(int idUsuario, int idFoto) throws SQLException {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_foto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idFoto);
            stmt.executeUpdate();
        }
    }
}