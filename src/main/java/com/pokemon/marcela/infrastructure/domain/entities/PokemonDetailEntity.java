package com.pokemon.marcela.infrastructure.domain.entities;

import java.util.List;

import com.pokemon.marcela.infrastructure.domain.PokemonAbilities;
import com.pokemon.marcela.infrastructure.domain.PokemonTypes;

public class PokemonDetailEntity {
    private String name;
    private String imagePokemon;
    private String baseExperience;
    private String height;
    private String weight;
    private List<PokemonTypes> types;
    private List<PokemonAbilities> abilities;

    public PokemonDetailEntity() {
    }

    public PokemonDetailEntity(String name, String imagePokemon, String baseExperience, String height, String weight, List<PokemonTypes> types, List<PokemonAbilities> abilities) {
        this.name = name;
        this.imagePokemon = imagePokemon;
        this.baseExperience = baseExperience;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imagePokemon;
    }

    public void setImage(String imagePokemon) {
        this.imagePokemon = imagePokemon;
    }

    public String getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(String baseExperience) {
        this.baseExperience = baseExperience;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

