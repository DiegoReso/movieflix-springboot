package br.com.movieflix.controller;


import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
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

    @Autowired
    private final CategoryService service;


    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        List<Category> list = service.findAll();
        return list.stream().map(CategoryMapper::toCategoryRespose).toList();
    }

    @PostMapping
    public CategoryResponse insert(@RequestBody CategoryRequest categoryRequest){
        Category category = service.insert(CategoryMapper.toCategory(categoryRequest));
        return CategoryMapper.toCategoryRespose(category);
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
