package br.com.movieflix.controller;

import br.com.movieflix.controller.request.LoginRequest;
import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.LoginResponse;
import br.com.movieflix.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @Operation(summary = "Registrar um novo usuário", description = "Endpoint para registrar um novo usuário no sistema")
    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse (responseCode = "400", description = "Dados inválidos",
            content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/register") ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request);

    @Operation (summary = "Login de usuário", description = "Endpoint para realizar o login de um usuário no sistema")
    @ApiResponse (responseCode = "200", description = "Login realizado com sucesso",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse (responseCode = "401", description = "Usuário ou senha inválidos",
            content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/login") ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);
}
