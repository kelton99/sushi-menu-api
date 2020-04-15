package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Ingredient;
import com.kelton.sushi.entities.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompleteItemDTO extends ItemDTO {

    private String description;
    private Float price;
    private String category = "No category";
    private List<String> ingredients;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CompleteItemDTO(Item item) {
        super(item);
        this.description = item.getDescription();
        this.price = item.getPrice();
        if(item.getCategory() != null ) this.category = item.getCategory().getName();

        this.ingredients = new ArrayList<>();
        if(item.getIngredients() != null)
            item.getIngredients().forEach(ingredient -> this.ingredients.add(ingredient.getName()));
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getCategory() {return category; }

    public List<String> getIngredients() {
        return ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static List<CompleteItemDTO> toDTO(List<Item> items) {
        return items.stream().map(CompleteItemDTO::new).collect(Collectors.toList());
    }
}
