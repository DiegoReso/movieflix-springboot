package br.com.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @Schema (description = "Email do usuário", example = "email@email")
        @NotEmpty(message = "Email obrigatório") String email,
        @Schema (description = "Senha do usuário", example = "senha123")
        @NotEmpty(message = "Password obrigatório") String password) {
}
