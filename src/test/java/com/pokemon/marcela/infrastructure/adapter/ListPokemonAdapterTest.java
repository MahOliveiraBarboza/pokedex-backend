package com.pokemon.marcela.infrastructure.adapter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pokemon.marcela.TestUtil.getLoggerMock;
import com.pokemon.marcela.infrastructure.adapters.ListPokemonAdapter;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.PokemonResponse;
import com.pokemon.marcela.infrastructure.domain.PokemonSprites;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;


@DisplayName("Given ListPokemonAdapter")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListPokemonAdapterTest {
    
    @InjectMocks
    ListPokemonAdapter listPokemonAdapter;

    Logger mockLogger;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @BeforeEach
    public void resetAndMockLogger() throws Exception {
        mockLogger = getLoggerMock(ListPokemonAdapter.class);
        reset(pokemonGatewayMock);
        reset(mockLogger);
    }

    @Nested
    @DisplayName("When getAllPokemons is called")
    class getAllPokemonsTest {

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyGetAllTest {
            PokemonListResponse pokemonListResponse;
            PokemonListResponse result;
            String limitMok = "20";
            String offsetMok = "0";

            @BeforeEach
            void mockAndAct() {
                PokemonResponse pokemonResponse = new PokemonResponse();
                pokemonResponse.setName("Bulbasaur");
                pokemonResponse.setUrl("https://pokeapi.co/api/v2/pokemon/1/");

                PokemonListResponse pokemonListResponse = new PokemonListResponse();
                List<PokemonResponse> pokemonResponses = new ArrayList<>();
                pokemonResponses.add(pokemonResponse);
                pokemonListResponse.setResults(pokemonResponses);

                PokemonDetail pokemonDetail = new PokemonDetail();
                PokemonSprites sprites = new PokemonSprites();
                sprites.setImagePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
                pokemonDetail.setSprites(sprites);


                when(pokemonGatewayMock.getAllPokemons(limitMok, offsetMok)).thenReturn(pokemonListResponse);

                when(pokemonGatewayMock.getDetailPokemon("Bulbasaur")).thenReturn(pokemonDetail);

                PokemonListResponse result = listPokemonAdapter.getAllPokemons(limitMok, offsetMok);
            }

            @Test
            @DisplayName("Then getAllPokemons is called from from the gateway")
            void gatewayGetAllPokemonsTest() {
                verify(pokemonGatewayMock, times(1)).getAllPokemons(limitMok, offsetMok);
            }

            @Test
            @DisplayName("Then return pokemonListResponse")
            void getAllReturnpokemonListResponseTest() {
                assertEquals(pokemonListResponse, result);
            }

            @Test
            @DisplayName("Then success log should be called correctly")
            void getAllSuccessLog() {
                verify(mockLogger, times(1)).info(
                        "[ListPokemonAdapter:getAllPokemons] Começando a pegar os dados dos pokemons");
            }
        }

        @Nested
        @DisplayName("And thows exception")
        class ExceptionThrowTest {
            String limitMok = "limit";
            String offsetMok = "offset";
            String errorMessage = "[ListPokemonAdapter:getAllPokemons] Erro aos pegar a lista de pokemons";
            GetPokemonException thrownException;

            @BeforeEach
            void mockAndAct() throws Exception {
                when(pokemonGatewayMock.getAllPokemons(limitMok, offsetMok)).thenThrow(new RuntimeException(errorMessage));

                try {
                    listPokemonAdapter.getAllPokemons(limitMok, offsetMok);
                } catch (GetPokemonException e) {
                    thrownException = e;
                }
            }

            @Test
            @DisplayName("Then throw GetPokemonException")
            void shouldThrowGetPokemonException() {
                assertEquals(errorMessage, thrownException.getMessage());
            }

            @Test
            @DisplayName("Then fail log should be called correctly")
            void geAllFailLog() {
                verify(mockLogger, times(1)).severe(
                    "[ListPokemonAdapter:getAllPokemons] Erro aos pegar a lista de pokemons");
            }
        }
    }
} 