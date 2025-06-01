package br.com.movieflix.controller;


import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
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

import java.nio.channels.NotYetBoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "Endpoints para gerenciamento de filmes")
public class MovieController {

    private final MovieService service;

    @Operation(summary = "Inserir um novo filme", description = "Endpoint para inserir um novo filme no sistema",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Filme inserido com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @PostMapping
    public ResponseEntity<MovieResponse> insert(@Valid @RequestBody MovieRequest movieRequest){
        Movie movie = service.insert(MovieMapper.toMovie(movieRequest));
        MovieResponse movieResponse = MovieMapper.toMovieResponse(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @Operation(summary = "Buscar todos os filmes", description = "Endpoint para buscar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<Movie> movies = service.findAll();
        List<MovieResponse> movieResponses = movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok().body(movieResponses);
    }

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
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));
    }


    @Operation(summary = "Deletar filme por ID", description = "Endpoint para deletar um filme específico pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado",
            content = @Content(schema = @Schema(type = "string"))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> {
                    service.delete(movie.getId());
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));

    }

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
    public ResponseEntity<MovieResponse> update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable Long id){
        return service.update(MovieMapper.toMovie(movieRequest), id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));
    }

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
    public ResponseEntity<List<MovieResponse>> getMovieParam(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategory(category).stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

}
