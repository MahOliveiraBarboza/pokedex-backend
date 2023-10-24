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



@Path("/") // endpoint que refenrencia minha rota 
@RegisterRestClient(baseUri = "https://pokeapi.co") // conexão com a api
public interface PokemonGateway {

    @GET
    @Path("/api/v2/pokemon?limit=10&offset=0") //endpoint q traz os 10 pokemons
    // @Produces(MediaType.APPLICATION_JSON)
    PokemonResponse getAllPokemons(); //para usar a domain para ter na tela nome e url

    @GET
    @Path("/api/v2/pokemon/{pokemonName}") //endpoint q traz somente o pokemon passado em nome
    // @Produces(MediaType.APPLICATION_JSON)
    PokemonDetail getDetailPokemon(@PathParam("pokemonName") String pokemonName);  //para usar a domain para ter na tela o card de detaljes   
}
