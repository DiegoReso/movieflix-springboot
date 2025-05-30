package br.com.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(
        @Schema (description = "ID da streaming", example = "1", type = "Long")
        Long id,
        @Schema (description = "Nome da streaming", example = "Netflix")
        String name) {
}
