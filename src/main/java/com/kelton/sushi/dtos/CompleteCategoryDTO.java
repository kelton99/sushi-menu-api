package com.kelton.sushi.dtos;

import com.kelton.sushi.entities.Category;

import java.time.LocalDateTime;

public class CompleteCategoryDTO extends CategoryDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CompleteCategoryDTO(Category category) {
        super(category);
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
