package com.kelton.sushi.repositories;

import com.kelton.sushi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
