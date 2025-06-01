package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
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
public class StreamingController {

    private final StreamingService service;

    @Operation (summary = "Buscar todas as streamings", description = "Endpoint para buscar todas as streamings cadastradas")
    @ApiResponse( responseCode = "200", description = "Lista de streamings retornada com sucesso",
    content =  @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @GetMapping
    public ResponseEntity<List<StreamingResponse>>  findAllStreamings(){
            List<StreamingResponse> streamingResponse =  service.findAll().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

            return ResponseEntity.ok(streamingResponse);
    }

    @Operation (summary = "Inserir uma nova streaming", description = "Endpoint para inserir uma nova streaming no sistema")
    @ApiResponse(responseCode = "201", description = "Streaming inserida com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping
    public ResponseEntity<StreamingResponse> insertStreaming(@Valid  @RequestBody StreamingRequest streamingRequest){
        StreamingResponse streaming = StreamingMapper.toStreamingResponse(service.insertStreaming(StreamingMapper.toStreaming(streamingRequest)));

        return ResponseEntity.status(HttpStatus.CREATED).body(streaming);
    }

    @Operation (summary = "Buscar streaming por ID", description = "Endpoint para buscar uma streaming específica pelo ID")
    @ApiResponse(responseCode = "200", description = "Streaming encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse (responseCode = "404", description = "Streaming não encontrada",
            content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findStreamingById(@PathVariable Long id){
        return service.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElseThrow(() -> new EntityNotFoundException("Streaming não encontrado com o id: " + id));
    }

    @Operation (summary = "Deletar uma streaming", description = "Endpoint para atualizar uma streaming existente")
    @ApiResponse (responseCode = "204", description = "Streaming deletada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
