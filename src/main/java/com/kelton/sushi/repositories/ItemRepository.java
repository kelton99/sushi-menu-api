package com.kelton.sushi.repositories;

import com.kelton.sushi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory_Id(Long id);
}
