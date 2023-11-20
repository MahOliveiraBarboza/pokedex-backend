package com.pokemon.marcela.aplication.command;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.application.commands.GetDetailPokemonCommand;
import com.pokemon.marcela.application.mapper.DetailPokemonMapper;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonDetailEntity;
import com.pokemon.marcela.infrastructure.gateways.InterfaceDetailPokemonGateway;

@DisplayName("Given GetDetailPokemonCommand")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetDetailPokemonCommandTest {
    @InjectMocks
    GetDetailPokemonCommand getDetailPokemonCommand;

    @Mock
    InterfaceDetailPokemonGateway interfaceDetailPokemonGatewayMock;

    @Mock
    DetailPokemonMapper detailPokemonMapperMock;

    @BeforeEach
    void setUp() {
        reset(interfaceDetailPokemonGatewayMock);
        reset(detailPokemonMapperMock);
    }

    @Nested
    @DisplayName("When execute is called")
    class ExecuteCallDetailTest {

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyDetailTest {

            PokemonDetail pokemonDetail;
            PokemonDetailEntity pokemonDetailEntity;
            PokemonDetailEntity result;
            String pokemonNameMock = "pokemonName";

            @BeforeEach
            void mockAndAct() {
                when(interfaceDetailPokemonGatewayMock.getDetailPokemon(pokemonNameMock)).thenReturn(pokemonDetail);
                when(detailPokemonMapperMock.mapPokemonDetailToPokemonEntity(pokemonDetail)).thenReturn(pokemonDetailEntity);

                result = getDetailPokemonCommand.execute(pokemonNameMock);
            }

            @Test
            @DisplayName("Then getDetailPokemon is called")
            void getDetailPokemonCalledTest() {
                verify(interfaceDetailPokemonGatewayMock, times(1)).getDetailPokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then mapPokemonDetailToPokemonEntity is called")
            void mapPokemonDetailToPokemonEntityCalledTest() {
                verify(detailPokemonMapperMock, times(1)).mapPokemonDetailToPokemonEntity(pokemonDetail);
            }

            @Test
            @DisplayName("Then return pokemonDetail")
            void executeReturnPokemonDetailTest() {
                assertEquals(pokemonDetailEntity, result);
            }

        }
    }

    @Nested
    @DisplayName("And thrown exception")
    class ExceptionThrownTest {
        String pokemonNameMock = "pokemonName";
        GetPokemonException thrownException;

        @BeforeEach
        void mockAndAct() throws Exception  {
             when(interfaceDetailPokemonGatewayMock.getDetailPokemon(pokemonNameMock)).thenThrow(new GetPokemonException("Ocorreu um erro ao obter a lista de pokémons"));

             try {
                getDetailPokemonCommand.execute(pokemonNameMock);
            } catch (GetPokemonException e) {
                thrownException = e;
            }
        }

        @Test
        @DisplayName("Then throw GetPokemonException")
        void executeThrowsGetPokemonExceptionTest() {
            assertEquals("Não foi possível obter o card de detalhes do pokémon: Ocorreu um erro ao obter a lista de pokémons", thrownException.getMessage());
        }
    }
}
