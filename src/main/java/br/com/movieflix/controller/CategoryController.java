package br.com.movieflix.controller;


import br.com.movieflix.entity.Category;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;


    @GetMapping
    public List<Category> getAllCategories(){
        return service.findAll();
    }

    @PostMapping
    public Category insert(@RequestBody Category category){
        return service.insert(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        Optional<Category> optCategory = service.getCategoryById(id);
        if(optCategory.isPresent()){
            return optCategory.get();
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        service.delete(id);
    }

}
