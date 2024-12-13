package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignora todas as outras propriedades quem vem no body
@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosSerie(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Genre") String genre,
        @JsonAlias("Type") String type

) {}
