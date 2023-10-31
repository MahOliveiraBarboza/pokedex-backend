package com.pokemon.marcela.infrastructure.gateways;

import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;

public interface InterfaceListPokemonGateway {
      PokemonListResponse getAllPokemons(String limit, String offset);
}
