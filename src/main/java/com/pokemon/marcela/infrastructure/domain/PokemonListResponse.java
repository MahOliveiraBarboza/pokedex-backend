package com.pokemon.marcela.infrastructure.domain;

import java.util.List;

public class PokemonListResponse {
     private List<PokemonResponse> results;

     public PokemonListResponse(List<PokemonResponse> results) {
        this.results = results;
    }

    public PokemonListResponse() {}

    public List<PokemonResponse> getResults() {
        return results;
    }

    public void setResults(List<PokemonResponse> results) {
        this.results = results;
    }
}
