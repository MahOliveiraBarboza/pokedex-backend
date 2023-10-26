package com.pokemon.marcela.infrastructure.gateways;

import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;

public interface InterfacePokemonGateway {
      PokemonListResponse getAllPokemons(String limit, String offset);

       PokemonDetail getDetailPokemon(String pokemonName);
}
