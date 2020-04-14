package com.kelton.sushi.forms;

import com.kelton.sushi.entities.Ingredient;
import com.kelton.sushi.repositories.IngredientRepository;

import java.time.LocalDateTime;

public class IngredientForm {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient toEntity() {
        return new Ingredient(name);
    }

    public Ingredient update(Long id, IngredientRepository ingredientRepository) {
        var ingredient = ingredientRepository.findById(id).get();
        ingredient.setName(this.name);
        ingredient.setUpdatedAt(LocalDateTime.now());
        return ingredient;
    }
}
