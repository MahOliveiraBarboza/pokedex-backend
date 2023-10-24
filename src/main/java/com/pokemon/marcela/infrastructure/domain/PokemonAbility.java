package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

//meu ability aqui representa o nome da minha habilidade em si la da api
    // portanto eu crio uma variavel name q puxo da api
    // crio a classe q recebe esse nome e passo a conseguir pegar e setar
public class PokemonAbility {
    @JsonbProperty("name")
    private String name;

    public PokemonAbility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
