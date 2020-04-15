package com.kelton.sushi.forms;

import com.kelton.sushi.entities.Item;
import com.kelton.sushi.repositories.CategoryRepository;
import com.kelton.sushi.repositories.IngredientRepository;
import com.kelton.sushi.repositories.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ItemForm {

    private String name;
    private String description;
    private Float price;
    private Long idCategory;
    private List<Long> idIngredients;

    public ItemForm() { }

    public ItemForm(String name, String description, Float price, Long idCategory, List<Long> idIngredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
        this.idIngredients = idIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public List<Long> getIdIngredients() {
        return idIngredients;
    }

    public void setIdIngredients(List<Long> idIngredients) {
        this.idIngredients = idIngredients;
    }

    public Item toEntity(ItemRepository itemRepo, IngredientRepository ingredientRepo, CategoryRepository categoryRepo){
        var item = new Item(name, description, price);
        if(idCategory != null) item.setCategory(categoryRepo.findById(idCategory).get());
        if(idIngredients != null) item.getIngredients().addAll(ingredientRepo.findAllById(idIngredients));
        return item;
    }

    public Item update(Long id, ItemRepository itemRepo, IngredientRepository ingredientRepo, CategoryRepository categoryRepo) {
        var item = itemRepo.findById(id).get();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setUpdatedAt(LocalDateTime.now());
        if(idCategory != null) item.setCategory(categoryRepo.findById(id).get());
        if(idIngredients != null) {
            item.getIngredients().clear();
            item.getIngredients().addAll(ingredientRepo.findAllById(idIngredients));}
        return item;
    }
}
