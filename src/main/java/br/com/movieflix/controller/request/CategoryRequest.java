package br.com.movieflix.controller.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(
        @Schema(
                description = "Nome da categoria",
                example = "Romance",
                type = "String"
        )
        @NotEmpty(message = "Nome da categoria obrigatório.") String name) {
}
