package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.services.ConsumoApi;


import br.com.alura.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Consumir a  -- API --
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6ebba443");

		// Converter os dados para uma classe
		ConverteDados conversor = new ConverteDados();

		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=1&apikey=6ebba443");
		DadosEpisodios episode = conversor.obterDados(json, DadosEpisodios.class);


		//Exibir os dados
		System.out.println("Titulo: " + dados.title());
		System.out.println("Temporadas: " + dados.totalTemporadas());
		System.out.println("Poster: " + dados.poster());
		System.out.println("Avaliação: " + dados.rating());
		System.out.println("Genero: " + dados.genre());

		System.out.println("\n\nEpisodio: " + episode.episodeNumber());
		System.out.println("Titulo: " + episode.title());
		System.out.println("Avaliacao: " + episode.rating());
		System.out.println("Lançamento: " + episode.released());

	}
}
