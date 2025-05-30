package br.com.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserRequest(
        @Schema (description = "ID do usuário", example = "1", type = "Long")
        String name,
        @Schema (description = "Email do usuário", example = "email@email.com")
        String email,
        @Schema (description = "Senha do usuário", example = "senha123")
                        @jakarta.validation.constraints.NotEmpty(message = "Senha obrigatória")
        String password
){
}
