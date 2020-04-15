package com.kelton.sushi.controllers;

import com.kelton.sushi.dtos.CompleteIngredientDTO;
import com.kelton.sushi.dtos.IngredientDTO;
import com.kelton.sushi.entities.Ingredient;
import com.kelton.sushi.entities.Item;
import com.kelton.sushi.forms.IngredientForm;
import com.kelton.sushi.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public List<IngredientDTO> getIngredients(){
        var ingredients = ingredientRepository.findAll();
        return IngredientDTO.toDTO(ingredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteIngredientDTO> getIngredient(@PathVariable Long id){
        var ingredient = ingredientRepository.findById(id);
        if(ingredient.isPresent())
            return ResponseEntity.ok(new CompleteIngredientDTO(ingredient.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<IngredientDTO> saveIngredient(@RequestBody @Valid IngredientForm form, UriComponentsBuilder uriBuilder){
        var ingredient = form.toEntity();
        ingredientRepository.save(ingredient);

        URI uri = uriBuilder.path("/ingredient/{id}").buildAndExpand(ingredient.getId()).toUri();
        return ResponseEntity.created(uri).body(new IngredientDTO(ingredient));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable Long id, @RequestBody IngredientForm form){
        var optional = ingredientRepository.findById(id);
        if(optional.isPresent()){
            Ingredient ingredient = form.update(id, ingredientRepository);
            return ResponseEntity.ok(new IngredientDTO(ingredient));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id){
        var ingredient = ingredientRepository.findById(id);

        if(ingredient.isPresent()){
            for(Item item: ingredient.get().getItems())
                item.getIngredients().remove(ingredient.get());

            ingredientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
