package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Testa se o método retorna todos os filmes")
    void shouldReturnAllMovies() {

        Movie movie = new Movie(1L, "Test Movie", "This is a test movie", LocalDate.now(), 8.5,LocalDateTime.now(),
                LocalDateTime.now(), null, null);

        when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));
        List<Movie> Movies = movieService.findAll();

        assertEquals(1, Movies.size());
    }

    @Test
    @DisplayName("Testa se o método retorna uma lista vazia quando não há filmes")
    void shouldReturnEmptyListWhenNoMovies() {

        when(movieRepository.findAll()).thenReturn(Collections.emptyList());
        List<Movie> Movies = movieService.findAll();

        assertEquals(0, Movies.size());
    }


    @Test
    @DisplayName("Testa se o metodo retorna um filme pelo ID")
    void shouldReturnMovieById() {
        Long movieId = 1L;
        Movie movie = new Movie(movieId, "Test Movie", "This is a test movie", LocalDate.now(), 8.5, LocalDateTime.now(),
                LocalDateTime.now(), null, null);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        var foundMovie = movieService.findById(movieId);

        assertEquals(movie, foundMovie.get());
    }

    @Test
    @DisplayName("Testa se o metodo retorna um filme por categoria")
    void shouldReturnMoviesByCategory() {
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Test Category");
        Movie movie = new Movie(1L, "Test Movie", "This is a test movie", LocalDate.now(), 8.5, LocalDateTime.now(),
                LocalDateTime.now(), List.of(category), null);

        when(movieRepository.findMovieByCategories(anyList())).thenReturn(List.of(movie));
        List<Movie> movies = movieService.findByCategory(categoryId);

        assertEquals(1, movies.size());
        assertEquals(movie, movies.get(0));
    }

    @Test
    @DisplayName("Testa se o metodo retorna uma lista vazia quando não há filmes na categoria")
    void shouldReturnEmptyListWhenNoMoviesInCategory() {
        Long categoryId = 1L;

        when(movieRepository.findMovieByCategories(anyList())).thenReturn(Collections.emptyList());
        List<Movie> movies = movieService.findByCategory(categoryId);

        assertEquals(0, movies.size());
    }

    @Test
    @DisplayName("Testa se o método insere um filme")
    void shouldInsertMovie() {
        Movie movie = new Movie(1L, "New Movie", "This is a new movie", LocalDate.now(), 9.0, LocalDateTime.now(),
                LocalDateTime.now(), List.of(), List.of());

        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);
        Movie savedMovie = movieService.insert(movie);

        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getTitle()).isEqualTo("New Movie");
        assertThat(savedMovie.getDescription()).isEqualTo("This is a new movie");
    }

    @Test
    @DisplayName("Testa se o método nao insere um filme com título vazio")
    void shouldNotInsertMovieWithEmptyTitle() {
        Movie movie = new Movie(1L, "", "This is a new movie", LocalDate.now(), 9.0, LocalDateTime.now(),
                LocalDateTime.now(), List.of(), List.of());

        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieService.insert(movie);

        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getTitle()).isEmpty();
    }

    @Test
    @DisplayName("Testa se o metodo deleta o filme por id")
    void shouldDeleteMovieById(){
        Long movieId = 1L;

        Mockito.doNothing().when(movieRepository).deleteById(movieId);
        movieService.delete(movieId);
        verify(movieRepository, times(1)).deleteById(movieId);

    }

    @Test
    @DisplayName("Testa se o metodo nao deleta o filme quando id nao for encontrado")
    void shouldNotDeleteWhenIdNotFound(){
        Long nonExistentMovieId = 99L;


        verify(movieRepository, times(0)).deleteById(nonExistentMovieId);

    }
}























