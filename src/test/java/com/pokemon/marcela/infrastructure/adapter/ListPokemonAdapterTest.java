package com.pokemon.marcela.infrastructure.adapter;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.infrastructure.adapters.ListPokemonAdapter;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;


@DisplayName("Given ListPokemonAdapter")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListPokemonAdapterTest {
    
    @InjectMocks
    ListPokemonAdapter listPokemonAdapter;

    @Mock
    Logger mockLogger;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @BeforeEach
    public void resetAndMockLogger() throws Exception {
        reset(pokemonGatewayMock);
        reset(mockLogger);
        Logger mockLogger = Mockito.mock(Logger.class);
    }

    @Nested
    @DisplayName("When getAllPokemons is called")
    class getAllPokemonsTest {

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyGetAllTest {
            PokemonListResponse pokemonListResponse;
            PokemonListResponse result;
            String limitMok = "limit";
            String offsetMok = "offset";

            @BeforeEach
            void mockAndAct() {
                pokemonListResponse = new PokemonListResponse();
                when(pokemonGatewayMock.getAllPokemons(limitMok, offsetMok)).thenReturn(pokemonListResponse);

                result = listPokemonAdapter.getAllPokemons(limitMok, offsetMok);
            }




            @Test
            @DisplayName("Then success log should be called correctly")
            void getAllSuccessLog() {
                verify(mockLogger, times(1)).info(
                        "[ListPokemonAdapter:getAllPokemons] Começando a pegar os dados dos pokemons");
            }
        }
    }
}
