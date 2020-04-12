package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;

public class IngredientDTO {

    private Long id;
    private String name;

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
