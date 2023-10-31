package com.pokemon.marcela.application.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.pokemon.marcela.infrastructure.domain.PokemonAbilities;
import com.pokemon.marcela.infrastructure.domain.PokemonAbility;
import com.pokemon.marcela.infrastructure.domain.PokemonDetail;
import com.pokemon.marcela.infrastructure.domain.PokemonSprites;
import com.pokemon.marcela.infrastructure.domain.PokemonType;
import com.pokemon.marcela.infrastructure.domain.PokemonTypes;
import com.pokemon.marcela.infrastructure.domain.entities.PokemonDetailEntity;

@ApplicationScoped
public class DetailPokemonMapper {

    public PokemonDetailEntity mapPokemonDetailToPokemonEntity(PokemonDetail pokemonDetail) {
        PokemonSprites sprites = pokemonDetail.getSprites();
        String imageUrl = null;
        if (sprites != null) {
            imageUrl = sprites.getImagePokemon();
        }

        String baseExperience = null;
        Number baseExperienceNumber = pokemonDetail.getBaseExperience();
        if (baseExperienceNumber != null) {
            baseExperience = String.valueOf(baseExperienceNumber.intValue());
        }

        String height = null;
        Float heightNumber = pokemonDetail.getHeight();
        if (heightNumber != null) {
            height = String.valueOf(heightNumber);
        }

        String weight = null;
        Float weightNumber = pokemonDetail.getWeight();
        if (weightNumber != null) {
            weight = String.valueOf(weightNumber);
        }

        List<PokemonAbilities> abilitiesList = pokemonDetail.getAbilities();
        List<PokemonAbilities> entityAbilities = new ArrayList<>();
        if (abilitiesList != null) {
            for (PokemonAbilities abilities : abilitiesList) {
                PokemonAbility ability = abilities.getName();
                if (ability != null) {
                    entityAbilities.add(new PokemonAbilities(new PokemonAbility(ability.getName())));
                }
            }
        }

        List<PokemonTypes> typesList = pokemonDetail.getTypes();
        List<PokemonTypes> entityTypes = new ArrayList<>();
        if (typesList != null) {
            for (PokemonTypes types : typesList) {
                PokemonType type = types.getName();
                if (type != null) {
                    entityTypes.add(new PokemonTypes(new PokemonType(type.getName())));
                }
            }
        }

        return new PokemonDetailEntity(pokemonDetail.getName(), imageUrl, baseExperience, height, weight, entityTypes, entityAbilities);
    }
}