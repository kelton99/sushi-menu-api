package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CompleteIngredientDTO extends IngredientDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemDTO> itens;

    public CompleteIngredientDTO(Ingredient ingredient) {
        super(ingredient);
        this.itens.addAll(ingredient.getItems().stream().map(ItemDTO::new).collect(Collectors.toList()));
    }

    public List<ItemDTO> getItens() {
        return itens;
    }
}


