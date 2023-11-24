package com.pokemon.marcela.application.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.PokemonResponse;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;

@ApplicationScoped
public class ListPokemonMapper {
    
    public List<PokemonListEntity> mapPokemonListResponseToPokemonListEntity(PokemonListResponse pokemonListResponse) {
        List<PokemonListEntity> newPokemonListEntity = new ArrayList<>();
        for (PokemonResponse pokemonResponse : pokemonListResponse.getResults()) {
            String id = extractIdFromUrl(pokemonResponse.getUrl());
            String name = pokemonResponse.getName();
    
            // Adiciona a URL da imagem default do Pokemon a propriedade "imagePokemon"
            String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
            
            newPokemonListEntity.add(new PokemonListEntity(id, name, imageUrl));
        }
        return newPokemonListEntity;
    }
   
    public String extractIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        return urlParts[urlParts.length - 1];
    }
}
