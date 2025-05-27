package br.com.movieflix.controller;


import br.com.movieflix.entity.Category;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;


    @GetMapping
    public List<Category> getAllCategories(){
        List<Category> list = service.findAll();
        return list;
    }

    @PostMapping
    public Category insert(@RequestBody Category category){
        return service.insert(category);
    }

}
