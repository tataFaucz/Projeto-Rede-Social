package dados;

import java.time.LocalDateTime;

public class Mensagem {
    private Usuario remetente;
    private Usuario destinatario;
    private String conteudo;
    private LocalDateTime dataHora;

    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.dataHora = LocalDateTime.now();
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + remetente.getNome() + ": " + conteudo;
    }
}
