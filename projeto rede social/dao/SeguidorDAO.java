package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguidorDAO {

    public void seguir(int idSeguidor, int idSeguido) throws SQLException {
        String sql = "INSERT INTO seguidores (id_seguidor, id_seguido) VALUES (?, ?) ON CONFLICT DO NOTHING";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSeguidor);
            stmt.setInt(2, idSeguido);
            stmt.executeUpdate();
        }
    }

    public void deixarDeSeguir(int idSeguidor, int idSeguido) throws SQLException {
        String sql = "DELETE FROM seguidores WHERE id_seguidor = ? AND id_seguido = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSeguidor);
            stmt.setInt(2, idSeguido);
            stmt.executeUpdate();
        }
    }

    public List<Integer> listarSeguidores(int idSeguido) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_seguidor FROM seguidores WHERE id_seguido = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSeguido);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("id_seguidor"));
                }
            }
        }
        return ids;
    }

    public List<Integer> listarSeguindo(int idSeguidor) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_seguido FROM seguidores WHERE id_seguidor = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSeguidor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("id_seguido"));
                }
            }
        }
        return ids;
    }
}
