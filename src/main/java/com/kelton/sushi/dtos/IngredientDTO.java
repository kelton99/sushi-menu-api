package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;

import java.util.List;

public class IngredientDTO {

    private Long id;
    private String name;

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
    }

    public static List<IngredientDTO> toDTO(List<Ingredient> ingredients) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
