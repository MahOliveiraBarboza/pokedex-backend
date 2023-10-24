package com.pokemon.marcela.infrastructure.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonType {
    @JsonProperty("name")
    private String name;

    public PokemonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
