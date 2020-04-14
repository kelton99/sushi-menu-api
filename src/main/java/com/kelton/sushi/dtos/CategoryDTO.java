package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<CategoryDTO> convert(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
