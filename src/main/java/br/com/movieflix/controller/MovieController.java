package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;

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

public interface MovieController {

    @Operation(summary = "Inserir um novo filme", description = "Endpoint para inserir um novo filme no sistema",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Filme inserido com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @PostMapping
     ResponseEntity<MovieResponse> insert(@Valid @RequestBody MovieRequest movieRequest);


    @Operation(summary = "Buscar todos os filmes", description = "Endpoint para buscar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @GetMapping
    ResponseEntity<List<MovieResponse>> findAll();

    @Operation(summary = "Buscar filme por ID", description = "Endpoint para buscar um filme específico pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Filme não encontrado",
            content = @Content(schema = @Schema(type = "string"))
    )
    @GetMapping("/{id}")
    ResponseEntity<MovieResponse> findById(@PathVariable Long id);


    @Operation(summary = "Deletar filme por ID", description = "Endpoint para deletar um filme específico pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado",
            content = @Content(schema = @Schema(type = "string"))
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id);


    @Operation (summary = "Atualizar filme por ID", description = "Endpoint para atualizar um filme específico pelo " +
            "ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Filme não encontrado",
            content = @Content(schema = @Schema(type = "string"))
    )
    @PutMapping("/{id}")
    ResponseEntity<MovieResponse> update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable Long id);

    @Operation (summary = "Buscar filmes por categoria", description = "Endpoint para buscar filmes filtrados por " +
            "categoria",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Lista de filmes filtrados por categoria retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
            content = @Content(schema = @Schema(type = "string"))
    )
    @GetMapping("/search")
    ResponseEntity<List<MovieResponse>> getMovieParam(@RequestParam Long category);

}
