package com.pokemon.marcela.infrastructure.domain;

import java.util.List;

public class PokemonListResponse {
     private List<PokemonResponse> results;

     public PokemonListResponse(List<PokemonResponse> results) {
        this.results = results;
    }

    public List<PokemonResponse> getResult() {
        return results;
    }
}
