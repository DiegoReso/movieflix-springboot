package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface CategoryController {

    @Operation(summary = "Buscar todas as categorias", description = "Endpoint para buscar todas as categorias " +
            "cadastradas",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))
    )
    @GetMapping
    ResponseEntity<List<CategoryResponse>> getAllCategories();


    @Operation (summary = "Inserir uma nova categoria", description = "Endpoint para inserir uma nova categoria no " +
            "sistema",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Categoria inserido com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    @PostMapping
    ResponseEntity<CategoryResponse>  insert(@Valid @RequestBody CategoryRequest categoryRequest);


    @Operation (summary = "Buscar categoria por ID", description = "Endpoint para buscar uma categoria específica " +
            "pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    @ApiResponse (responseCode = "404", description = "Categoria não encontrada",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse>  getCategoryById(@PathVariable Long id);


    @Operation (summary = "Deletar categoria por ID", description = "Endpoint para deletar uma categoria específica " +
            "pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso",
            content = @Content(schema = @Schema(type = "void"))
    )
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
            content = @Content(schema = @Schema(type = "string"))
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);
}
