package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>>  findAllStreamings(){
            List<StreamingResponse> streamingResponse =  service.findAll().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

            return ResponseEntity.ok(streamingResponse);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> insertStreaming(@RequestBody StreamingRequest streamingRequest){
        StreamingResponse streaming = StreamingMapper.toStreamingResponse(service.insertStreaming(StreamingMapper.toStreaming(streamingRequest)));

        return ResponseEntity.ok(streaming);
    }


}
