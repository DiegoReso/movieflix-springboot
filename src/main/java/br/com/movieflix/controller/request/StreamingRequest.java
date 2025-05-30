package br.com.movieflix.controller.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(
        @Schema (description = "ID da streaming", example = "1", type = "Long")
        @NotEmpty(message = "Nome da streaming obrigatório") String name) {
}
