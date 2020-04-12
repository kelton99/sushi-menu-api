package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;
import com.kelton.sushi.entities.Item;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CompleteItemDTO extends ItemDTO {

    private String description;
    private Float price;
    private List<String> ingredients;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CompleteItemDTO(Item item) {
        super(item);
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.ingredients.addAll(item.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList()));
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
