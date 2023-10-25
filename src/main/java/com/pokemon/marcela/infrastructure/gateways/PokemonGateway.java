package com.pokemon.marcela.infrastructure.gateways;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;


@Path("/api/v2/pokemon")
@RegisterRestClient(baseUri = "https://pokeapi.co")
public interface PokemonGateway {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    PokemonListResponse getAllPokemons(@QueryParam("limit=10") @DefaultValue("10") String limit, @QueryParam("offset") @DefaultValue("0") String offset);

    @GET
    @Path("/{pokemonName}")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonDetail getDetailPokemon(@PathParam("pokemonName") String pokemonName);
}
