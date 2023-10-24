package com.pokemon.marcela.infrastructure.domain;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

public class PokemonTypes {
    @JsonbProperty("ability")
    private List<PokemonType> types;

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
