package com.pokemon.marcela.infrastructure.gateways;

// import javax.enterprise.inject.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
// import javax.ws.rs.QueryParam;
// import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonResponse;



@Path("/")
@RegisterRestClient(baseUri = "https://pokeapi.co")
public interface PokemonGateway {

    @GET
    @Path("/api/v2/pokemon?limit=10&offset=0")
    // @Produces(MediaType.APPLICATION_JSON)
    PokemonResponse getAllPokemons();

    @GET
    @Path("/api/v2/pokemon/{pokemonName}")
    // @Produces(MediaType.APPLICATION_JSON)
    PokemonDetail getDetailPokemon(@PathParam("pokemonName") String pokemonName);
}
