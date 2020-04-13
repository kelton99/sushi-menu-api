package com.kelton.sushi.controllers;

import com.kelton.sushi.dtos.CategoryDTO;
import com.kelton.sushi.dtos.CompleteCategoryDTO;
import com.kelton.sushi.entities.Category;
import com.kelton.sushi.forms.CategoryForm;
import com.kelton.sushi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository catRepo;

    @GetMapping
    public List<CategoryDTO> getCategories(){
        var categories = catRepo.findAll();
        return CategoryDTO.convert(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompleteCategoryDTO> getCategory(@PathVariable Long id){
        var category = catRepo.findById(id);
        if(category.isPresent())
            return ResponseEntity.ok(new CompleteCategoryDTO(category.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder){
        var category = form.toEntity(catRepo);
        catRepo.save(category);

        URI uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryDTO(category));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryForm form){
        var optional = catRepo.findById(id);
        if (optional.isPresent()){
            Category category= form.update(id, catRepo);
            return ResponseEntity.ok(new CategoryDTO(category));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        var category = catRepo.findById(id);

        if(category.isPresent()){
            catRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
