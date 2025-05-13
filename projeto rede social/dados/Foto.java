package dados;

import java.time.LocalDateTime;

public class Foto {
    private String descricao;
    private String caminhoArquivo;
    private LocalDateTime dataPostagem;

    public Foto(String descricao, String caminhoArquivo, LocalDateTime dataPostagem) {
        this.descricao = descricao;
        this.caminhoArquivo = caminhoArquivo;
        this.dataPostagem = dataPostagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "descricao='" + descricao + '\'' +
                ", caminhoArquivo='" + caminhoArquivo + '\'' +
                ", dataPostagem=" + dataPostagem +
                '}';
    }
}