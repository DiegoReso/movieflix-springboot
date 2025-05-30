package br.com.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        @Schema (description = "ID do filme", example = "1", type = "Long")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Long id,
        @Schema (description = "Título do filme", example = "O Poderoso Chefão")
        String title,
        @Schema (description = "Descrição do filme", example = "Um épico sobre a máfia italiana.")
        String description,
        @Schema (description = "Data de lançamento do filme", example = "01/01/2020", type = "date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        @Schema (description = "Nota do filme", example = "9.9", type = "double")
        double rating,
        @Schema (description = "Lista de categorias associadas ao filme", type = "array")
        List<CategoryResponse> categories,
        @Schema (description = "Lista de streamings associadas ao filme", type = "array")
        List<StreamingResponse> streaming
                            ) {
}
