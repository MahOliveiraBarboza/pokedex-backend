package com.pokemon.marcela.infrastructure.domain;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class PokemonDetail {
    @JsonbProperty("sprites")
    private PokemonSprites sprites;
    private String name;
    @JsonbProperty("base_experience")
    private Number baseExperience;
    private Float height;
    private Float weight;
    @JsonbProperty("types")
    private List<PokemonTypes> types;
    @JsonbProperty("abilities")
    private List<PokemonAbilities> abilities;
    
    public PokemonDetail(PokemonSprites sprites, String name, Number baseExperience, Float height, Float weight, List<PokemonTypes> types, List<PokemonAbilities> abilities) {
        this.sprites = sprites;
        this.name = name;
        this.baseExperience = baseExperience;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.abilities = abilities;
    }

    public PokemonDetail() {
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Number baseExperience) {
        this.baseExperience = baseExperience;
    }

     public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public List<PokemonTypes> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypes> types) {
        this.types = types;
    }

    public List<PokemonAbilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbilities> abilities) {
        this.abilities = abilities;
    }
}
