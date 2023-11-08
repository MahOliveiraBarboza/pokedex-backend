package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;

public class PokemonResponse {
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("url")
    private String url;

    public PokemonResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public PokemonResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
