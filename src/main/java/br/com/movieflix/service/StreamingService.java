package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;


    public List<Streaming> findAll() {
        return repository.findAll();
    }

    public Streaming insertStreaming(Streaming streaming){
        return repository.save(streaming);
    }

    public Optional<Streaming> findById(Long id){
        return repository.findById(id);
    }
}
