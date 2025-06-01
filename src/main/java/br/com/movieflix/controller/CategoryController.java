package br.com.movieflix.controller;


import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;

import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
@Tag (name = "Categorias", description = "Endpoints para gerenciamento de categorias")
public class CategoryController {


    private final CategoryService service;

    @Operation (summary = "Buscar todas as categorias", description = "Endpoint para buscar todas as categorias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))
    )
    @GetMapping
    public ResponseEntity<List<CategoryResponse>>  getAllCategories(){
        List<CategoryResponse> categoryResponseList = service.findAll()
                .stream().map(CategoryMapper::toCategoryRespose).toList();

        return ResponseEntity.ok(categoryResponseList);
    }

    @Operation (summary = "Inserir uma nova categoria", description = "Endpoint para inserir uma nova categoria no sistema")
    @ApiResponse(responseCode = "201", description = "Categoria inserido com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    @PostMapping
    public ResponseEntity<CategoryResponse>  insert(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse =
                CategoryMapper.toCategoryRespose(service.insert(CategoryMapper.toCategory(categoryRequest)));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @Operation (summary = "Buscar categoria por ID", description = "Endpoint para buscar uma categoria específica pelo ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    @ApiResponse (responseCode = "404", description = "Categoria não encontrada",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse>  getCategoryById(@PathVariable Long id){

            return service.getCategoryById(id)
                    .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryRespose(category)))
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o id: " + id));

    }

    @Operation (summary = "Deletar categoria por ID", description = "Endpoint para deletar uma categoria específica pelo ID")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso",
            content = @Content(schema = @Schema(type = "void"))
    )
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
            content = @Content(schema = @Schema(type = "string"))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
