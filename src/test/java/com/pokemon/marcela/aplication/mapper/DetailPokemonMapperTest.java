package com.pokemon.marcela.aplication.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemon.marcela.application.mapper.DetailPokemonMapper;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonSprites;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonDetailEntity;

@DisplayName("Given DetailPokemonMapper")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DetailPokemonMapperTest {

    DetailPokemonMapper detailPokemonMapper;

    @BeforeEach
    public void setup() {
        detailPokemonMapper = new DetailPokemonMapper();
    }

    @Nested
    @DisplayName("When mapPokemonDetailToPokemonEntity is called")
    class mapPokemonDetailToPokemonEntityTest {
        PokemonDetailEntity expectedPokemon;
        PokemonDetail pokemonDetail;
        PokemonDetailEntity result;

        @BeforeEach
            public void mockAndAct() {
                expectedPokemon = new PokemonDetailEntity(
                        "bulbasaur",
                        null,
                        "64",
                        "7.0",
                        "69.0",
                        new ArrayList<>(),
                        new ArrayList<>()
                );

                pokemonDetail = new PokemonDetail(
                        new PokemonSprites(),
                        "bulbasaur",
                        64,
                        7F,
                        69F,
                        new ArrayList<>(),
                        new ArrayList<>()
                );

                result = detailPokemonMapper.mapPokemonDetailToPokemonEntity(pokemonDetail);
            }

            @Test
            @DisplayName("Then return mapPokemonDetailToPokemonEntity")
            void returnMethodMapTest() {
                Assertions.assertEquals(expectedPokemon.getName(), result.getName());
                Assertions.assertEquals(expectedPokemon.getImage(), result.getImage());
                Assertions.assertEquals(expectedPokemon.getBaseExperience(), result.getBaseExperience());
                Assertions.assertEquals(expectedPokemon.getHeight(), result.getHeight());
                Assertions.assertEquals(expectedPokemon.getWeight(), result.getWeight());
                Assertions.assertEquals(expectedPokemon.getTypes(), result.getTypes());
                Assertions.assertEquals(expectedPokemon.getAbilities(), result.getAbilities());

                assertTrue(EqualsBuilder.reflectionEquals(expectedPokemon, result));
            }
    }
}