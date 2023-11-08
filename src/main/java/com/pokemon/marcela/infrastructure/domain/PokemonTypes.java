package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonTypes {
    @JsonbProperty("type")
    private PokemonType name;

    public PokemonTypes(PokemonType name) {
        this.name = name;
    }

    public PokemonTypes() {
    }

    public PokemonType getName(){
        return name;
    }

    public void setName(PokemonType name) {
        this.name = name;
    }
}
