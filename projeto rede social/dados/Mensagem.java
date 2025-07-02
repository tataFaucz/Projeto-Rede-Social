package dados;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Timestamp;

public class Mensagem {
    private int id; // pode ser 0 ou -1 para mensagens ainda não persistidas
    private Usuario remetente;
    private Usuario destinatario;
    private String conteudo;
    private LocalDateTime data;

    // Construtor para mensagens já no banco (com id e data)
    public Mensagem(int id, Usuario remetente, Usuario destinatario, String conteudo, LocalDateTime data) {
        this.id = id;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.data = data;
    }

    // Construtor para nova mensagem (sem id e com data atual)
    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo) {
        this(0, remetente, destinatario, conteudo, LocalDateTime.now());
    }

    // Getters
    public int getId() { return id; }
    public Usuario getRemetente() { return remetente; }
    public Usuario getDestinatario() { return destinatario; }
    public String getConteudo() { return conteudo; }
    public LocalDateTime getData() { return data; }

    // Método auxiliar para converter LocalDateTime em Timestamp para salvar no banco
    public Timestamp getTimestamp() {
        if (data == null) return null;
        return Timestamp.from(data.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Método auxiliar para criar LocalDateTime a partir de Timestamp do banco
    public static LocalDateTime fromTimestamp(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}