package dao;

import dados.Foto;
import dados.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FotoDAO {

    // Inserir nova foto
    public void inserir(Foto foto, int idUsuario) throws SQLException {
        String sql = "INSERT INTO fotos (id_usuario, legenda, caminho_arquivo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, foto.getLegenda());
            stmt.setString(3, foto.getCaminhoArquivo());
            stmt.executeUpdate();
        }
    }

    public List<Foto> listarPorUsuario(int idUsuario) throws SQLException {
        List<Foto> fotos = new ArrayList<>();
        String sql = "SELECT * FROM fotos WHERE id_usuario = ? ORDER BY data_postagem DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Foto foto = new Foto(
                    rs.getInt("id"),
                    rs.getString("legenda"),
                    rs.getString("caminho_arquivo"),
                    rs.getTimestamp("data_postagem")
                );
                fotos.add(foto);
            }
        }
        return fotos;
    }

    public void excluir(int idFoto) throws SQLException {
        String sql = "DELETE FROM fotos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFoto);
            stmt.executeUpdate();
        }
    }
}