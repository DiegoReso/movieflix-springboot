package br.com.movieflix.controller;


import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;

import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
@Tag (name = "Categorias", description = "Endpoints para gerenciamento de categorias")
public class CategoryControllerImpl implements CategoryController {


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
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o id: " + id));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
