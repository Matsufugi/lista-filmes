package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        List<DadosTemporada> seasons = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = get.obterDados(END + nameSerie + "&season=" + i + API_KEY);
            DadosTemporada season = conversor.obterDados(json, DadosTemporada.class);

            seasons.add(season);

        }

        seasons.forEach(System.out::println);

    }
}
