package br.com.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
        @Schema (description = "ID do usuário", example = "1", type = "Long")
        @NotEmpty (message = "ID do usuário obrigatório")
        String name,
        @Schema (description = "Email do usuário", example = "email@email.com")
        @NotEmpty(message = "Email obrigatório")
        String email,
        @Schema (description = "Senha do usuário", example = "senha123")
        @NotEmpty(message = "Senha obrigatória")
        String password
){
}
