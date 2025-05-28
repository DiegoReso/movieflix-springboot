package br.com.movieflix.service;

import br.com.movieflix.entity.Movie;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private MovieRepository repository;

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie insertMovie(Movie streaming){
        return repository.save(streaming);
    }

    public Optional<Movie> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
