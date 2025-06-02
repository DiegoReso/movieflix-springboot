package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface StreamingController {

    @Operation(summary = "Buscar todas as streamings", description = "Endpoint para buscar todas as streamings " +
            "cadastradas",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse( responseCode = "200", description = "Lista de streamings retornada com sucesso",
            content =  @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @GetMapping
    ResponseEntity<List<StreamingResponse>> findAllStreamings();

    @Operation (summary = "Inserir uma nova streaming", description = "Endpoint para inserir uma nova streaming no " +
            "sistema",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "201", description = "Streaming inserida com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping
    ResponseEntity<StreamingResponse> insertStreaming(@Valid @RequestBody StreamingRequest streamingRequest);

    @Operation (summary = "Buscar streaming por ID", description = "Endpoint para buscar uma streaming específica " +
            "pelo ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Streaming encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse (responseCode = "404", description = "Streaming não encontrada",
            content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/{id}")
    ResponseEntity<StreamingResponse> findStreamingById(@PathVariable Long id);

    @Operation (summary = "Deletar uma streaming", description = "Endpoint para deletar uma streaming existente",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse (responseCode = "204", description = "Streaming deletada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStreaming(@PathVariable Long id);
}
