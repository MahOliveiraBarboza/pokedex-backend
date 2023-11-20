package com.pokemon.marcela.application.commands;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.pokemon.marcela.application.mapper.DetailPokemonMapper;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonDetailEntity;
import com.pokemon.marcela.infrastructure.gateways.InterfaceDetailPokemonGateway;

@ApplicationScoped
public class GetDetailPokemonCommand {
    private final InterfaceDetailPokemonGateway pokemonGateway;
    private final DetailPokemonMapper pokemonMapper;

    @Inject
    public GetDetailPokemonCommand(InterfaceDetailPokemonGateway pokemonGateway, DetailPokemonMapper pokemonMapper) {
        this.pokemonGateway = pokemonGateway;
        this.pokemonMapper = pokemonMapper;
    }

    public PokemonDetailEntity execute(String pokemonName) {
        try {
            PokemonDetail pokemonDetail = pokemonGateway.getDetailPokemon(pokemonName);
            return pokemonMapper.mapPokemonDetailToPokemonEntity(pokemonDetail);
        } catch (Exception e) {
            throw new GetPokemonException("Não foi possível obter o card de detalhes do pokémon: " + e.getMessage());
        }
    }
}
