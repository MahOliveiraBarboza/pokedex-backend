package com.pokemon.marcela.aplication.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.application.mapper.ListPokemonMapper;
import com.pokemon.marcela.infrastructure.domain.PokemonListResponse;
import com.pokemon.marcela.infrastructure.domain.PokemonResponse;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonListEntity;

@DisplayName("Given ListPokemonMapper")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListPokemonMapperTest {

    ListPokemonMapper listPokemonMapper;

    @BeforeEach
    public void setup() {
        listPokemonMapper = new ListPokemonMapper();
    }

    @Nested
    @DisplayName("When mapPokemonListResponseToPokemonListEntity is called")
    class mapPokemonListResponseToPokemonListEntityTest {
        List<PokemonListEntity> expectedPokemonList;
        PokemonListResponse pokemonListResponse = spy(PokemonListResponse.class);
        List<PokemonListEntity> result;

        @BeforeEach
        public void mockAndAct() {
            expectedPokemonList = new ArrayList<>(Arrays.asList(
                new PokemonListEntity(
                    "1", 
                    "bulbasaur",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                )
            ));

            List<PokemonResponse> responses = Arrays.asList(
                new PokemonResponse(
                    "bulbasaur",
                    "https://pokeapi.co/api/v2/pokemon/1/"
                )
            );

            when(pokemonListResponse.getResults()).thenReturn(responses);

            result = listPokemonMapper.mapPokemonListResponseToPokemonListEntity(pokemonListResponse);
        }

        @Test
        @DisplayName("Then pokemonListResponse.getResults is called")
        void getResultsTest() {
            verify(pokemonListResponse, times(1)).getResults();

            assertEquals("1", result.get(0).getId());
        }

        @Test
        @DisplayName("Then return id")
        void idTest() {
            assertEquals("1", result.get(0).getId());
        }

        @Test
        @DisplayName("Then return name")
        void nameTest() {
            assertEquals("bulbasaur", result.get(0).getName());        
        }

        @Test
        @DisplayName("Then return image")
        void imageTest() {
            assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", result.get(0).getImage());      
        }

        @Test
        @DisplayName("Then return mapPokemonListResponseToPokemonListEntity")
        void returnMethodMapTest() {
            assertTrue(EqualsBuilder.reflectionEquals(expectedPokemonList, result));
        }
    }

    @Nested
    @DisplayName("When extractIdFromUrl is called")
    class extractIdFromUrlTest {

        @Test
        @DisplayName("Then extractIdFromUrl returns id correctly")
        void extractIdFromUrl() {
            String url = "https://pokeapi.co/api/v2/pokemon/1/";
            String id = listPokemonMapper.extractIdFromUrl(url);

            assertEquals("1", id);
        }
    }
}
