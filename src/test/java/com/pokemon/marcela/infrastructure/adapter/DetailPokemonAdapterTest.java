package com.pokemon.marcela.infrastructure.adapter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.infrastructure.adapters.DetailPokemonAdapter;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;

import static com.pokemon.marcela.TestUtil.getLoggerMock;
import org.mockito.Mock;

@DisplayName("Given DetailPokemonAdapter")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DetailPokemonAdapterTest {

    @InjectMocks
    DetailPokemonAdapter detailPokemonAdapter;

    Logger mockLogger;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @BeforeEach
    public void resetAndMockLogger() throws Exception {
        mockLogger = getLoggerMock(DetailPokemonAdapter.class);
        reset(pokemonGatewayMock);
        reset(mockLogger);
    }

    @Nested
    @DisplayName("When getDetailPokemon is called")
    class getDetailPokemonTest {

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyGetDetailTest {
            PokemonDetail pokemonDetail;
            PokemonDetail result;
            String pokemonNameMock = "PokemonName";

            @BeforeEach
            void mockAndAct() {
                pokemonDetail = new PokemonDetail();
                when(pokemonGatewayMock.getDetailPokemon(pokemonNameMock)).thenReturn(pokemonDetail);

                result = detailPokemonAdapter.getDetailPokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then getDetailPokemon is called from from the gateway")
            void gatewayGetDetailPokemonTest() {
                verify(pokemonGatewayMock, times(1)).getDetailPokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then return pokemonDetail")
            void getDetailReturnPokemonDetailTest() {
                assertEquals(pokemonDetail, result);
            }

            @Test
            @DisplayName("Then success log is called correctly")
            void getDetailSuccessLog() {
                verify(mockLogger, times(1)).info(
                        "[DetailPokemonAdapter:getDetailPokemon] Começando a pegar os dados de detalhes dos pokemons");
            }

        }

        @Nested
        @DisplayName("And throw exception")
        class ExceptionThrowTest {
            String pokemonNameMock = "PokemonName";
            String errorMessage = "[DetailPokemonAdapter:getDetailPokemon] Erro aos pegar os dados de detalhes";
            GetPokemonException thrownException;

            @BeforeEach
            void mockAndAct() throws Exception {
                when(pokemonGatewayMock.getDetailPokemon(pokemonNameMock)).thenThrow(new GetPokemonException(errorMessage));

                try {
                    detailPokemonAdapter.getDetailPokemon(pokemonNameMock);
                } catch (GetPokemonException e) {
                    thrownException = e;
                }
            }

            @Test
            @DisplayName("Then throw GetPokemonException")
            void throwGetPokemonException() {
                assertEquals(errorMessage, thrownException.getMessage());
            }

            @Test
            @DisplayName("Then fail log is called correctly")
            void getDetailFailLog() {
                verify(mockLogger, times(1)).severe(
                        "[DetailPokemonAdapter:getDetailPokemon] Erro ao pegar os dados de detalhes");
            }
        }
    }

}
