package com.pokemon.marcela.infrastructure.adapters.exception;

public class GetPokemonException extends RuntimeException {

    public GetPokemonException() {

    }

    public GetPokemonException(String message) {
        super(message);
    }
}
