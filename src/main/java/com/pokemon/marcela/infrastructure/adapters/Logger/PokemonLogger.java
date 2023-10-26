package com.pokemon.marcela.infrastructure.adapters.Logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PokemonLogger {
    private static final Logger LOGGER = Logger.getLogger(PokemonLogger.class.getName());

    public void myMethod(String message) {
        LOGGER.info(message);
    }   

    public void error(String message, Throwable exception) {
        LOGGER.log(Level.SEVERE, message, exception);
     }
}
