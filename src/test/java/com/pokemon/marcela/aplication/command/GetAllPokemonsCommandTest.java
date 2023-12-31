package com.pokemon.marcela.aplication.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.application.commands.GetAllPokemonsCommand;
import com.pokemon.marcela.application.mapper.ListPokemonMapper;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;
import com.pokemon.marcela.infrastructure.gateways.InterfaceListPokemonGateway;

import java.util.List;
import org.mockito.Mock;

@DisplayName("Given GetAllPokemonsCommand")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetAllPokemonsCommandTest {
    @InjectMocks
    GetAllPokemonsCommand getAllPokemonsCommand;

    @Mock
    InterfaceListPokemonGateway interfaceListPokemonGatewayMock;
    @Mock
    ListPokemonMapper pokemonMapperMock;

    @BeforeEach
    void setUp() {
        reset(interfaceListPokemonGatewayMock);
        reset(pokemonMapperMock);
    }

    @Nested
    @DisplayName("When execute is called")
    class ExecuteCallListTest{

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyListTest {

            PokemonListResponse pokemonListResponse;
            List<PokemonListEntity> pokemonListEntity;
            List<PokemonListEntity> result;
            String LIMIT = "10";
            String OFFSET = "0";

            @BeforeEach
            void mockAndAct() {
                when(interfaceListPokemonGatewayMock.getAllPokemons(LIMIT, OFFSET)).thenReturn(pokemonListResponse);
                when(pokemonMapperMock.mapPokemonListResponseToPokemonListEntity(pokemonListResponse)).thenReturn(pokemonListEntity);

                result = getAllPokemonsCommand.execute();
            }

            @Test
            @DisplayName("Then getAllPokemon is called")
            void getAllPokemonCalledTest() {
                verify(interfaceListPokemonGatewayMock, times(1)).getAllPokemons(LIMIT, OFFSET);
            }

            @Test
            @DisplayName("Then mapPokemonListResponseToPokemonListEntity is called")
            void mapPokemonListResponseToPokemonListEntityCalledTest() {
                verify(pokemonMapperMock, times(1)).mapPokemonListResponseToPokemonListEntity(pokemonListResponse);
            }

            @Test
            @DisplayName("Then return pokemonList")
            void executeReturnPokemonListTest() {
                assertEquals(pokemonListEntity, result);
            }
        }
    }     

    @Nested
    @DisplayName("And throw exception")
    class ExceptionThrowTest {
        String LIMIT = "10";
        String OFFSET = "0";
        String errorMessage = "Não foi possível obter a lista de pokémons: ";
        GetPokemonException thrownException;

        @BeforeEach
        void mockAndAct() throws Exception  {
             when(interfaceListPokemonGatewayMock.getAllPokemons(LIMIT, OFFSET)).thenThrow(new GetPokemonException("Ocorreu um erro ao obter a lista de pokémons"));

             try {
                getAllPokemonsCommand.execute();
            } catch (GetPokemonException e) {
                thrownException = e;
            }
        }

        @Test
        @DisplayName("Then throw GetPokemonException")
        void executeThrowsGetPokemonException() {
            assertEquals("Não foi possível obter a lista de pokémons: Ocorreu um erro ao obter a lista de pokémons", thrownException.getMessage());
        }
    }
}
