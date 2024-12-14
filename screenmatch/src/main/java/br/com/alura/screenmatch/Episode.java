package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodios;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private String dataDeLancamento;

    public Episode(Integer seasonTemporada, DadosEpisodios dadosEpisodio) {
            this.temporada = seasonTemporada;
            this.titulo = dadosEpisodio.title();
            this.numeroEpisodio = dadosEpisodio.episodeNumber();

            try {
                this.avaliacao = Double.valueOf(dadosEpisodio.rating());
            } catch(NumberFormatException e) {
                this.avaliacao = 0.0;
            }

            try {
                this.dataDeLancamento = dadosEpisodio.released();
            } catch (DateTimeException e) {
                this.dataDeLancamento = null;
            }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliaca(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(String dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String toString(){
        return "\nTitulo: " + getTitulo() +
                "\nTemporada: " + getTemporada() +
                "\nEpisodio: " + getNumeroEpisodio() +
                "\nAvaliação: " + getAvaliacao() +
                "\nLançamento: " + getDataDeLancamento();
    }
}
