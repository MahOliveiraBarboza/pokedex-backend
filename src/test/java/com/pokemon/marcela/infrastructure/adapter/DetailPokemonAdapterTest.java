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
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.gateways.PokemonGateway;

import org.mockito.Mock;
import org.mockito.Mockito;

@DisplayName("Given DetailPokemonAdapter")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DetailPokemonAdapterTest {

    Logger mockLogger;

    @InjectMocks
    DetailPokemonAdapter detailPokemonAdapter;

    @Mock
    PokemonGateway pokemonGatewayMock;

    @BeforeEach
    public void resetAndMockLogger() throws Exception {
    reset(pokemonGatewayMock);
    Logger mockLogger = Mockito.mock(Logger.class);
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
                pokemonDetail.setName("name");
                when(pokemonGatewayMock.getDetailPokemon(pokemonNameMock)).thenReturn(pokemonDetail);

                result = detailPokemonAdapter.getDetailPokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then getDetailPokemon is called from from the gateway")
            void gatewayGetDetailPokemonTest() {
                verify(pokemonGatewayMock,times(1)).getDetailPokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then return pokemonDetail")
            void getDetailReturnPokemonDetailTest() {
                assertEquals(pokemonDetail, result);
            }

            @Test
            @DisplayName("Then success log should be called correctly")
            void getDetailSuccessLog() {
                verify(mockLogger, times(1)).info("[DetailPokemonAdapter:getDetailPokemon] Começando a pegar os dados de detalhes dos pokemons");
            }

        }
    }


}
