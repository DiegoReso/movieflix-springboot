package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streamings", description = "Endpoints para gerenciamento de streamings")
public class StreamingControllerImpl implements StreamingController{

    private final StreamingService service;


    @GetMapping
    public ResponseEntity<List<StreamingResponse>>  findAllStreamings(){
            List<StreamingResponse> streamingResponse =  service.findAll().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

            return ResponseEntity.ok(streamingResponse);
    }


    @PostMapping
    public ResponseEntity<StreamingResponse> insertStreaming(@Valid  @RequestBody StreamingRequest streamingRequest){
        StreamingResponse streaming = StreamingMapper.toStreamingResponse(service.insertStreaming(StreamingMapper.toStreaming(streamingRequest)));

        return ResponseEntity.status(HttpStatus.CREATED).body(streaming);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findStreamingById(@PathVariable Long id){
        return service.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElseThrow(() -> new EntityNotFoundException("Streaming não encontrado com o id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
