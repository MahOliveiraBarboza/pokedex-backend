package com.pokemon.marcela.application.commands;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.pokemon.marcela.application.mapper.ListPokemonMapper;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;
import com.pokemon.marcela.infrastructure.gateways.InterfaceListPokemonGateway;

@ApplicationScoped
public class GetAllPokemonsCommand {
    private final InterfaceListPokemonGateway listPokemonGateway;
    private final ListPokemonMapper listPokemonMapper;
    private static final String LIMIT = "10";
    private static final String OFFSET = "0";

    @Inject
    public GetAllPokemonsCommand(InterfaceListPokemonGateway listPokemonGateway, ListPokemonMapper listPokemonMapper) {
        this.listPokemonGateway = listPokemonGateway;
        this.listPokemonMapper = listPokemonMapper;
    }

    public List<PokemonListEntity> execute() {
        try {
            PokemonListResponse pokemonListResponse = listPokemonGateway.getAllPokemons(LIMIT, OFFSET);
            return listPokemonMapper.mapPokemonListResponseToPokemonListEntity(pokemonListResponse);
         } catch (Exception e) {
            throw new GetPokemonException("Não foi possível obter a lista de pokémons: " + e.getMessage());
        }
    }
}
