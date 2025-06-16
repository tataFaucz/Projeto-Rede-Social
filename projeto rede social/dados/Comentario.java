package dados;

public class Comentario {
    private String autor;
    private String mensagem;

    public Comentario(String autor, String mensagem) {
        this.autor = autor;
        this.mensagem = mensagem;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }
}