package com.pokemon.marcela.infrastructure.adapters;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.gateways.InterfacePokemonGateway;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;

@Singleton
public class PokemonAdapter implements InterfacePokemonGateway {
    private static final Logger LOGGER = Logger.getLogger(PokemonAdapter.class.getName());
    private final PokemonGateway pokemonGateway;

    @Inject
    public PokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    @Override
    public PokemonListResponse getAllPokemons(String limit, String offset) {
        try {
            LOGGER.info("[PokemonAdapter:getAllPokemons] Começando a pegar os dados dos pokkemons");
            return pokemonGateway.getAllPokemons(limit, offset);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[PokemonAdapter:getAllPokemons] Erro aos pegar os dados", e);
            throw new GetPokemonException(e.getMessage());
        }
        
    }

    @Override
    public PokemonDetail getDetailPokemon(String pokemonName) {
        try {
            LOGGER.info("[PokemonAdapter:getDetailPokemon] Começando a pegar os dadosde detalhes dos pokkemons");
            return pokemonGateway.getDetailPokemon(pokemonName);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[PokemonAdapter:getDetailPokemon] Erro aos pegar os dados de detalhes", e);
            throw new GetPokemonException(e.getMessage());
        }
    }

}
