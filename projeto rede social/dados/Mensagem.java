package dados;

import java.time.LocalDateTime;

public class Mensagem {
    private Usuario remetente;
    private Usuario destinatario;
    private String conteudo;
    private LocalDateTime data;

    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.data = LocalDateTime.now();
    }

    public Usuario getRemetente(){ 
        return remetente; 
    }
    public Usuario getDestinatario(){ 
        return destinatario; 
    }
    public String getConteudo(){ 
        return conteudo; 
    }
    public LocalDateTime getData(){ 
        return data; 
    }
}