package dados;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Comentario {
    private int id;                 
    private String autor;           
    private String mensagem;        
    private LocalDateTime dataComentario;  

    public Comentario(int id, String autor, String mensagem, LocalDateTime dataComentario) {
        this.id = id;
        this.autor = autor;
        this.mensagem = mensagem;
        this.dataComentario = dataComentario;
    }

    public Comentario(String autor, String mensagem) {
        this(0, autor, mensagem, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    // Para converter LocalDateTime em Timestamp ao salvar no banco
    public Timestamp getTimestamp() {
        if (dataComentario == null) return null;
        return Timestamp.from(dataComentario.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Para converter Timestamp do banco em LocalDateTime ao carregar
    public static LocalDateTime fromTimestamp(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}