package com.pokemon.marcela.infrastructure.adapters;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.PokemonResponse;
import com.pokemon.marcela.infrastructure.gateways.InterfaceListPokemonGateway;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;

@Singleton
public class ListPokemonAdapter implements InterfaceListPokemonGateway {
    private static final Logger LOGGER = Logger.getLogger(ListPokemonAdapter.class.getName());
    private final PokemonGateway pokemonGateway;

    @Inject
    public ListPokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    @Override
    public PokemonListResponse getAllPokemons(String limit, String offset) {
        try {
            LOGGER.info("[ListPokemonAdapter:getAllPokemons] Começando a pegar os dados dos pokkemons");
             PokemonListResponse pokemonListResponse = pokemonGateway.getAllPokemons(limit, offset);

            for (PokemonResponse pokemonResponse : pokemonListResponse.getResults()) {
                PokemonDetail pokemonDetail = pokemonGateway.getDetailPokemon(pokemonResponse.getName());
                pokemonDetail.getSprites().setImagePokemon(pokemonDetail.getSprites().getImagePokemon());; // adiciona a URL da imagem
            }
            return pokemonListResponse;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[ListPokemonAdapter:getAllPokemons] Erro aos pegar os dados", e);
            throw new GetPokemonException(e.getMessage());
        }
        
    }
}
