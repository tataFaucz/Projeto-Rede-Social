package dao;

import dados.Mensagem;
import dados.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    public void enviar(Mensagem mensagem) throws SQLException {
        String sql = "INSERT INTO mensagens (id_remetente, id_destinatario, conteudo, data_envio) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mensagem.getRemetente().getId());
            stmt.setInt(2, mensagem.getDestinatario().getId());
            stmt.setString(3, mensagem.getConteudo());
            stmt.setTimestamp(4, mensagem.getTimestamp());
            stmt.executeUpdate();
        }
    }

    public List<Mensagem> listarMensagensEntre(Usuario remetente, Usuario destinatario) throws SQLException {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagens WHERE " +
                     "(id_remetente = ? AND id_destinatario = ?) OR " +
                     "(id_remetente = ? AND id_destinatario = ?) ORDER BY data_envio";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, remetente.getId());
            stmt.setInt(2, destinatario.getId());
            stmt.setInt(3, destinatario.getId());
            stmt.setInt(4, remetente.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mensagem m = new Mensagem(
                    rs.getInt("id"),
                    remetente.getId() == rs.getInt("id_remetente") ? remetente : destinatario,
                    remetente.getId() == rs.getInt("id_remetente") ? destinatario : remetente,
                    rs.getString("conteudo"),
                    Mensagem.fromTimestamp(rs.getTimestamp("data_envio"))
                );
                mensagens.add(m);
            }
        }
        return mensagens;
    }
}