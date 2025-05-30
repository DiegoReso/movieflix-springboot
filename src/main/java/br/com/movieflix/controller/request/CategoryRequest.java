package br.com.movieflix.controller.request;


import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome da categoria obrigatório.") String name) {
}
