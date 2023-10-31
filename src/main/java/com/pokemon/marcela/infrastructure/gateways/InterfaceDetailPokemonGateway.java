package com.pokemon.marcela.infrastructure.gateways;

import com.pokemon.marcela.infrastructure.domain.PokemonDetail;

public interface InterfaceDetailPokemonGateway {
    PokemonDetail getDetailPokemon(String pokemonName);
}
