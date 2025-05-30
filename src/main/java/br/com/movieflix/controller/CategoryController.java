package br.com.movieflix.controller;


import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>>  getAllCategories(){
        List<CategoryResponse> categoryResponseList = service.findAll()
                .stream().map(CategoryMapper::toCategoryRespose).toList();

        return ResponseEntity.ok(categoryResponseList);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse>  insert(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse =
                CategoryMapper.toCategoryRespose(service.insert(CategoryMapper.toCategory(categoryRequest)));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse>  getCategoryById(@PathVariable Long id){
        return service.getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryRespose(category)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
