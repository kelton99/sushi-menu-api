package com.kelton.sushi.forms;

import com.kelton.sushi.entities.Category;
import com.kelton.sushi.repositories.CategoryRepository;

import java.time.LocalDateTime;


public class CategoryForm {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toEntity(CategoryRepository catRepo) {
        return new Category(name);
    }

    public Category update(Long id, CategoryRepository catRepo) {
        var category = catRepo.findById(id).get();
        category.setName(this.name);
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
}
