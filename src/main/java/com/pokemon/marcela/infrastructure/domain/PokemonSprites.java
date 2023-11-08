package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonSprites {
    @JsonbProperty("front_default")
    private String imagePokemon;

    public PokemonSprites(String imagePokemon) {
        this.imagePokemon = imagePokemon;
    }

    public PokemonSprites() {
        
    }

    public String getImagePokemon() {
        return imagePokemon;
    }

    public void setImagePokemon(String imagePokemon) {
        this.imagePokemon = imagePokemon;
    }
}
