package dados;

import java.util.ArrayList;
import java.util.List;

public class Foto {
    private String caminho;
    private String legenda;
    private List<String> comentarios;

    public Foto(String caminho, String legenda) {
        this.caminho = caminho;
        this.legenda = legenda;
        this.comentarios = new ArrayList<>();
    }

    public String getCaminho() { return caminho; }
    public String getLegenda() { return legenda; }
    public List<String> getComentarios() { return comentarios; }
    public void adicionarComentario(String comentario) { comentarios.add(comentario); }
}
