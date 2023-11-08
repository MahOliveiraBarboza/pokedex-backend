package com.pokemon.marcela.infrastructure.adapters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.gateways.InterfaceDetailPokemonGateway;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;

@Singleton
public class DetailPokemonAdapter implements InterfaceDetailPokemonGateway{
    private static final Logger LOGGER = Logger.getLogger(DetailPokemonAdapter.class.getName());
    private final PokemonGateway pokemonGateway;

    @Inject
    public DetailPokemonAdapter(@RestClient PokemonGateway pokemonGateway) {
        this.pokemonGateway = pokemonGateway;
    }

    @Override
    public PokemonDetail getDetailPokemon(String pokemonName) {
        try {
            LOGGER.info("[DetailPokemonAdapter:getDetailPokemon] Começando a pegar os dados de detalhes dos pokkemons");
            return pokemonGateway.getDetailPokemon(pokemonName);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[DetailPokemonAdapter:getDetailPokemon] Erro aos pegar os dados de detalhes", e);
            throw new GetPokemonException(e.getMessage());
        }
    }
}
