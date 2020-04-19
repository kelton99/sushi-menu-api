package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<IngredientDTO> toDTO(List<Ingredient> ingredients) {
        return ingredients.stream().map(IngredientDTO::new).collect(Collectors.toList());
    }
}
