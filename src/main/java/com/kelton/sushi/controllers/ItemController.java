package com.kelton.sushi.controllers;

import com.kelton.sushi.dtos.CompleteItemDTO;
import com.kelton.sushi.entities.Item;
import com.kelton.sushi.forms.ItemForm;
import com.kelton.sushi.repositories.CategoryRepository;
import com.kelton.sushi.repositories.IngredientRepository;
import com.kelton.sushi.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private IngredientRepository ingredientRepo;

    @GetMapping
    public Page<CompleteItemDTO> getItems(@RequestParam(required = false) String param, Pageable pagination){
        Page<Item> items;
        if(param != null)
            items = itemRepo.findByNameContains(param, pagination);
        else
            items = itemRepo.findAll(pagination);

        return CompleteItemDTO.toDTO(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteItemDTO> getItem(@PathVariable Long id){
        var item = itemRepo.findById(id);
        if(item.isPresent())
            return ResponseEntity.ok(new CompleteItemDTO(item.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompleteItemDTO> saveItem(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder){
        var item = form.toEntity(itemRepo, ingredientRepo, categoryRepo);
        itemRepo.save(item);
        URI uri = uriBuilder.path("/item/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new CompleteItemDTO(item));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CompleteItemDTO> updateItem(@PathVariable Long id, @RequestBody ItemForm form){
        var optional = itemRepo.findById(id);
        if(optional.isPresent()){
            Item item = form.update(id, itemRepo, ingredientRepo, categoryRepo);
            return ResponseEntity.ok(new CompleteItemDTO(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        var item = itemRepo.findById(id);
        if (item.isPresent()){
            itemRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
