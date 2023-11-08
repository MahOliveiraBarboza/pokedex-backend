package com.pokemon.marcela.infrastructure.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pokemon.marcela.application.commands.GetAllPokemonsCommand;
import com.pokemon.marcela.application.commands.GetDetailPokemonCommand;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;

@Path("/pokemon")
public class PokemonResource {
    private final GetAllPokemonsCommand getAllPokemonsCommand;
    private final GetDetailPokemonCommand getDetailPokemonCommand;

    @Inject
    public PokemonResource(GetAllPokemonsCommand getAllPokemonsCommand, GetDetailPokemonCommand getDetailPokemonCommand) {
        this.getAllPokemonsCommand = getAllPokemonsCommand;
        this.getDetailPokemonCommand = getDetailPokemonCommand;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPokemons() {

        try{
            List<PokemonListEntity> entityResponse = getAllPokemonsCommand.execute();
            return Response.status(Response.Status.OK).entity(entityResponse).build();
        }catch (GetPokemonException error) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{pokemonName}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response onePokemon(@PathParam("pokemonName") String pokemonName) {
        try{
            return Response.status(Response.Status.OK).entity(getDetailPokemonCommand.execute(pokemonName)).build();
        }catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
