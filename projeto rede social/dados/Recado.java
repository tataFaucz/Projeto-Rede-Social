package dados;

public class Recado {
    private String remetente;
    private String mensagem;

    public Recado(String remetente, String mensagem) {
        this.remetente = remetente;
        this.mensagem = mensagem;
    }

    public String getRemetente() { return remetente; }
    public String getMensagem() { return mensagem; }
}