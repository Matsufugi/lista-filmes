package br.com.alura.screenmatch.services;

import br.com.alura.screenmatch.model.DadosSerie;

public interface IConverteDados {

    // Generics "Não sei o que vira mas deve-se ter alguma coisa"
    <T> T obterDados(String json, Class<T> classe);

}
