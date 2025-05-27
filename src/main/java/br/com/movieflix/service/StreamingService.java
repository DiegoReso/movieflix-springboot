package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
