package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.Episode;
import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final String END = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6ebba443";
    private ConsumoApi get = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    Scanner read = new Scanner(System.in);

    public void showMenu() throws IOException, InterruptedException {
        System.out.println("Digite o nome da serie: ");
        var nameSerie = read.nextLine().toLowerCase().replace(" ", "+");
        var json = get.obterDados(END + nameSerie + API_KEY);

        // Converter os dados para uma classe
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        System.out.println(dados);


        // Cria uma lista de temporadas
        List<DadosTemporada> seasons = new ArrayList<>();

        // Passa por cada temporada + pega todos os episodios e armazena na lista
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = get.obterDados(END + nameSerie + "&season=" + i + API_KEY);
            DadosTemporada season = conversor.obterDados(json, DadosTemporada.class);

            seasons.add(season);
        }

        // Exibe todos os episodios de cada temporada
        seasons.forEach(System.out::println);

        // Percorre o array de temporadas -> percorre os episodios de cada temporada -> exibe todos os titulos de cada episodio
        //seasons.forEach(s -> s.episodios().forEach(
        //        e -> System.out.println(e.title())
        //));


        //List<DadosEpisodios> dadosEpisodios = seasons.stream()
        //        .flatMap(t -> t.episodios().stream())
        //        .collect(Collectors.toList());

        //dadosEpisodios.stream().filter(e -> !e.rating().equalsIgnoreCase("N/A"))
        //        .sorted(Comparator.comparing(DadosEpisodios::rating).reversed())
        //        .limit(5)
        //        .forEach(System.out::println);


        List<Episode> eps = seasons.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episode(t.season(), d)))
                        .collect(Collectors.toList());

        eps.forEach(System.out::println);
    }
}


