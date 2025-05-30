package br.com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @Schema(description = "ID do filme", example = "1", type = "Long")
        @NotEmpty(message = "Titulo do filme obrigatório") String title,
        @Schema(description = "Descrição do filme", example = "Um filme incrível sobre...")
        String description,
        @Schema(description = "Data de lançamento do filme", example = "01/01/2020", type = "date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema(description = "Nota do filme", example = "9.9", type = "double")
        double rating,
        @Schema(description = "Lista de IDs das categorias associadas ao filme", type = "array", example = "[1, 2, 3]")
        List<Long> categories,
        @Schema(description = "Lista de IDs das streamings associadas ao filme", type = "array", example = "[1, 2]")
        List<Long> streamings
) {
}
