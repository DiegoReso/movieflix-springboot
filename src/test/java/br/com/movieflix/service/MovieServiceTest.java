package br.com.movieflix.service;

import br.com.movieflix.entity.Movie;
import br.com.movieflix.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

        Mockito.when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));
        List<Movie> Movies = movieService.findAll();

        assertEquals(1, Movies.size());
    }

    @Test
    @DisplayName("Testa se o método retorna uma lista vazia quando não há filmes")
    void shouldReturnEmptyListWhenNoMovies() {

        Mockito.when(movieRepository.findAll()).thenReturn(Collections.emptyList());
        List<Movie> Movies = movieService.findAll();

        assertEquals(0, Movies.size());
    }

}