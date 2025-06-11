package dados;

public class Comentario {
    private String mensagem;
    private String autor;

    public Comentario(String mensagem, String autor) {
        this.mensagem = mensagem;
        this.autor = autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutor() {
        return autor;
    }
}
