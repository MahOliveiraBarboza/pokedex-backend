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
        List<PokemonListEntity> pokemonListEntityList = new ArrayList<>();
        for (PokemonResponse pokemonResponse : pokemonListResponse.getResults()) {
            String id = extractIdFromUrl(pokemonResponse.getUrl());
    
            // Adiciona a URL da imagem default do Pokemon a propriedade "imagePokemon"
            String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
            
            pokemonListEntityList.add(new PokemonListEntity(id, pokemonResponse.getName(), imageUrl));
        }
        return pokemonListEntityList;
    }
   
    private String extractIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        return urlParts[urlParts.length - 1];
    }
}
