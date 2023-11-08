package com.pokemon.marcela.infrastructure.domain;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonListResponse {
    @JsonbProperty("results")
    private List<PokemonResponse> results;

    public PokemonListResponse(List<PokemonResponse> results) {
        this.results = results;
    }

    public PokemonListResponse() {
        
    }

    public List<PokemonResponse> getResults() {
        return results;
    }

    public void setResults(List<PokemonResponse> results) {
        this.results = results;
    }
}
