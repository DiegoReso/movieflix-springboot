package br.com.movieflix.controller;


import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "Endpoints para gerenciamento de filmes")
public class MovieControllerImpl implements MovieController{

    private final MovieService service;


    @PostMapping
    public ResponseEntity<MovieResponse> insert(@Valid @RequestBody MovieRequest movieRequest){
        Movie movie = service.insert(MovieMapper.toMovie(movieRequest));
        MovieResponse movieResponse = MovieMapper.toMovieResponse(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }


    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<Movie> movies = service.findAll();
        List<MovieResponse> movieResponses = movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok().body(movieResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> {
                    service.delete(movie.getId());
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable Long id){
        return service.update(MovieMapper.toMovie(movieRequest), id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com o id: " + id));
    }

    @GetMapping ("/search")
    public ResponseEntity<List<MovieResponse>> getMovieParam(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategory(category).stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

}
