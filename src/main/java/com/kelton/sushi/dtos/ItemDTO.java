package com.kelton.sushi.dtos;
import com.kelton.sushi.entities.Item;

public class ItemDTO {

    private Long id;
    private String name;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
