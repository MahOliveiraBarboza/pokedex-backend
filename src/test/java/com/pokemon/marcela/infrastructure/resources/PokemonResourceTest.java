package com.pokemon.marcela.infrastructure.resources;

import com.pokemon.marcela.application.commands.GetAllPokemonsCommand;
import com.pokemon.marcela.application.commands.GetDetailPokemonCommand;
import com.pokemon.marcela.infrastructure.adapters.exception.GetPokemonException;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonDetailEntity;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@DisplayName("Given PokemonResource")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonResourceTest {

    @Mock
    GetDetailPokemonCommand getDetailPokemonCommand;

    @Mock
    GetAllPokemonsCommand getAllPokemonsCommand;

    @InjectMocks
    PokemonResource pokemonResource;

    @BeforeEach
    void setUp() {
        reset(getDetailPokemonCommand);
        reset(getAllPokemonsCommand);
    }
    
    @Nested
    @DisplayName("When allPokemons is called")
    class AllPokemonsTest {

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyTest {

            Response result;
            List<PokemonListEntity> pokemonListEntity;

            @BeforeEach
            void mockAndAct() {
                when(getAllPokemonsCommand.execute()).thenReturn(pokemonListEntity);

                result = pokemonResource.allPokemons();
            }

            @Test
            @DisplayName("Then result status is 200")
            void allPokemonsSuccessCodeOKTest() {
                assertEquals(200, result.getStatus());
            }

            @Test
            @DisplayName("Then returns pokemonList")
            void allPokemonsallPokemonsReturnPokemonListTest() {
                assertEquals(pokemonListEntity, result.getEntity());
            }
        }

        @Nested
        @DisplayName("And throw GetPokemonException")
        class ErrorGetPokemonExceptionTest {

            Response result;
            GetPokemonException getPokemonException;

            @BeforeEach
            void mockAndAct() {
                doThrow(GetPokemonException.class).when(getAllPokemonsCommand).execute();

                try {
                    result = pokemonResource.allPokemons();
                } catch (GetPokemonException e) {
                    getPokemonException = e;
                }
            }

            @Test
            @DisplayName("Then result status is 400")
            void allPokemonsBadRequestTest() {
                assertEquals(400, result.getStatus());
            }
        }

        @Nested
        @DisplayName("And throw Exception")
        class ErrorExceptionTest {

            Response result;
            Exception exception;

            @BeforeEach
                void mockAndAct() {
                    doThrow(new RuntimeException()).when(getAllPokemonsCommand).execute();

                    try {
                        result = pokemonResource.allPokemons();
                    } catch (Exception e) {
                        exception = e;
                    }
                }

            @Test
            @DisplayName("Then result status is 500")
            void allPokemonsInternalServerErrorTest() {
                assertEquals(500, result.getStatus());
            }
        }
    }

    @Nested
    @DisplayName("When onePokemon is called")
    class OnePokemonTest {
        String pokemonNameMock = "pokemonName";

        @Nested
        @DisplayName("And returns successfully")
        class SuccessFullyTest {

            Response result;
            PokemonDetailEntity pokemonDetailEntity;

            @BeforeEach
            void mockAndAct() {
                when(getDetailPokemonCommand.execute(pokemonNameMock)).thenReturn(pokemonDetailEntity);

                result = pokemonResource.onePokemon(pokemonNameMock);
            }

            @Test
            @DisplayName("Then result status is 200")
            void onePokemonSuccessCodeOKTest() {
                assertEquals(200, result.getStatus());
            }

            @Test
            @DisplayName("Then Then returns pokemonDetail")
            void onePokemonReturnPokemonDetailTes() {
                assertEquals(pokemonDetailEntity, result.getEntity());
            }
        }

        @Nested
        @DisplayName("And throw Exception")
        class ErrorExceptionTest {

            Response result;
            Exception exception;

            @BeforeEach
                void mockAndAct() {
                    doThrow(new RuntimeException()).when(getDetailPokemonCommand).execute(pokemonNameMock);

                    try {
                        result = pokemonResource.onePokemon(pokemonNameMock);
                    } catch (Exception e) {
                        exception = e;
                    }
                }

            @Test
            @DisplayName("Then result status is 500")
            void onePokemonInternalServerErrorTest() {
                assertEquals(500, result.getStatus());
            }
        }
    }

}
