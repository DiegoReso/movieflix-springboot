package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "Email obrigatório") String email,
                           @NotEmpty(message = "Password obrigatório") String password) {
}
