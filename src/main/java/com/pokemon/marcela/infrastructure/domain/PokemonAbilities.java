package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonAbilities {
    @JsonbProperty("ability")
    private PokemonAbility name;

    public PokemonAbilities(PokemonAbility name) {
         this.name = name;
    }

    public PokemonAbilities() {
    }

    public PokemonAbility getName() {
        return name;
    }

    public void setName(PokemonAbility name) {
        this.name = name;
    }

}
